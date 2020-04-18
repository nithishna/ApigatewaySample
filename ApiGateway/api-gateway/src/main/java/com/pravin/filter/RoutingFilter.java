package com.pravin.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.pravin.service.AuthServiceClient;

@Component
public class RoutingFilter extends ZuulFilter {
	@Value("${jwt.header}")
	private String headerName;

	@Autowired
	private AuthServiceClient authClient;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		if (ctx.getRequest().getRequestURI() == null || !ctx.getRequest().getRequestURI().startsWith("/auth"))
			return true;
		return false;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		if (ctx != null && request != null && request.getHeader(headerName) != null) {
			ctx.addZuulRequestHeader(headerName, request.getHeader(headerName));
			try {
				ResponseEntity<Boolean> result = authClient.isExpired(request.getHeader(headerName));
				if (result.getBody()) {
					this.updateRequestContext(ctx); 	    	    	
	    	    	return null;
					//new ErrorHandler().processValidationError(new AuthenticationException("invalid user, Please enter valid credentials"));
				}
			}catch(Exception ex) {
				this.updateRequestContext(ctx); 	    	    	
    	    	return null;
			}
		} else {
			this.updateRequestContext(ctx);	    	
	    	return null;
			//new ErrorHandler().processValidationError(new AuthenticationException("invalid user, Please enter valid credentials"));
		}
		return null;
	}
	
	private void updateRequestContext(RequestContext ctx) {
		String msgError="invalid user, Please enter valid credentials";  
    	ctx.setResponseBody(msgError);
    	ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
    	ctx.setSendZuulResponse(false);
	}
}

package com.xhcms.lottery.account.web.validation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.AnnotationValidationInterceptor;

import com.opensymphony.xwork2.ActionInvocation;

public class PostAnnotationValidationInterceptor extends AnnotationValidationInterceptor {
	private static final long serialVersionUID = -4949421076262283059L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = 
			(HttpServletRequest)invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);       
		
		if("post".equalsIgnoreCase(request.getMethod())) {
			return super.doIntercept(invocation);
		} else {
			return invocation.invoke();
		}
	}
}

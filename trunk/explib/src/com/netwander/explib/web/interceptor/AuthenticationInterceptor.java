package com.netwander.explib.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.netwander.core.Constants;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("unused")
public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = 6238929093982970759L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ac = invocation.getInvocationContext();
		if (invocation.getAction().getClass().getSimpleName().equals("LoginAction") ||
			invocation.getAction().getClass().getSimpleName().equals("ExpAction")	) {
			return invocation.invoke();
		}
		//Object object = ((HttpServletRequest)ac.get(StrutsStatics.HTTP_REQUEST)).getAttribute("status");
		if (null == ac.getSession().get(Constants.USER_SESSION_KEY)) {
			HttpServletResponse response = (HttpServletResponse) ac.get(StrutsStatics.HTTP_RESPONSE);
			BaseAction.renderHtml("<script>window.top.location.href='login.do';</script>");
			return null;
		}
		return invocation.invoke();
	}
}

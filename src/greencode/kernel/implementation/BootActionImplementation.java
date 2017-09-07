package greencode.kernel.implementation;

import greencode.jscript.dom.Form;
import greencode.kernel.GreenContext;
import greencode.validator.DataValidation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract interface BootActionImplementation extends PluginImplementation {
	public boolean beforeAction(GreenContext context, Method requestMethod);

	public void afterAction(GreenContext context, Method requestMethod);

	public void beforeValidation(DataValidation dataValidation);

	public void afterValidation(Form form, DataValidation dataValidation);

	public boolean onRequest(HttpServletRequest request, HttpServletResponse response);
	
	public void onException(GreenContext context, Exception e);

	public void initUserContext(GreenContext context);
	
	public boolean whenUnauthorized(GreenContext context);
	
	public void sessionDestroyed(HttpSession session);
}

package greencode.kernel.implementation;

import greencode.jscript.Form;
import greencode.kernel.GreenContext;
import greencode.validator.DataValidation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface BootActionImplementation extends PluginImplementation {
	public boolean beforeAction(GreenContext context, Method requestMethod);

	public void afterAction(GreenContext context, Method requestMethod);

	public void beforeValidation(DataValidation dataValidation);

	public void afterValidation(Form form, DataValidation dataValidation);

	public void onRequest(HttpServletRequest request, HttpServletResponse response);
	
	public boolean onException(GreenContext context, Exception e);

	public void initUserContext(GreenContext context);
	
	public boolean whenUnauthorized(GreenContext context);
}

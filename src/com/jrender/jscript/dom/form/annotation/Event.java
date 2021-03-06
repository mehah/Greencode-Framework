package com.jrender.jscript.dom.form.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.jrender.http.enumeration.RequestMethod;
import com.jrender.jscript.dom.Window;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Event {
	public String[] name();
	public Class<? extends Window> windowAction();
	public String method() default "init";
	public RequestMethod requestMethod() default RequestMethod.GET;
}

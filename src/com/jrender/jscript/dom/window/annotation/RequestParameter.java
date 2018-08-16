package com.jrender.jscript.dom.window.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParameter {
	public String value() default "";
	public boolean trim() default false;
	public boolean removeMultipleSpaces() default false;
	public boolean useServerCharset() default true;
}

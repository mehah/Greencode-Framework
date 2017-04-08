package greencode.jscript.dom.elements;

import java.lang.reflect.ParameterizedType;

import greencode.jscript.DOMHandle;
import greencode.jscript.dom.Element;
import greencode.jscript.dom.ElementHandle;
import greencode.jscript.dom.Form;
import greencode.jscript.dom.Window;
import greencode.jscript.dom.WindowHandle;
import greencode.util.ClassUtils;

public class OptionElement<T> extends Element {
	private final Class<T> typeValue;

	protected OptionElement(Window window) {
		this(window, null);
	}

	protected OptionElement(Window window, Class<?> typeValue) {
		super(window, "option");

		this.typeValue = (Class<T>) (typeValue == null ? ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0] : typeValue);
	}

	public Form form() {
		return null;
	}

	public void text(String text) {
		DOMHandle.setProperty(this, "text", text);
	}

	public String text() {
		return DOMHandle.getVariableValueByProperty(this, "text", String.class, "text");
	}

	public T value() {
		return (T) DOMHandle.getVariableValueByProperty(this, "value", typeValue, "value");
	}

	public void value(T value) {
		if(value != null && !ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
			WindowHandle.registerObjectParamter(window, value);
		}
		
		DOMHandle.setProperty(this, "value", value == null ? "" : value.toString());
	}

	public Integer index() {
		return DOMHandle.getVariableValueByProperty(this, "index", Integer.class, "index");
	}

	public void index(Integer index) {
		DOMHandle.setProperty(this, "index", index);
	}

	public Boolean selected() {
		return DOMHandle.getVariableValueByProperty(this, "selected", Boolean.class, "selected");
	}

	public void selected(Boolean selected) {
		DOMHandle.setProperty(this, "selected", selected);
	}

	public void disabled(Boolean disabled) {
		DOMHandle.setProperty(this, "disabled", disabled);
	}

	public Boolean disabled() {
		return DOMHandle.getVariableValueByProperty(this, "disabled", Boolean.class, "disabled");
	}

	public Boolean defaultSelected() {
		return DOMHandle.getVariableValueByProperty(this, "defaultSelected", Boolean.class, "defaultSelected");
	}

	public static<T> OptionElement<T> cast(Element e) {
		return ElementHandle.cast(e, OptionElement.class);
	}
	
	public static<T> OptionElement<T> cast(Element e, Class<T> type) {
		return ElementHandle.cast(e, OptionElement.class, type);
	}
}
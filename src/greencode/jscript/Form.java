package greencode.jscript;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import greencode.http.enumeration.RequestMethod;
import greencode.jscript.annotation.QuerySelector;
import greencode.jscript.form.annotation.ElementValue;
import greencode.jscript.form.annotation.Event;
import greencode.jscript.form.annotation.Name;
import greencode.jscript.form.annotation.RegisterEvent;
import greencode.jscript.form.annotation.Visible;
import greencode.kernel.GreenContext;
import greencode.kernel.LogMessage;
import greencode.util.ClassUtils;
import greencode.util.GenericReflection;
import greencode.util.GenericReflection.Condition;

public abstract class Form extends Element {
	final Field[] elementFields = $Form.processFields(getClass());
	
	private static Condition<Field> fieldsWithRegisterEvent = new Condition<Field>() {		
		public boolean init(Field arg0) { return arg0.isAnnotationPresent(RegisterEvent.class) && ClassUtils.isParent(arg0.getType(), EventTarget.class); }
	};
	
	private static Condition<Field> fieldsWithFindElement = new Condition<Field>() {		
		public boolean init(Field arg0) { return ClassUtils.isParent(arg0.getType(), Element.class) && arg0.isAnnotationPresent(QuerySelector.class); }
	};
	
	public Form() { this(GreenContext.getInstance()); }
	
	public Form(GreenContext context) {
		super(context.currentWindow());
		String name = getClass().getAnnotation(Name.class).value();
		
		Visible visibleAnnotation = getClass().getAnnotation(Visible.class);
		
		if(visibleAnnotation == null)
			DOMHandle.registerElementByCommand(context.currentWindow().principalElement(), this, "@crossbrowser.querySelector", "form[name=\""+name+"\"]");
		else {
			HashMap<String, String[]> attrs = new HashMap<String, String[]>();
			attrs.put("display", new String[]{"none"});
			DOMHandle.registerElementByCommand(context.currentWindow().principalElement(), this, "@customMethod.querySelector", "form[name=\""+name+"\"]", attrs, !visibleAnnotation.value());
		}
	}
	
	void processAnnotation() {
		Field[] fields = GenericReflection.getDeclaredFieldsByConditionId(getClass(), "form:fieldsWithFindElement");
		if(fields == null)
			fields = GenericReflection.getDeclaredFieldsByCondition(getClass(), "form:fieldsWithFindElement", fieldsWithFindElement);
		
		for (Field f : fields) {
			if(GenericReflection.NoThrow.getValue(f, this) != null)
				continue;
			
			QuerySelector a = f.getAnnotation(QuerySelector.class);
			Class<? extends Element> type = (Class<? extends Element>)f.getType();			
			
			Element context = null;
			if(!a.context().isEmpty()) {
				for (Field f2 : fields) {
					if(f2.getName().equals(a.context())) {
						context = (Element) GenericReflection.NoThrow.getValue(f2, this);
						break;
					}
				}
				
				if(context == null)
					throw new RuntimeException(LogMessage.getMessage("green-0041", a.context(), f.getName(), getClass().getSimpleName()));				
			}
			
			Object v = null;
			if(type.isArray()) {
				type = (Class<? extends Element>) type.getComponentType();
				if(context == null)
					v = ElementHandle.cast(window.document.querySelectorAll(a.selector()), type);
				else
					v = ElementHandle.cast(context.querySelectorAll(a.selector()), type);
			} else {
				if(context == null)
					v = ElementHandle.cast(window.document.querySelector(a.selector()), type);
				else
					v = ElementHandle.cast(context.querySelector(a.selector()), type);
			}
					
			GenericReflection.NoThrow.setValue(f, v, this);
		}
		
		fields = GenericReflection.getDeclaredFieldsByConditionId(getClass(), "form:elementsWithRegisterEvent");
		if(fields == null)
			fields = GenericReflection.getDeclaredFieldsByCondition(getClass(), "form:elementsWithRegisterEvent", fieldsWithRegisterEvent);
	
		for (Field f : fields) {
			Event[] events = f.getAnnotation(RegisterEvent.class).value();
			EventTarget et = (EventTarget) GenericReflection.NoThrow.getValue(f, this);
			
			if(et == null)
				throw new RuntimeException(LogMessage.getMessage("green-0036", f.getName(), getClass().getSimpleName()));
			
			for (Event e : events) {
				FunctionHandle fh = new FunctionHandle(e.windowAction(), e.method());
				fh.setRequestMethod(e.requestMethod());
				
				for (String n : e.name())
					et.addEventListener(n, fh);
			}
		}
	}
	
	public void acceptCharset(String acceptCharset) {
		DOMHandle.setProperty(this, "acceptCharset", acceptCharset);
	}
	
	public String acceptCharset() {
		return DOMHandle.getVariableValueByProperty(this, "acceptCharset", String.class, "acceptCharset");
	}
	
	public void action(String action) {
		DOMHandle.setProperty(this, "action", action);
	}
	
	public String action() {
		return DOMHandle.getVariableValueByProperty(this, "action", String.class, "action");
	}
	
	public void enctype(String enctype) { DOMHandle.setProperty(this, "enctype", enctype); }
	
	public String enctype() {
		return DOMHandle.getVariableValueByProperty(this, "enctype", String.class, "enctype");
	}
	
	public Integer length() {
		return DOMHandle.getVariableValueByProperty(this, "length", Integer.class, "length");
	}
	
	public void method(RequestMethod method) {
		DOMHandle.setProperty(this, "method", method);
	}
	
	public RequestMethod method() {
		return RequestMethod.valueOf(DOMHandle.getVariableValueByProperty(this, "method", String.class, "method").toUpperCase());
	}
	
	public void name(String name) { DOMHandle.setProperty(this, "name", name); }
	
	public String name() {
		return DOMHandle.getVariableValueByProperty(this, "name", String.class, "name");
	}
	
	public void target(String target) { DOMHandle.setProperty(this, "target", target); }
	
	public String target() {
		return DOMHandle.getVariableValueByProperty(this, "target", String.class, "target");
	}
	
	public void reset() { DOMHandle.CustomMethod.call(this, "resetForm"); }
	public void submit() { DOMHandle.execCommand(this, "submit"); }
	
	public void fill() {
		Gson g = new Gson();
		JsonArray fields = new JsonArray();
		for (Field field : elementFields) {
			Object value = GenericReflection.NoThrow.getValue(field, this);
			if(value != null) {
				ElementValue annotation = field.getAnnotation(ElementValue.class);
				String name = annotation.name();
				if(name.isEmpty())
					name = field.getName();
				
				JsonObject o = new JsonObject();
				o.addProperty("name", name);
				o.add("values", g.toJsonTree(value));
				fields.add(o);
			}
		}
		
		if(fields.size() > 0)
			DOMHandle.CustomMethod.call(this, "fillForm", fields);
	}
}

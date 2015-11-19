package greencode.jscript.elements;

import greencode.jscript.DOMHandle;
import greencode.jscript.Window;

public abstract class InputElementCheckable<T> extends InputElementDisabling<T> {
		
	protected InputElementCheckable(String type, Window window) { super(type, window); }
	
	public void checked(Boolean checked) { DOMHandle.setProperty(this, "checked", checked); }
	
	public Boolean checked() { return DOMHandle.getVariableValueByProperty(this, "checked", Boolean.class, "checked"); }
	
	public void defaultChecked(Boolean defaultChecked) { DOMHandle.setProperty(this, "defaultChecked", defaultChecked); }
	
	public Boolean defaultChecked() { return DOMHandle.getVariableValueByProperty(this, "defaultChecked", Boolean.class, "defaultChecked"); }
}

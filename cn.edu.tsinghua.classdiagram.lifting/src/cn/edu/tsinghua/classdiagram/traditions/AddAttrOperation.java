package cn.edu.tsinghua.classdiagram.traditions;

import java.util.HashSet;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelFactory;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class AddAttrOperation extends AtomicOperation{
	private String className;
	private String attrName;
	
	public String getClassName() {
		return className;
	}

	public String getAttrName() {
		return attrName;
	}

	public AddAttrOperation() {}
	
	public AddAttrOperation(String className, String attrName) {
		this.className = className;
		this.attrName = attrName;
	}
	
	public AddAttrOperation(Diagram state, String className, String attrName) {
		this(className, attrName);
		setAllState(state);
	}
	
	@Override
	public Diagram execute() {
		Class c = curState.retrieveClass(className);
		Attribute a = ModelFactory.eINSTANCE.createAttribute();
		a.setName(attrName);
		c.getAttributes().add(a);
//		postState = (Diagram)curState.clone();
		return curState;
	}
	@Override
	protected void setRelatedElements() {
		relatedElements = new HashSet<String>();
		relatedElements.add(getReletedAttr());
	}
	
	private String getReletedAttr() {
		return StrLinker.linkAttr(className, attrName);
	}
	
}

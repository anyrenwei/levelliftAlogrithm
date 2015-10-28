package cn.edu.tsinghua.classdiagram.traditions;

import java.util.HashSet;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelFactory;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class AddClassOperation extends AtomicOperation {

	private String className;
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public AddClassOperation(){}
	
	public AddClassOperation(String className)
	{
		this.className = className;
	}

	public AddClassOperation(String className, Diagram state)
	{
		this(className);
		setAllState(state);
	}
	
	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		Class c = ModelFactory.eINSTANCE.createClass();
		c.setName(className);
		curState.addClass(c);
//		postState = (Diagram)curState.clone();
		return curState;
	}

	@Override
	protected void setRelatedElements() {
		// TODO Auto-generated method stub
		relatedElements = new HashSet<String>();
		relatedElements.add(getReletedAttr());
	}
	
	private String getReletedAttr() {
		return StrLinker.linkClass(className);
	}
	

}

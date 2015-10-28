package cn.edu.tsinghua.classdiagram.traditions;

import java.util.HashSet;
import java.util.Iterator;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class DeleteClassOperation extends AtomicOperation {

	String className;
	
	public DeleteClassOperation(){}
	
	public DeleteClassOperation(String className){
		this.className = className;
	}
	
	public DeleteClassOperation(Diagram state, String className)
	{
		this(className);
		this.setAllState(state);
	}
	
	
	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		Class c = curState.retrieveClass(className);
		if (c != null) {
			
			curState.delClass(c);
		}
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

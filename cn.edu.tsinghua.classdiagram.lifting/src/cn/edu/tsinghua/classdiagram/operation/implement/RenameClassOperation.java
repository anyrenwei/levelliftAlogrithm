package cn.edu.tsinghua.classdiagram.operation.implement;

import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

import java.util.HashSet;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;

public class RenameClassOperation extends AtomicOperation {

	private String className;
	
	private String newName;
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	public RenameClassOperation(){}
	
	public RenameClassOperation(String className, String newName){
		
		this.className = className;
		this.newName = newName;
	}
	

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		Class c = curState.retrieveClass(className);
		c.setName(newName);
		return curState;
	}

	@Override
	protected void setRelatedElements() {
		// TODO Auto-generated method stub
		relatedElements = new HashSet<String>();
		relatedElements.add(getReletedAttr());
	}
	
	private String getReletedAttr() {
		return StrLinker.linkRename(className);
	}

}

package cn.edu.tsinghua.classdiagram.operation.implement;

import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;

public class RenameAttrOperation extends AtomicOperation {

	private String className;
	private String attrName;
	
	
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setRelatedElements() {
		// TODO Auto-generated method stub

	}

}

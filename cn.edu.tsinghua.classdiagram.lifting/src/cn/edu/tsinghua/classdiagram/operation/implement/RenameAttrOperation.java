package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.HashSet;
import java.util.Iterator;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class RenameAttrOperation extends AtomicOperation {

	private String className;
	private String attrName;
	private String newAttrName;
	
	
	
	public String getNewAttrName() {
		return newAttrName;
	}

	public void setNewAttrName(String newAttrName) {
		this.newAttrName = newAttrName;
	}

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
	
	public RenameAttrOperation(){}
	
	public RenameAttrOperation(String className, String attrName, String newAttrName)
	{
		this.className = className;
		this.attrName = attrName;
		this.newAttrName = newAttrName;
	}
	
	public RenameAttrOperation(Diagram state, String className, String attrName, String newAttrName)
	{
		this(className,attrName,newAttrName);
		this.setAllState(state);
	}
	

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		Class c = curState.retrieveClass(className);
		if (c != null) {
			for(Attribute a:c.getAttributes())
			{
				if(a.getName()!=null&&a.getName().equals(attrName))
				{
					a.setName(newAttrName);
				}
			}
		}
//		postState = (Diagram)curState.clone();
		return curState;
	}

	@Override
	protected void setRelatedElements() {
		relatedElements = new HashSet<String>();
		relatedElements.add(getReletedAttr());
	}
	
	private String getReletedAttr() {
		return StrLinker.linkRenameAttribute(className, newAttrName);
	}


}

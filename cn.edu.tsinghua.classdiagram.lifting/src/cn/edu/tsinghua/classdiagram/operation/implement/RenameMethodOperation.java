package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.HashSet;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.Method;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class RenameMethodOperation extends AtomicOperation {

	private String className;
	private String methodName;
	private String newMethodName;
	
	public RenameMethodOperation(){}
	
	public RenameMethodOperation(String className, String methodName, String newMethodName){
		
		this.className = className;
		this.methodName = methodName;
		this.newMethodName = newMethodName;
	}
	
	public RenameMethodOperation(Diagram state, String className, String methodName, String newMethodName){
		
		this(className,methodName,newMethodName);
		this.setAllState(state);
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getNewMethodName() {
		return newMethodName;
	}

	public void setNewMethodName(String newMethodName) {
		this.newMethodName = newMethodName;
	}

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		Class c = curState.retrieveClass(className);
		if (c != null) {
			for(Method m:c.getMethods())
			{
				if(m.getName()!=null&&m.getName().equals(methodName))
				{
					m.setName(newMethodName);
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
		return StrLinker.linkRenameMethod(className, newMethodName);
	}

}

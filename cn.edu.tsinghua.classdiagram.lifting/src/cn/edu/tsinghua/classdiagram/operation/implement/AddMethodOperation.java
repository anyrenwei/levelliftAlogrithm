package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.HashSet;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.Method;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelFactory;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class AddMethodOperation extends AtomicOperation {

	private String className;
	
	private String methodName;
	
	public AddMethodOperation(){}
	
	public AddMethodOperation(String className, String methodName){
		
		this.className = className;
		
		this.methodName = methodName;
		
	}
	
	public AddMethodOperation(Diagram state, String className, String methodName){
		
		this(className, methodName);
		setAllState(state);
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

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		Class c = curState.retrieveClass(className);
		Method m = ModelFactory.eINSTANCE.createMethod();
		m.setName(methodName);
		c.getMethods().add(m);
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
		return StrLinker.linkMeth(className, methodName);
	}
	
}

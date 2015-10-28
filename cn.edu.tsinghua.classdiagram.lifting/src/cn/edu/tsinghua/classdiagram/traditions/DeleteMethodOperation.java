package cn.edu.tsinghua.classdiagram.traditions;

import java.util.HashSet;
import java.util.Iterator;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.Method;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class DeleteMethodOperation extends AtomicOperation {

	String className;
	
	String methodName;
	
	
	
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
	
	public DeleteMethodOperation(){}
	
	public DeleteMethodOperation(String className, String methodName){
		
		this.className = className;
		this.methodName = methodName;
	}
	public DeleteMethodOperation(Diagram state, String className, String methodName){
		
		this(className,methodName);
		setAllState(state);
	}
	
	
	

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		Class c = curState.retrieveClass(className);
		if (c != null) {
			for (Iterator<Method> i = c.getMethods().iterator();i.hasNext();) {
				Method m = i.next();
				// 名字空是非法的，自己写一定设置名字
				if (m.getName() == null || m.getName().equals(methodName))
					i.remove();
			}
		}
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

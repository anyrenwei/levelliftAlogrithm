package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class PushDownMethodOperation extends CompositeOperation {

	private String subClassName;
	private String methodName;
	
	
	public PushDownMethodOperation(){
		
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(MoveMethodOperation.class);
	}
	
	public PushDownMethodOperation(String subClassName, String methodName){
		this();
		this.subClassName = subClassName;
		this.methodName = methodName;
	}
	
	public PushDownMethodOperation(Diagram state, String subClassName, String methodName)
	{
		this(subClassName,methodName);
		this.setAllState(state);
		this.initSubOperations();
	}
	
	
	@Override
	public void initSubOperations() {
		// TODO Auto-generated method stub

		subOperations = new ArrayList<Operation>();
	}

	@Override
	public CompositeOperation generateByMains(List<Operation> mains) {
		// TODO Auto-generated method stub

		if (mains.size() != 1
				|| !mains.get(0).getClass().equals(MoveMethodOperation.class))
			return null;
		MoveMethodOperation moveOp = (MoveMethodOperation) mains.get(0);
		Diagram preState = moveOp.getPreState();
		Class fromClass = preState.retrieveClass(moveOp.getFromClass());
		Class toClass = preState.retrieveClass(moveOp.getToClass());
		if (toClass.getSuper() == null
				|| !toClass.getSuper().getName().equals(fromClass.getName()))
			return null;

		this.subClassName = moveOp.getFromClass();
		// this.parentClass = moveOp.getToClass();
		this.methodName = moveOp.getMethodName();
		// 暂时将move的pre当做自己的pre
		return new PushDownMethodOperation(preState, subClassName, methodName);
	
	}

	@Override
	public boolean searchOthers(List<Operation> sequence, List<Integer> resultIndexs) {
		// TODO Auto-generated method stub

		if (resultIndexs.size() < 1)
			return false;
		// 只有一个主操作move
		int main = resultIndexs.get(0);
		// pushdown的所有非主操作都是AddAttrOperation
		for (int i = main - 1; i >= 0; i--) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof AddMethodOperation))
				break;
			// 这里相关的删除操作一定是删除子类相同属性的操作f，即子操作
			resultIndexs.add(i);
		}
		for (int i = main + 1; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof AddMethodOperation))
				break;
			resultIndexs.add(i);
		}

		Class parent = preState.retrieveClass(subClassName);
		// 操作的数量应当等于子类数量
		if (resultIndexs.size() < parent.getChildren().size())
			return false;
		return true;
		}

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setRelatedElements() {
		// TODO Auto-generated method stub

		relatedElements = new HashSet<String>();
		relatedElements.add(StrLinker.linkAttr(subClassName, methodName));
		Class parent = preState.retrieveClass(subClassName);
		for (Class c : parent.getChildren()) {
			relatedElements.add(StrLinker.linkAttr(c.getName(), methodName));
			relatedElements.add(StrLinker.linkSuper(c.getName()));
		}
	
	}

	public String getSubClassName() {
		return subClassName;
	}

	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}

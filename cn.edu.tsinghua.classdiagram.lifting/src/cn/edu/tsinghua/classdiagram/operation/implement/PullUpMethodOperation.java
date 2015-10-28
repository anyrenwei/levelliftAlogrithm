package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class PullUpMethodOperation extends CompositeOperation {

	private String parentClassName;
	private String methodName;
	
	public PullUpMethodOperation(){
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(MoveMethodOperation.class);
	}
	
	public PullUpMethodOperation(String parentClassName, String methodName)
	{
		this();
		this.parentClassName  = parentClassName;
		this.methodName = methodName;
	}
	
	public PullUpMethodOperation(Diagram state, String parentClassName, String methodName)
	{
		this(parentClassName, methodName);
		setAllState(state);
		initSubOperations();
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
		if (fromClass.getSuper() == null
				|| !fromClass.getSuper().getName().equals(toClass.getName()))
			return null;

		this.parentClassName = moveOp.getToClass();
		// this.parentClass = moveOp.getToClass();
		this.methodName = moveOp.getMethodName();
		// 暂时将move的pre当做自己的pre
		return new PullUpMethodOperation(preState, parentClassName,
				methodName);
	}

	@Override
	public boolean searchOthers(List<Operation> sequence, List<Integer> resultIndexs) {
		// TODO Auto-generated method stub
		if (resultIndexs.size() < 1)
			return false;
		// 只有一个主操作move
		int main = resultIndexs.get(0);
		// pullup的所有非主操作都是DeleteAttrOperation
		for (int i = main - 1; i >= 0; i--) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof DeleteMethodOperation))
				break;
			// DeleteAttrOperation delOp = (DeleteAttrOperation)o;
			// 这里相关的删除操作一定是删除子类相同属性的操作f，即子操作
			resultIndexs.add(i);
		}
		for (int i = main + 1; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof DeleteMethodOperation))
				break;
			resultIndexs.add(i);
		}

		Class parent = preState.retrieveClass(parentClassName);
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
		relatedElements.add(StrLinker.linkMeth(parentClassName, methodName));
		Class parent = preState.retrieveClass(parentClassName);
		for (Class c : parent.getChildren()) {
			relatedElements.add(StrLinker.linkMeth(c.getName(), methodName));
			relatedElements.add(StrLinker.linkSuper(c.getName()));
		}
	
	}

	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}

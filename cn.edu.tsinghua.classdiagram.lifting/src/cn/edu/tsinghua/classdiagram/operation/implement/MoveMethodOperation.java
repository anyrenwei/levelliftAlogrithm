package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class MoveMethodOperation extends CompositeOperation {

	private String fromClass;
	private String toClass;
	private String methodName;
	
	
	public MoveMethodOperation(){
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(DeleteMethodOperation.class);
		mainOperationsType.add(AddMethodOperation.class);
	}
	
	public MoveMethodOperation(String fromClass, String toClass, String methodName){
		
		this();
		this.fromClass = fromClass;
		this.toClass = toClass;
		this.methodName = methodName;
	}
	
	public MoveMethodOperation(Diagram state,String fromClass, String toClass, String methodName){
		this(fromClass, toClass, methodName);
		this.setAllState(state);
		this.initSubOperations();
	}
	
	
	@Override
	public void initSubOperations() {
		// TODO Auto-generated method stub
		subOperations = new ArrayList<Operation>();
		subOperations.add(new DeleteMethodOperation(fromClass, methodName));
		subOperations.add(new AddMethodOperation(toClass, methodName));
	}

	@Override
	protected void setRelatedElements() {
		String elements[] = { StrLinker.linkAttr(fromClass, methodName),
				StrLinker.linkAttr(toClass, methodName) };
		relatedElements = new HashSet<String>(Arrays.asList(elements));
	}
	
	@Override
	public boolean searchOtherMains(List<Operation> sequence,
			List<Integer> resultIndexs) {
		System.out.println("MoveMethodOperation.searchOtherMains()");
		if (null == resultIndexs || resultIndexs.isEmpty())
			return false;

		// 数组里应当为一个Delete,寻找另一个Add
		int deleteOpIndex = resultIndexs.get(0);
		// 省略类型检查
		System.out.println("op:"+sequence.get(deleteOpIndex).getClass().getSimpleName());
		DeleteMethodOperation delOp = (DeleteMethodOperation) sequence
				.get(deleteOpIndex);
		for (int i = deleteOpIndex - 1; i >= 0; i--) {
			Operation o = sequence.get(i);
			// 该属性删除前进行了使用
			if (delOp.relatedWith(o))
				break;
			if (!(o instanceof AddMethodOperation))
				continue;
			AddMethodOperation addOp = (AddMethodOperation) o;
			if (!addOp.getMethodName().equals(delOp.getMethodName()))
				continue;
			resultIndexs.add(i);
			return true;
		}

		for (int i = deleteOpIndex + 1; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			if (delOp.relatedWith(o))
				break;
			if (!(o instanceof AddMethodOperation))
				continue;
			AddMethodOperation addOp = (AddMethodOperation) o;
			if (!addOp.getMethodName().equals(delOp.getMethodName()))
				continue;
			resultIndexs.add(i);
			return true;
		}
		return false;
	}
	
	
	@Override
	public CompositeOperation generateByMains(List<Operation> mains) {
		// TODO Auto-generated method stub

		if (mains.size() != 2)
			return null;
		int addOpIndex = 0;
		if (mains.get(0) instanceof DeleteMethodOperation)
			addOpIndex = 1;
		AddMethodOperation addOp = (AddMethodOperation) mains.get(addOpIndex);
		DeleteMethodOperation delOp = (DeleteMethodOperation) mains
				.get(1 - addOpIndex);

		// 组合成候选的MoveMethodOperation
		return new MoveMethodOperation(delOp.getClassName(),
				addOp.getClassName(), addOp.getMethodName());
	
	}
	
	/**
	 * 对于move，没有主操作以外的操作，只需要验证合法就可以
	 * 
	 * @return 成功返回true，表明可以进行提升
	 * */
	@Override
	public boolean searchOthers(List<Operation> sequence,
			List<Integer> resultIndexs) {
		int begin = resultIndexs.get(0);
		int end = resultIndexs.get(1);
		// 两个自操作之间，没有其他操作进行相同元素的操作
		for (int i = begin + 1; i < end; i++) {
			// if(resultIndexs.contains(i))//这里可以优化，因为mainIndexs是递增的
			// continue;
			if (relatedWith(sequence.get(resultIndexs.get(i))))
				return false;
		}
		return true;
	}

	@Override
	public Diagram execute() {
		curState = new DeleteMethodOperation(curState, fromClass, methodName)
				.execute();
		curState = new AddMethodOperation(curState, toClass, methodName)
				.execute();
		return curState;
	}

	public String getFromClass() {
		return fromClass;
	}

	public void setFromClass(String fromClass) {
		this.fromClass = fromClass;
	}

	public String getToClass() {
		return toClass;
	}

	public void setToClass(String toClass) {
		this.toClass = toClass;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}

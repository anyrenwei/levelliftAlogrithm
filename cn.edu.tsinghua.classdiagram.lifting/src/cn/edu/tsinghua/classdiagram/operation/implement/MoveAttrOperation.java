package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class MoveAttrOperation extends CompositeOperation {

	private String fromClassName;
	private String toClassName;
	private String attrName;

	public String getFromClassName() {
		return fromClassName;
	}

	public String getToClassName() {
		return toClassName;
	}

	public String getAttrName() {
		return attrName;
	}

	public MoveAttrOperation() {
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(DeleteAttrOperation.class);
		mainOperationsType.add(AddAttrOperation.class);
	}

	public MoveAttrOperation(String fromClassName, String toClassName,
			String attrName) {
		this();
		this.fromClassName = fromClassName;
		this.toClassName = toClassName;
		this.attrName = attrName;
	}

	public MoveAttrOperation(Diagram state, String fromClassName,
			String toClassName, String attrName) {
		this(fromClassName, toClassName, attrName);
		setAllState(state);
		initSubOperations();
	}

	@Override
	public void initSubOperations() {
		subOperations = new ArrayList<Operation>();
		subOperations.add(new DeleteAttrOperation(fromClassName, attrName));
		subOperations.add(new AddAttrOperation(toClassName, attrName));
	}

	@Override
	protected void setRelatedElements() {
		String elements[] = { StrLinker.linkAttr(fromClassName, attrName),
				StrLinker.linkAttr(toClassName, attrName) };
		relatedElements = new HashSet<String>(Arrays.asList(elements));
	}

	@Override
	public boolean searchOtherMains(List<Operation> sequence,
			List<Integer> resultIndexs) {
		System.out.println("MoveAttrOperation.searchOtherMains()");
		if (null == resultIndexs || resultIndexs.isEmpty())
			return false;

		// 数组里应当为一个Delete,寻找另一个Add
		int deleteOpIndex = resultIndexs.get(0);
		// 省略类型检查
		System.out.println("op:"+sequence.get(deleteOpIndex).getClass().getSimpleName());
		DeleteAttrOperation delOp = (DeleteAttrOperation) sequence
				.get(deleteOpIndex);
		for (int i = deleteOpIndex - 1; i >= 0; i--) {
			Operation o = sequence.get(i);
			// 该属性删除前进行了使用
			if (delOp.relatedWith(o))
				break;
			if (!(o instanceof AddAttrOperation))
				continue;
			AddAttrOperation addOp = (AddAttrOperation) o;
			if (!addOp.getAttrName().equals(delOp.getAttrName()))
				continue;
			resultIndexs.add(i);
			return true;
		}

		for (int i = deleteOpIndex + 1; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			if (delOp.relatedWith(o))
				break;
			if (!(o instanceof AddAttrOperation))
				continue;
			AddAttrOperation addOp = (AddAttrOperation) o;
			if (!addOp.getAttrName().equals(delOp.getAttrName()))
				continue;
			resultIndexs.add(i);
			return true;
		}
		return false;
	}

	@Override
	public CompositeOperation generateByMains(List<Operation> mains) {
		if (mains.size() != 2)
			return null;
		int addOpIndex = 0;
		if (mains.get(0) instanceof DeleteAttrOperation)
			addOpIndex = 1;
		AddAttrOperation addOp = (AddAttrOperation) mains.get(addOpIndex);
		DeleteAttrOperation delOp = (DeleteAttrOperation) mains
				.get(1 - addOpIndex);

		// 组合成候选的MoveAttrOperation
		return new MoveAttrOperation(delOp.getClassName(),
				addOp.getClassName(), addOp.getAttrName());
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
		curState = new DeleteAttrOperation(curState, fromClassName, attrName)
				.execute();
		curState = new AddAttrOperation(curState, fromClassName, attrName)
				.execute();
		return curState;
	}

}

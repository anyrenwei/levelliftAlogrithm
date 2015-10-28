package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class PushDownAttrOperation extends CompositeOperation {
	private String subClassName;
	private String attrName;

	public PushDownAttrOperation() {
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(MoveAttrOperation.class);
	}

	public PushDownAttrOperation(String subClassName, String attrName) {
		this();
		this.subClassName = subClassName;
		this.attrName = attrName;
	}

	public PushDownAttrOperation(Diagram state, String subClassName,
			String attrName) {
		this(subClassName, attrName);
		setAllState(state);
		initSubOperations();
	}

	@Override
	public void initSubOperations() {
		// TODO:补充完整，暂时用不到
		subOperations = new ArrayList<Operation>();
	}

	@Override
	public CompositeOperation generateByMains(List<Operation> mains) {
		if (mains.size() != 1
				|| !mains.get(0).getClass().equals(MoveAttrOperation.class))
			return null;
		MoveAttrOperation moveOp = (MoveAttrOperation) mains.get(0);
		Diagram preState = moveOp.getPreState();
		Class fromClass = preState.retrieveClass(moveOp.getFromClassName());
		Class toClass = preState.retrieveClass(moveOp.getToClassName());
		if (toClass.getSuper() == null
				|| !toClass.getSuper().getName().equals(fromClass.getName()))
			return null;

		this.subClassName = moveOp.getFromClassName();
		// this.parentClass = moveOp.getToClass();
		this.attrName = moveOp.getAttrName();
		// 暂时将move的pre当做自己的pre
		return new PushDownAttrOperation(preState, subClassName, attrName);
	}

	/**
	 * 类似pullup的搜索
	 */
	@Override
	public boolean searchOthers(List<Operation> sequence,
			List<Integer> resultIndexs) {
		if (resultIndexs.size() < 1)
			return false;
		// 只有一个主操作move
		int main = resultIndexs.get(0);
		// pushdown的所有非主操作都是AddAttrOperation
		for (int i = main - 1; i >= 0; i--) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof AddAttrOperation))
				break;
			// 这里相关的删除操作一定是删除子类相同属性的操作f，即子操作
			resultIndexs.add(i);
		}
		for (int i = main + 1; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof AddAttrOperation))
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
	protected void setRelatedElements() {
		relatedElements = new HashSet<String>();
		relatedElements.add(StrLinker.linkAttr(subClassName, attrName));
		Class parent = preState.retrieveClass(subClassName);
		for (Class c : parent.getChildren()) {
			relatedElements.add(StrLinker.linkAttr(c.getName(), attrName));
			relatedElements.add(StrLinker.linkSuper(c.getName()));
		}
	}

	public String getSubClassName() {
		return subClassName;
	}

	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
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

}

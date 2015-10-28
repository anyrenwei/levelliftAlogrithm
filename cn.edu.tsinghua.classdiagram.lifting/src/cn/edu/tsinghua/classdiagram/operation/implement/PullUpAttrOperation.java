package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class PullUpAttrOperation extends CompositeOperation {
	public String getParentClassName() {
		return parentClassName;
	}

	public void setParentClassName(String parentClassName) {
		this.parentClassName = parentClassName;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	private String parentClassName;
	private String attrName;
	// private Class parentClass;

	public PullUpAttrOperation() {
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(MoveAttrOperation.class);
	}

	public PullUpAttrOperation(String parentClassName, String attrName) {
		this();
		this.parentClassName = parentClassName;
		this.attrName = attrName;
	}

	public PullUpAttrOperation(Diagram state, String parentClass,
			String attrName) {
		this(parentClass, attrName);
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
		if (fromClass.getSuper() == null
				|| !fromClass.getSuper().getName().equals(toClass.getName()))
			return null;

		this.parentClassName = moveOp.getToClassName();
		// this.parentClass = moveOp.getToClass();
		this.attrName = moveOp.getAttrName();
		// 暂时将move的pre当做自己的pre
		return new PullUpAttrOperation(preState, parentClassName,
				attrName);
	}

	@Override
	public boolean searchOthers(List<Operation> sequence,
			List<Integer> resultIndexs) {
		if (resultIndexs.size() < 1)
			return false;
		// 只有一个主操作move
		int main = resultIndexs.get(0);
		// pullup的所有非主操作都是DeleteAttrOperation
		for (int i = main - 1; i >= 0; i--) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof DeleteAttrOperation))
				break;
			// DeleteAttrOperation delOp = (DeleteAttrOperation)o;
			// 这里相关的删除操作一定是删除子类相同属性的操作f，即子操作
			resultIndexs.add(i);
		}
		for (int i = main + 1; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof DeleteAttrOperation))
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
	protected void setRelatedElements() {
		relatedElements = new HashSet<String>();
		relatedElements.add(StrLinker.linkAttr(parentClassName, attrName));
		Class parent = preState.retrieveClass(parentClassName);
		for (Class c : parent.getChildren()) {
			relatedElements.add(StrLinker.linkAttr(c.getName(), attrName));
			relatedElements.add(StrLinker.linkSuper(c.getName()));
		}
	}

	@Override
	public Diagram execute() {
		return null;// 暂时用不到
	}

}

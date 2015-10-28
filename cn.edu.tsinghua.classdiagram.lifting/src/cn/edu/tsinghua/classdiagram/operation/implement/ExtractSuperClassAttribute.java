package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

public class ExtractSuperClassAttribute extends CompositeOperation {

	private String superClassName;
	
	private String attrName;
	
	
	public ExtractSuperClassAttribute(){
		
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(PullUpAttrOperation.class);
	}
	
	public ExtractSuperClassAttribute(String superClassName, String attrName){
		
		this();
		this.superClassName = superClassName;
		this.attrName = attrName;
		
	}
	public ExtractSuperClassAttribute(Diagram state, String superClassName, String attrName){
		
		this(superClassName,attrName);
		this.setAllState(state);
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
				|| !mains.get(0).getClass().equals(PullUpAttrOperation.class))
			return null;
		PullUpAttrOperation pullUpOp = (PullUpAttrOperation) mains.get(0);
		Diagram preState = pullUpOp.getPreState();
		Class parentClass = preState.retrieveClass(pullUpOp.getParentClassName());
//		Class toClass = preState.retrieveClass(moveOp.getToClassName());
		if (parentClass.getSuper() == null)
			return null;

		this.superClassName = pullUpOp.getParentClassName();
		// this.parentClass = moveOp.getToClass();
		this.attrName = pullUpOp.getAttrName();
		// 暂时将move的pre当做自己的pre
		return new PullUpAttrOperation(preState, superClassName,
				attrName);
	}

	@Override
	public boolean searchOthers(List<Operation> sequence, List<Integer> resultIndexs) {
		// TODO Auto-generated method stub
		if (resultIndexs.size() < 1)
			return false;
		// 只有一个主操作pullUp
		int main = resultIndexs.get(0);
		// 的所有非主操作都是AddClassOperation
		for (int i = main - 1; i >= 0; i--) {
			Operation o = sequence.get(i);
			if (!relatedWith(o))
				continue;
			if (!(o instanceof AddClassOperation))
				break;
			// DeleteAttrOperation delOp = (DeleteAttrOperation)o;
			// 这里相关的删除操作一定是删除子类相同属性的操作f，即子操作
			resultIndexs.add(i);
		}
		for (int i = main + 1; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			//related方法得修改
			if (!relatedWith(o))
				continue;
			if (!(o instanceof AddClassOperation))
				break;
			resultIndexs.add(i);
		}

		Class parent = preState.retrieveClass(superClassName);
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
		relatedElements.add(StrLinker.linkAttr(superClassName, attrName));
		Class parent = preState.retrieveClass(superClassName);
		for (Class c : parent.getChildren()) {
			relatedElements.add(StrLinker.linkAttr(c.getName(), attrName));
			relatedElements.add(StrLinker.linkSuper(c.getName()));
		}
	}

}

package cn.edu.tsinghua.classdiagram.operation.implement;

import java.util.ArrayList;
import java.util.List;

import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;

public class ExtractSubClassAttribute extends CompositeOperation {

	private String subClassName;
	private String attrName;
	
	public ExtractSubClassAttribute(){
		
		mainOperationsType = new ArrayList<java.lang.Class<?>>();
		mainOperationsType.add(PushDownAttrOperation.class);
	}
	
	public ExtractSubClassAttribute(String subClassName, String attrName)
	{
		this();
		this.subClassName = subClassName;
		this.attrName = attrName;
	}
	
	public ExtractSubClassAttribute(Diagram state, String subClassName, String attrName){
		
		this(subClassName,attrName);
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

		// TODO Auto-generated method stub
		if (mains.size() != 1
				|| !mains.get(0).getClass().equals(PushDownAttrOperation.class))
			return null;
		PushDownAttrOperation pushDownOp = (PushDownAttrOperation) mains.get(0);
		Diagram preState = pushDownOp.getPreState();
		Class parentClass = preState.retrieveClass(pushDownOp.getSubClassName());
//		Class toClass = preState.retrieveClass(moveOp.getToClassName());
		if (parentClass.getSuper() == null)
			return null;

		this.subClassName = pushDownOp.getSubClassName();
		// this.parentClass = moveOp.getToClass();
		this.attrName = pushDownOp.getAttrName();
		// 暂时将move的pre当做自己的pre
		return new ExtractSubClassAttribute(preState, subClassName,
				attrName);
	
	}

	@Override
	public boolean searchOthers(List<Operation> sequence, List<Integer> resultIndexs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Diagram execute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setRelatedElements() {
		// TODO Auto-generated method stub

	}

}

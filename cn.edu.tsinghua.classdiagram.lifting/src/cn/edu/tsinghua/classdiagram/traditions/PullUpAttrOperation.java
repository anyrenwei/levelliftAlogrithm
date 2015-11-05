package cn.edu.tsinghua.classdiagram.traditions;

import java.util.List;

import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;

public class PullUpAttrOperation extends CompositeOperation{

	
	private final Class<?>[] liftOperations = { AddAttribute.class, PullUpAttrOperation.class, PushDownAttrOperation.class };

	
	
	@Override
	public void initSubOperations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CompositeOperation generateByMains(List<Operation> mains) {
		// TODO Auto-generated method stub
		return null;
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

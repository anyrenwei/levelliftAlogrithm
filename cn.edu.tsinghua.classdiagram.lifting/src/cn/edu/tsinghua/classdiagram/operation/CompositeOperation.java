package cn.edu.tsinghua.classdiagram.operation;

import java.util.List;
import java.util.Set;

import cn.edu.tsinghua.classdiagram.diagram.Diagram;

/**
 * 
 * @author CrazyCake 组合操作，需要定义子操作及其主操作
 */
public abstract class CompositeOperation extends Operation {

	/** 子操作 */
	protected List<Operation> subOperations;
	/** 具体操作的主操作 */
	protected List<Operation> mainOperations;

	/**所有该组合操作类型，都应该具有的主操作的类型*/
	protected List<Class<?>> mainOperationsType;
	/**
	 * @return the subOperations
	 */
	public List<Operation> getSubOperations() {
		return subOperations;
	}

	/**
	 * @param subOperations
	 *            the subOperations to set
	 */
	public void setSubOperations(List<Operation> subOperations) {
		this.subOperations = subOperations;
	}

	public List<Operation> getMainOperations() {
		return mainOperations;
	}

	public void setMainOperations(List<Operation> mainOperations) {
		this.mainOperations = mainOperations;
	}

	
	public CompositeOperation() {
	}

	/** 返回主操作的类型，用于查找 */
	public List<Class<?>> getMainOperationsType(){
		return mainOperationsType;
	}

	/** 完成对子操作序列的初始化操作，并定义主操作 */
	public abstract void initSubOperations();
	/** 根据主操作定义出唯一确定的组合操作*/
	public abstract CompositeOperation generateByMains(List<Operation> mains);
	/** 在找到一个主操作的前提下，找到剩余的主操作*/
	public boolean searchOtherMains(List<Operation> sequence, List<Integer> resultIndexs) {
		return true;
	}
	/** 在找到所有主操作后，找到剩余操作*/
	public abstract boolean searchOthers(List<Operation> sequence, List<Integer> resultIndexs);
//	/** 对于定义完整子操作的可以这样调用，实现应当在每个操作中具体写完成的改变 */
//	@Override
//	public Diagram execute() {
//		for (Operation o : subOperations) {
//			o.setCurState(curState);
//			o.execute();
//		}
//		return curState;
//	}
//	/**检查主操作区间内的这些操作是否与本操作相关*/
//	public boolean relatedWith(List<Operation> sequence, List<Integer> mainIndexs) {
//		int begin = mainIndexs.get(0);
//		int end = mainIndexs.get(mainIndexs.size()-1);
//		for(int i = begin+1;i<end;i++) {
//			if(mainIndexs.contains(i))//这里可以优化，因为mainIndexs是递增的
//				continue;
//			if(relatedWith(sequence.get(mainIndexs.get(i))))
//				return true;
//		}
//		return false;
//	}

}

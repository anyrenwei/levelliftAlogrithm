package cn.edu.tsinghua.classdiagram.operation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.edu.tsinghua.classdiagram.diagram.Diagram;

public abstract class Operation {
	/** 操作前的状态，保留快照 */
	protected Diagram preState;
	// /**操作后的状态，保留快照*/
	// protected Diagram postState;
	/** 当前进行操作的状态，也即传入的状态，在执行execute时进行修改 */
	protected Diagram curState;

	protected Set<String> relatedElements;

	public void setAllState(Diagram state) {
		this.preState = (Diagram) state.clone();
		this.curState = state;
		setRelatedElements();
	}

	public Diagram getPreState() {
		return preState;
	}

	public void setCurState(Diagram curState) {
		this.curState = curState;
	}

	public Operation() {

	}

	public abstract Diagram execute();

	/**
	 * 该操作的相关元素，属性方法的字符串以className.a.attrName和className.m.methodName的格式表示
	 * 对于组合操作，也是在合并过程中，不能被子操作以外的操作修改的元素
	 **/
	protected abstract void setRelatedElements();

	/** 两个操作相关元素有交集，则相关 */
	public boolean relatedWith(Operation o) {
		Set<String> set = new HashSet<String>();
		set.addAll(o.relatedElements);
		set.addAll(this.relatedElements);
		return set.size() < o.relatedElements.size()
				+ this.relatedElements.size();
	}

}

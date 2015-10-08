package cn.edu.tsinghua.classdiagram.lifting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.CompositeOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.operation.implement.MoveAttrOperation;
import cn.edu.tsinghua.classdiagram.operation.implement.PullUpAttrOperation;
import cn.edu.tsinghua.classdiagram.operation.implement.PushDownAttrOperation;

public class LevelLifting {
	private final Class<?>[] liftOperations = { MoveAttrOperation.class, PullUpAttrOperation.class, PushDownAttrOperation.class };
	private List<Operation> sequence;

	public LevelLifting(List<Operation> sequence) {
		this.sequence = sequence;
	}

	public void liftLevel() {
		// 从简单的组合操作开始，尝试提升为这些组合操作
		for (Class<?> c : liftOperations) {
			CompositeOperation o = null;
			try {
				o = (CompositeOperation) c.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			System.out.println("LiftOP:" + c.getSimpleName());
			// 查找到一个并提升到该操作//sequence将被修改
			liftOne(o);
		}

	}

	public void liftOne(CompositeOperation target) {
		// 尝试提升为o
		// 记录参与提升的子操作的序号
		List<Integer> resultIndexs = new ArrayList<Integer>();
		// 1.search main operations, 分两种，只有一个主/多个主
		searchMains(0, target, resultIndexs);
		if (resultIndexs.size() < target.getMainOperationsType().size())
			return;
		// 2.根据main，得到想合成的操作的参数，从而得到该操作
		List<Operation> candidateMains = new ArrayList<Operation>();

		for (Integer index : resultIndexs) {
			System.out.println("Searched Index:" + index);
			candidateMains.add(sequence.get(index));
		}
		CompositeOperation op = target.generateByMains(candidateMains);
		if(op == null)
			return;
		// 3.search all operations，需要跳过无关操作
		boolean success = op.searchOthers(sequence, resultIndexs);
		// 4.查找成功，需要进行提升
		if (success) {
			List<Operation> newSeq = new ArrayList<Operation>();
			Collections.sort(resultIndexs);// 
			int k = 0;// 指向resultIndexs
			//将无关的不需要提升的操作先放到新的序列中
			for (int i = 0; i < sequence.size(); i++) {
				if (i == resultIndexs.get(k)) {
					k++;
					continue;
				}
				newSeq.add(sequence.get(i));
			}

			Diagram pre = sequence.get(resultIndexs.get(0)).getPreState();
			op.setAllState(pre);
			op.initSubOperations();
			sequence.clear();
			sequence.addAll(newSeq);
			sequence.add(resultIndexs.get(0), op);
		}

	}

	/**
	 * 根据主操作类型，寻找组操作
	 * 
	 * @param start
	 *            从序列第几个操作开始寻找
	 * @param target
	 *            寻找哪个组合操作
	 * @param resultIndexs
	 *            调用结束后存储了主操作在序列中的下标
	 */
	public void searchMains(int start, CompositeOperation target,
			List<Integer> resultIndexs) {
		resultIndexs.clear();
		// 主操作只有一个时
		if (target.getMainOperationsType().size() == 1) {
			Class<?> mainType = (Class<?>) target.getMainOperationsType()
					.toArray()[0];
			int i = matchTypeIndex(start, mainType);
			if (i != -1)
				resultIndexs.add(i);
			return;
		}
		// } else {// 多个主操作
		//
		Class<?> mainType1 = (Class<?>) target.getMainOperationsType()
				.toArray()[0];
		int i = matchTypeIndex(start, mainType1);
		if(i == -1)
			return;
		resultIndexs.add(i);
		target.searchOtherMains(sequence, resultIndexs);
		return;
	}

	/** 从start开始，匹配到该type类型的操作的下标 */
	private int matchTypeIndex(int start, Class<?> type) {
		for (int i = start; i < sequence.size(); i++) {
			Operation o = sequence.get(i);
			if (o.getClass().equals(type)) {
				return i;
			}
		}
		return -1;
	}
}

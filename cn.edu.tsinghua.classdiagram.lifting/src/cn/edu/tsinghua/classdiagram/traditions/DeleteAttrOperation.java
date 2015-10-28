package cn.edu.tsinghua.classdiagram.traditions;

import java.util.HashSet;
import java.util.Iterator;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.operation.AtomicOperation;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.util.StrLinker;

/**
 * 前提假定同一个类里没有重名attr
 * 
 * @author Crazy
 *
 */
public class DeleteAttrOperation extends AtomicOperation {
	private String className;
	private String attrName;

	public String getClassName() {
		return className;
	}

	public String getAttrName() {
		return attrName;
	}

	public DeleteAttrOperation(String className, String attrName) {
		this.className = className;
		this.attrName = attrName;
	}
	
	public DeleteAttrOperation(Diagram state, String className, String attrName) {
		this(className, attrName);
		setAllState(state);
	}

	@Override
	public Diagram execute() {
		Class c = curState.retrieveClass(className);
		if (c != null) {
			for (Iterator<Attribute> i = c.getAttributes().iterator();i.hasNext();) {
				Attribute a = i.next();
				// 名字空是非法的，自己写一定设置名字
				if (a.getName() == null || a.getName().equals(attrName))
					i.remove();
			}
		}
//		postState = (Diagram)curState.clone();
		return curState;
	}

	@Override
	protected void setRelatedElements() {
		relatedElements = new HashSet<String>();
		relatedElements.add(getReletedAttr());
	}
	
	private String getReletedAttr() {
		return StrLinker.linkAttr(className, attrName);
	}
	
	/**相关元素相同*/
//	public boolean elementsEquals(Operation o) {
//		if(o==null || !(o instanceof DeleteAttrOperation))
//			return false;
//		DeleteAttrOperation other = (DeleteAttrOperation)o;
//		//省去了验证null的过程
//		return other.attrName.equals(this.attrName) && other.className.equals(this.className);
//	}
}

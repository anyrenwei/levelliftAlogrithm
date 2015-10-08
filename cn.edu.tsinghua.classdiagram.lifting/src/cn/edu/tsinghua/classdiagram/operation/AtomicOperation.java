package cn.edu.tsinghua.classdiagram.operation;

import cn.edu.tsinghua.classdiagram.classdiagram.NamedElement;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;

public abstract class AtomicOperation extends Operation{

	private NamedElement element;

	/**
	 * @return the element
	 */
	public NamedElement getElement() {
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(NamedElement element) {
		this.element = element;
	}
	
}

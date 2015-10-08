package cn.edu.tsinghua.classdiagram.diagram;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.Method;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelFactory;

public class Diagram {
	/**
	 * 从名称到实际类的映射，名称作为唯一标识 按照emfstore，所有属性及方法都具有唯一id并被映射，这里做简化只映射了类
	 * 实际情况在使用属性和方法时也更多的是用到name而非id
	 * */
	private Map<String, Class> classes;

	public Diagram(Map<String, Class> classes) {
		this.classes = classes;
	}

	/** 添加成功返回true，如果已有同名返回false */
	public boolean addClass(Class c) {
		if (c == null || classes.containsKey(c.getName()))
			return false;
		classes.put(c.getName(), c);
		return true;
	}

	public Class retrieveClass(String name) {
		return classes.get(name);
	}

	@Override
	public Object clone() {
		//存放拷贝后的，由名称到类的映射
		HashMap<String, Class> mapping = new HashMap<String, Class>();
		// 拷贝类的成员变量
		for (Entry entry : classes.entrySet()) {
			Class oldClass = (Class) entry.getValue();
			String className = (String) entry.getKey();
			Class newClass = ModelFactory.eINSTANCE.createClass();
			newClass.setName(className);
			for (Attribute attr : oldClass.getAttributes()) {
				Attribute newAttr = ModelFactory.eINSTANCE.createAttribute();
				newAttr.setName(attr.getName());
				newClass.getAttributes().add(newAttr);
			}
			for (Method method : oldClass.getMethods()) {
				Method newMethod = ModelFactory.eINSTANCE.createMethod();
				newMethod.setName(method.getName());
				newClass.getMethods().add(newMethod);
			}
			mapping.put(newClass.getName(), newClass);
		}
		// 添加关联：父子关系
		for (Class oldClass : classes.values()) {
			Class newClass = mapping.get(oldClass.getName());
			if (oldClass.getSuper() != null)
				newClass.setSuper(mapping.get(oldClass.getSuper().getName()));
			for (Class oldChild : oldClass.getChildren()) {
				newClass.getChildren().add(mapping.get(oldChild.getName()));
			}
		}
		Diagram diagram = new Diagram(mapping);
		return diagram;
	}
}

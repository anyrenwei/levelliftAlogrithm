package cn.edu.tsinghua.classdiagram.util;

public class StrLinker {
	public static String linkAttr(String className, String attrName) {
		return className + ".a." + attrName;
	}
	
	public static String linkSuper(String className) {
		return className + ".s";
	}
}

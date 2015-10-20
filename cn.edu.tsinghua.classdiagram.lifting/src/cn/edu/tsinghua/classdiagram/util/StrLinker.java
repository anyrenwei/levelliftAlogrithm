package cn.edu.tsinghua.classdiagram.util;

public class StrLinker {
	
	public static String linkClass(String className)
	{
		return className+".c";
	}
	
	public static String linkAttr(String className, String attrName) {
		return className + ".a." + attrName;
	}
	
	public static String linkMeth(String className, String methName){
		
		return className + ".m." +methName;
	}
	
	public static String linkSuper(String className) {
		return className + ".s";
	}
	
	public static String linkRename(String className) {
		return className + ".rename";
	}
	
	public static String linkRenameAttribute(String className, String attrName){
		
		return className+"."+attrName+".rename";
	}
	
	public static String linkRenameMethod(String className, String methName){
		
		return className+"."+methName+".rename";
	}
	
}

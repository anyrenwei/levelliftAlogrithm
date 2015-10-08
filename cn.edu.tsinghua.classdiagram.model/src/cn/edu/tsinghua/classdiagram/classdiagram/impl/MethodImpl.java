/**
 */
package cn.edu.tsinghua.classdiagram.classdiagram.impl;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Method;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class MethodImpl extends NamedElementImpl implements Method {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.METHOD;
	}
	@Override
	public boolean equals(Object obj) {
		Method a = (Method) obj;
		return a.getName().equals(this.name);
	}
} //MethodImpl

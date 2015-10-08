/**
 */
package cn.edu.tsinghua.classdiagram.classdiagram.impl;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class AttributeImpl extends NamedElementImpl implements Attribute {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ATTRIBUTE;
	}
	
	@Override
	public boolean equals(Object obj) {
		Attribute a = (Attribute) obj;
		return a.getName().equals(this.name);
	}

} //AttributeImpl

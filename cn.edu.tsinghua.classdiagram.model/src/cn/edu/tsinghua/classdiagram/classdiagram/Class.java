/**
 */
package cn.edu.tsinghua.classdiagram.classdiagram;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cn.edu.tsinghua.classdiagram.classdiagram.Class#getSuper <em>Super</em>}</li>
 *   <li>{@link cn.edu.tsinghua.classdiagram.classdiagram.Class#getChildren <em>Children</em>}</li>
 *   <li>{@link cn.edu.tsinghua.classdiagram.classdiagram.Class#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link cn.edu.tsinghua.classdiagram.classdiagram.Class#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @see cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage#getClass_()
 * @model
 * @generated
 */
public interface Class extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Super</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link cn.edu.tsinghua.classdiagram.classdiagram.Class#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super</em>' reference.
	 * @see #setSuper(Class)
	 * @see cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage#getClass_Super()
	 * @see cn.edu.tsinghua.classdiagram.classdiagram.Class#getChildren
	 * @model opposite="children"
	 * @generated
	 */
	Class getSuper();

	/**
	 * Sets the value of the '{@link cn.edu.tsinghua.classdiagram.classdiagram.Class#getSuper <em>Super</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super</em>' reference.
	 * @see #getSuper()
	 * @generated
	 */
	void setSuper(Class value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link cn.edu.tsinghua.classdiagram.classdiagram.Class}.
	 * It is bidirectional and its opposite is '{@link cn.edu.tsinghua.classdiagram.classdiagram.Class#getSuper <em>Super</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage#getClass_Children()
	 * @see cn.edu.tsinghua.classdiagram.classdiagram.Class#getSuper
	 * @model opposite="super"
	 * @generated
	 */
	EList<Class> getChildren();

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link cn.edu.tsinghua.classdiagram.classdiagram.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage#getClass_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link cn.edu.tsinghua.classdiagram.classdiagram.Method}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage#getClass_Methods()
	 * @model containment="true"
	 * @generated
	 */
	EList<Method> getMethods();

} // Class

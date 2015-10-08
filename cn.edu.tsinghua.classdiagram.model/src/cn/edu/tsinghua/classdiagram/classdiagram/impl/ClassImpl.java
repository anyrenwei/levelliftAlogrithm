/**
 */
package cn.edu.tsinghua.classdiagram.classdiagram.impl;

import java.util.Collection;

import javax.annotation.Generated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.Method;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Class</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link cn.edu.tsinghua.classdiagram.classdiagram.impl.ClassImpl#getSuper
 * <em>Super</em>}</li>
 * <li>
 * {@link cn.edu.tsinghua.classdiagram.classdiagram.impl.ClassImpl#getChildren
 * <em>Children</em>}</li>
 * <li>
 * {@link cn.edu.tsinghua.classdiagram.classdiagram.impl.ClassImpl#getAttributes
 * <em>Attributes</em>}</li>
 * <li>
 * {@link cn.edu.tsinghua.classdiagram.classdiagram.impl.ClassImpl#getMethods
 * <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassImpl extends NamedElementImpl implements
		cn.edu.tsinghua.classdiagram.classdiagram.Class {
	/**
	 * The cached value of the '{@link #getSuper() <em>Super</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSuper()
	 * @generated
	 * @ordered
	 */
	protected cn.edu.tsinghua.classdiagram.classdiagram.Class super_;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<cn.edu.tsinghua.classdiagram.classdiagram.Class> children;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Attribute> attributes;

	/**
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<Method> methods;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.CLASS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public cn.edu.tsinghua.classdiagram.classdiagram.Class getSuper() {
		if (super_ != null && super_.eIsProxy()) {
			InternalEObject oldSuper = (InternalEObject) super_;
			super_ = (cn.edu.tsinghua.classdiagram.classdiagram.Class) eResolveProxy(oldSuper);
			if (super_ != oldSuper) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ModelPackage.CLASS__SUPER, oldSuper, super_));
			}
		}
		return super_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public cn.edu.tsinghua.classdiagram.classdiagram.Class basicGetSuper() {
		return super_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSuper(
			cn.edu.tsinghua.classdiagram.classdiagram.Class newSuper,
			NotificationChain msgs) {
		cn.edu.tsinghua.classdiagram.classdiagram.Class oldSuper = super_;
		super_ = newSuper;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, ModelPackage.CLASS__SUPER, oldSuper,
					newSuper);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSuper(
			cn.edu.tsinghua.classdiagram.classdiagram.Class newSuper) {
		if (newSuper != super_) {
			NotificationChain msgs = null;
			if (super_ != null)
				msgs = ((InternalEObject) super_).eInverseRemove(this,
						ModelPackage.CLASS__CHILDREN,
						cn.edu.tsinghua.classdiagram.classdiagram.Class.class,
						msgs);
			if (newSuper != null)
				msgs = ((InternalEObject) newSuper).eInverseAdd(this,
						ModelPackage.CLASS__CHILDREN,
						cn.edu.tsinghua.classdiagram.classdiagram.Class.class,
						msgs);
			msgs = basicSetSuper(newSuper, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelPackage.CLASS__SUPER, newSuper, newSuper));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<cn.edu.tsinghua.classdiagram.classdiagram.Class> getChildren() {
		if (children == null) {
			children = new EObjectWithInverseResolvingEList<cn.edu.tsinghua.classdiagram.classdiagram.Class>(
					cn.edu.tsinghua.classdiagram.classdiagram.Class.class,
					this, ModelPackage.CLASS__CHILDREN,
					ModelPackage.CLASS__SUPER);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Attribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentEList<Attribute>(
					Attribute.class, this, ModelPackage.CLASS__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Method> getMethods() {
		if (methods == null) {
			methods = new EObjectContainmentEList<Method>(Method.class, this,
					ModelPackage.CLASS__METHODS);
		}
		return methods;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ModelPackage.CLASS__SUPER:
			if (super_ != null)
				msgs = ((InternalEObject) super_).eInverseRemove(this,
						ModelPackage.CLASS__CHILDREN,
						cn.edu.tsinghua.classdiagram.classdiagram.Class.class,
						msgs);
			return basicSetSuper(
					(cn.edu.tsinghua.classdiagram.classdiagram.Class) otherEnd,
					msgs);
		case ModelPackage.CLASS__CHILDREN:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getChildren())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ModelPackage.CLASS__SUPER:
			return basicSetSuper(null, msgs);
		case ModelPackage.CLASS__CHILDREN:
			return ((InternalEList<?>) getChildren()).basicRemove(otherEnd,
					msgs);
		case ModelPackage.CLASS__ATTRIBUTES:
			return ((InternalEList<?>) getAttributes()).basicRemove(otherEnd,
					msgs);
		case ModelPackage.CLASS__METHODS:
			return ((InternalEList<?>) getMethods())
					.basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelPackage.CLASS__SUPER:
			if (resolve)
				return getSuper();
			return basicGetSuper();
		case ModelPackage.CLASS__CHILDREN:
			return getChildren();
		case ModelPackage.CLASS__ATTRIBUTES:
			return getAttributes();
		case ModelPackage.CLASS__METHODS:
			return getMethods();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ModelPackage.CLASS__SUPER:
			setSuper((cn.edu.tsinghua.classdiagram.classdiagram.Class) newValue);
			return;
		case ModelPackage.CLASS__CHILDREN:
			getChildren().clear();
			getChildren()
					.addAll((Collection<? extends cn.edu.tsinghua.classdiagram.classdiagram.Class>) newValue);
			return;
		case ModelPackage.CLASS__ATTRIBUTES:
			getAttributes().clear();
			getAttributes().addAll((Collection<? extends Attribute>) newValue);
			return;
		case ModelPackage.CLASS__METHODS:
			getMethods().clear();
			getMethods().addAll((Collection<? extends Method>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ModelPackage.CLASS__SUPER:
			setSuper((cn.edu.tsinghua.classdiagram.classdiagram.Class) null);
			return;
		case ModelPackage.CLASS__CHILDREN:
			getChildren().clear();
			return;
		case ModelPackage.CLASS__ATTRIBUTES:
			getAttributes().clear();
			return;
		case ModelPackage.CLASS__METHODS:
			getMethods().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ModelPackage.CLASS__SUPER:
			return super_ != null;
		case ModelPackage.CLASS__CHILDREN:
			return children != null && !children.isEmpty();
		case ModelPackage.CLASS__ATTRIBUTES:
			return attributes != null && !attributes.isEmpty();
		case ModelPackage.CLASS__METHODS:
			return methods != null && !methods.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	@Override
	public boolean equals(Object obj) {
		Class a = (Class) obj;
		return a.getName().equals(this.name);
	}
	
} // ClassImpl

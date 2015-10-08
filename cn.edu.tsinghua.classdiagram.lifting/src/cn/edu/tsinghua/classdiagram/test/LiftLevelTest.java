package cn.edu.tsinghua.classdiagram.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.edu.tsinghua.classdiagram.classdiagram.Attribute;
import cn.edu.tsinghua.classdiagram.classdiagram.Class;
import cn.edu.tsinghua.classdiagram.classdiagram.ModelFactory;
import cn.edu.tsinghua.classdiagram.diagram.Diagram;
import cn.edu.tsinghua.classdiagram.lifting.LevelLifting;
import cn.edu.tsinghua.classdiagram.operation.Operation;
import cn.edu.tsinghua.classdiagram.operation.implement.AddAttrOperation;
import cn.edu.tsinghua.classdiagram.operation.implement.DeleteAttrOperation;
import cn.edu.tsinghua.classdiagram.operation.implement.MoveAttrOperation;
import cn.edu.tsinghua.classdiagram.operation.implement.PullUpAttrOperation;
import cn.edu.tsinghua.classdiagram.operation.implement.PushDownAttrOperation;

public class LiftLevelTest {
	Diagram diagram;

	@Before
	public void setUp() throws Exception {
		System.out.println("SetUP");
		diagram = new Diagram(new HashMap<String, Class>());

		Class c1 = ModelFactory.eINSTANCE.createClass();
		c1.setName("c1");
		Class c2 = ModelFactory.eINSTANCE.createClass();
		c2.setName("c2");
		Class c3 = ModelFactory.eINSTANCE.createClass();
		c3.setName("c3");

		c2.setSuper(c1);
		c3.setSuper(c1);

		diagram.addClass(c1);
		diagram.addClass(c2);
		diagram.addClass(c3);
	}

	@Test
	public void testMove() {
		printSeperateLine();
		System.out.println("LiftLevelTest.testMove()");
		String attrName = "attr";
		String c1Name = "c1";
		String c2Name = "c2";
		
		Attribute a = ModelFactory.eINSTANCE.createAttribute();
		a.setName(attrName);
		
		diagram.retrieveClass(c2Name).getAttributes().add(a);

		// 设置操作序列sequence
		List<Operation> sequence = new ArrayList<Operation>();
		Operation o1 = new AddAttrOperation(diagram, c1Name, attrName);
		sequence.add(o1);
		o1.execute();
		Operation o2 = new DeleteAttrOperation(diagram, c2Name, attrName);
		sequence.add(o2);

		// 进行提升
		LevelLifting lifting = new LevelLifting(sequence);
		lifting.liftLevel();
		assertEquals(1, sequence.size());
		assertEquals(MoveAttrOperation.class, sequence.get(0).getClass());
	}
	
	/**
	 * c1作为父类，从c2,c3进行提升
	 */
	@Test
	public void testPullUp() {
		printSeperateLine();
		System.out.println("LiftLevelTest.testPullUp()");
		String attrName = "attr";
		String c1Name = "c1";
		String c2Name = "c2";
		String c3Name = "c3";

		Attribute a2 = ModelFactory.eINSTANCE.createAttribute();
		a2.setName(attrName);
		Attribute a3 = ModelFactory.eINSTANCE.createAttribute();
		a3.setName(attrName);
		diagram.retrieveClass(c2Name).getAttributes().add(a2);
		diagram.retrieveClass(c3Name).getAttributes().add(a3);

		// 设置操作序列sequence
		List<Operation> sequence = new ArrayList<Operation>();
		Operation o1 = new MoveAttrOperation(diagram, c3Name, c1Name, attrName);
		sequence.add(o1);
		o1.execute();
		Operation o2 = new DeleteAttrOperation(diagram, c2Name, attrName);
		sequence.add(o2);

		// 进行提升
		LevelLifting lifting = new LevelLifting(sequence);
		lifting.liftLevel();
		assertEquals(1, sequence.size());
		assertEquals(PullUpAttrOperation.class, sequence.get(0).getClass());
	}

	@Test
	public void testPushDown() {
		printSeperateLine();
		System.out.println("LiftLevelTest.testPushDown()");
		String attrName = "attr";
		String c1Name = "c1";
		String c2Name = "c2";
		String c3Name = "c3";

		Attribute a1 = ModelFactory.eINSTANCE.createAttribute();
		a1.setName(attrName);
		diagram.retrieveClass(c1Name).getAttributes().add(a1);

		// 设置操作序列sequence
		List<Operation> sequence = new ArrayList<Operation>();
		Operation o1 = new AddAttrOperation(diagram, c2Name, attrName);
		sequence.add(o1);
		o1.execute();
		Operation o2 = new MoveAttrOperation(diagram, c1Name, c3Name, attrName);
		sequence.add(o2);

		// 进行提升
		LevelLifting lifting = new LevelLifting(sequence);
		lifting.liftLevel();
		assertEquals(1, sequence.size());
		assertEquals(PushDownAttrOperation.class, sequence.get(0).getClass());

	}
	
	private void printSeperateLine() {
		System.out.println("****************************");
	}
	
	@After
	public void tearDown() throws Exception {
	}
}

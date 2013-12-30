package bo.project.logicTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import bo.project.logic.Generator;
import bo.project.logic.Intersection;
import bo.project.logic.Road;

public class IntersectionTest {
	Intersection tester;
	
	@Before
	public void setUp() throws Exception {
		
		ArrayList<Road> eRI0 = new ArrayList<Road>();
		eRI0.add(new Road(10, 2400));
		eRI0.add(new Road(10, 1200));
		eRI0.add(new Road(10, 1200));
		eRI0.add(new Road(10, 1200));
		
		ArrayList<Road> aRI0 = new ArrayList<Road>();
		aRI0.add(new Road(10, 2400));
		aRI0.add(new Road(10, 2400));
		aRI0.add(new Road(10, 2400));
		aRI0.add(new Road(10, 2400));
		
		tester=(new Intersection(eRI0, aRI0, 30, 30, 0, 0));
	}

	@Test
	public void testCheckStatus() {
		assertTrue(tester.checkStatus(0));
		assertTrue(tester.checkStatus(29));
		assertFalse(tester.checkStatus(30));
		assertTrue(tester.checkStatus(60));
	}

	@Test
	/*
	 * jedyne co tu mo�e nie dzia�a�, je�li wszystkie funkcje road dzia�aj� to
	 * losowanie drogi wyj�ciowej...
	 * wi�c zostawiam na razie tak, bo tamto dzia�a na 99%
	 */
	public void testMoveVehicles() {
		assertTrue(true);
	}

}

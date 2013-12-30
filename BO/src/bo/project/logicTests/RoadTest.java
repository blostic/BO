package bo.project.logicTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bo.project.logic.Road;
import bo.project.logic.Vehicle;

public class RoadTest {
	Road road;
	
	@Before
	public void setUp() throws Exception {
		road = new Road(10, 2200);
	}

	@Test
	public void testIsFull() {
		Assert.assertFalse(road.isFull());
		for(int i=0;i<9;++i){
			road.addVehicle(new Vehicle());
			Assert.assertFalse(road.isFull());
		}
		road.addVehicle(new Vehicle());
		Assert.assertTrue(road.isFull());
	}

	@Test
	public void testGetFirstWaitingVehicle() {
		Vehicle vehicle = new Vehicle();
		road.addVehicleFirst(vehicle);
		Assert.assertNull(road.getFirstWaitingVehicle());
		vehicle.markAsWaiting();
		Assert.assertNotNull(road.getFirstWaitingVehicle());
	}

	@Test
	public void testGetNumberOfWaiting() {
		for(int i=0;i<2;++i){
			Vehicle vehicle = new Vehicle();
			road.addVehicleFirst(vehicle);
		}
		for(int i=0;i<7;++i){
			Vehicle vehicle = new Vehicle();
			vehicle.markAsWaiting();
			road.addVehicleFirst(vehicle);
		}
		Assert.assertTrue(road.getNumberOfWaiting()==7);
	}

	@Test
	public void testMoveVehiclesOnRoad() {
		road.addVehicle(new Vehicle(8));
		road.addVehicle(new Vehicle(7));
		road.addVehicle(new Vehicle(4));
		road.addVehicle(new Vehicle(0));
		road.moveVehiclesOnRoad(1);
		Assert.assertTrue(road.getNumberOfWaiting()==0);
		road.moveVehiclesOnRoad(1);
		Assert.assertTrue(road.getNumberOfWaiting()==2);
		road.moveVehiclesOnRoad(1);
		Assert.assertTrue(road.getNumberOfWaiting()==2);
		road.moveVehiclesOnRoad(1);
		Assert.assertTrue(road.getNumberOfWaiting()==3);
		road.moveVehiclesOnRoad(2);
		Assert.assertTrue(road.getNumberOfWaiting()==3);
		road.moveVehiclesOnRoad(1);
		Assert.assertTrue(road.getNumberOfWaiting()==4);
	}

}

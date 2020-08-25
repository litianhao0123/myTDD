import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LockerRobotManagerTest {
    @Test
    public void should_get_the_first_locker_ticket_when_store_bag_by_manager_given_the_first_locker_has_the_more_available_capacity() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2),null);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag, locker1.pickUpBy(ticket));
    }

    @Test
    public void should_get_the_second_locker_ticket_when_store_bag_by_manager_given_the_second_locker_has_the_more_available_capacity() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2),null);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag, locker2.pickUpBy(ticket));
    }

    @Test
    public void should_get_the_first_locker_ticket_when_store_bag_by_manager_given_two_lockers_have_the_same_available_capacity() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2),null);
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag, locker1.pickUpBy(ticket));
    }

    @Test
    public void should_throw_exception_when_store_bag_by_manager_given_the_first_and_second_locker_are_full(){
        Locker locker1 = new Locker(1);
        locker1.store(new Bag());
        Locker locker2 = new Locker(1);
        locker2.store(new Bag());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2),null);
        Bag bag = new Bag();
        assertThrows(LockerFullException.class,()->lockerRobotManager.store(bag));
    }

    @Test
    public void should_store_the_bag_by_the_smart_robot_when_store_bag_by_robot_given_the_first_robot_is_smart_and_available(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        Locker locker4 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker3, locker4));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null,Arrays.asList(smartLockerRobot, primaryLockerRobot));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,smartLockerRobot.pickUpBy(ticket));
    }

    @Test
    public void should_store_the_bag_by_the_smart_robot_when_store_bag_by_robot_given_the_second_robot_is_smart_and_available(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        Locker locker4 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker3, locker4));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null,Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,smartLockerRobot.pickUpBy(ticket));
    }

    @Test
    public void should_store_the_bag_by_the_first_primary_robot_when_store_bag_by_robot_given_two_robots_are_primary_and_available(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        Locker locker4 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot1 = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        PrimaryLockerRobot primaryLockerRobot2 = new PrimaryLockerRobot(Arrays.asList(locker3, locker4));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null,Arrays.asList(primaryLockerRobot1, primaryLockerRobot2));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,primaryLockerRobot1.pickUpBy(ticket));
    }

    @Test
    public void should_store_the_bag_by_the_second_robot_when_store_bag_by_robot_given_the_first_robot_is_full(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        Locker locker4 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        smartLockerRobot.store(new Bag());
        smartLockerRobot.store(new Bag());
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker3, locker4));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null,Arrays.asList(smartLockerRobot, primaryLockerRobot));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,primaryLockerRobot.pickUpBy(ticket));
    }

    @Test
    public void should_store_the_bag_by_the_second_robot_when_store_bag_by_robot_given_two_robots_are_primary_and_the_first_robot_is_full(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        Locker locker4 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot1 = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        primaryLockerRobot1.store(new Bag());
        primaryLockerRobot1.store(new Bag());
        PrimaryLockerRobot primaryLockerRobot2 = new PrimaryLockerRobot(Arrays.asList(locker3, locker4));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null,Arrays.asList(primaryLockerRobot1, primaryLockerRobot2));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,primaryLockerRobot2.pickUpBy(ticket));
    }

    @Test
    public void should_throw_exception_when_store_bag_by_robot_given_two_robots_are_full(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        Locker locker4 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot1 = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        primaryLockerRobot1.store(new Bag());
        primaryLockerRobot1.store(new Bag());
        PrimaryLockerRobot primaryLockerRobot2 = new PrimaryLockerRobot(Arrays.asList(locker3, locker4));
        primaryLockerRobot2.store(new Bag());
        primaryLockerRobot2.store(new Bag());
        LockerRobotManager lockerRobotManager = new LockerRobotManager(null,Arrays.asList(primaryLockerRobot1, primaryLockerRobot2));
        Bag bag = new Bag();
        assertThrows(LockerFullException.class,()->lockerRobotManager.store(bag));
    }

    @Test
    public void should_store_the_bag_by_smart_robot_when_store_bag_by_robot_given_all_are_available(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        Locker locker4 = new Locker(1);
        Locker locker5 = new Locker(1);
        Locker locker6 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker3, locker4));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker5,locker6),Arrays.asList(primaryLockerRobot, smartLockerRobot));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,smartLockerRobot.pickUpBy(ticket));
    }
}

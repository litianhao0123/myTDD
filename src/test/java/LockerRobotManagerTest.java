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
}

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SmartLockerRobotTest {
    @Test
    public void should_get_the_first_locker_ticket_when_store_bag_given_the_first_locker_has_the_more_available_capacity(){
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.store(bag);
        assertNotNull(ticket);
        assertEquals(bag, locker1.pickUpBy(ticket));
    }

    @Test
    public void should_get_the_second_locker_ticket_when_store_bag_given_the_second_locker_has_the_more_available_capacity(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,locker2.pickUpBy(ticket));
    }

    @Test
    public void should_get_the_first_locker_ticket_when_store_bag_given_two_lockers_have_the_same_available_capacity(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.store(bag);
        assertNotNull(ticket);
        assertEquals(bag,locker1.pickUpBy(ticket));
    }

    @Test
    public void should_throw_exception_when_store_bag_given_the_first_and_second_locker_are_full(){
        Locker locker1 = new Locker(1);
        locker1.store(new Bag());
        Locker locker2 = new Locker(1);
        locker2.store(new Bag());
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        assertThrows(LockerFullException.class,()->smartLockerRobot.store(bag));
    }

    @Test
    public void should_get_bag_when_pick_up_with_the_right_ticket(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.store(bag);
        assertEquals(bag, smartLockerRobot.pickUpBy(ticket));
    }

    @Test
    public void should_throw_exception_when_pick_up_with_the_wrong_ticket(){
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.store(bag);
        assertThrows(WrongTicketException.class,()->smartLockerRobot.pickUpBy(new Ticket()));
    }

}

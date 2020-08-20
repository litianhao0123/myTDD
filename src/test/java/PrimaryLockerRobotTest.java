import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryLockerRobotTest {
    @Test
    public void should_get_the_first_locker_ticket_when_store_bag_given_two_available_locker(){
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        Robot robot = new Robot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = robot.store(bag);
        assertNotNull(ticket);
        assertEquals(bag, locker1.pickUpBy(ticket));
    }

    @Test
    public void should_get_the_second_locker_ticket_when_store_bag_given_only_the_second_locker_available(){
        Locker locker1 = new Locker(1);
        Bag bag1 = new Bag();
        locker1.store(bag1);
        Locker locker2 = new Locker(2);
        Robot robot = new Robot(Arrays.asList(locker1, locker2));
        Bag bag2 = new Bag();
        Ticket ticket = robot.store(bag2);
        assertNotNull(ticket);
        assertEquals(bag2, locker2.pickUpBy(ticket));
    }

    @Test
    public void should_throw_exception_when_store_bag_given_all_lockers_unavailable(){
        Locker locker1 = new Locker(1);
        Bag bag1 = new Bag();
        locker1.store(bag1);
        Locker locker2 = new Locker(1);
        Bag bag2 = new Bag();
        locker2.store(bag2);
        Robot robot = new Robot(Arrays.asList(locker1, locker2));
        Bag bag3 = new Bag();
        assertThrows(LockerFullException.class,()->robot.store(bag3));
    }
}

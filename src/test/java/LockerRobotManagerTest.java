import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerRobotManagerTest {
    @Test
    public void should_get_the_first_locker_ticket_when_store_bag_by_manager_given_the_first_locker_has_the_more_available_capacity() {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag, locker1.pickUpBy(ticket));
    }

    @Test
    public void should_get_the_second_locker_ticket_when_store_bag_by_manager_given_the_second_locker_has_the_more_available_capacity() {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.store(bag);
        assertNotNull(ticket);
        assertEquals(bag, locker2.pickUpBy(ticket));
    }
}

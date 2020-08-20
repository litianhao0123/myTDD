import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}

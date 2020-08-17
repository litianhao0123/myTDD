import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TDDtest {

    @Test
    public void should_get_a_ticket_when_storing_a_bag_in_locker_of_one_capacity(){
        Locker locker = new Locker(1);
        Bag bag = new Bag();
        Ticket ticket = locker.store(bag);
        assertNotNull(ticket);
    }
}

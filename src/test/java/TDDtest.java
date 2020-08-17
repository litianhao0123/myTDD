import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TDDtest {

    @Test
    public void should_get_a_ticket_when_storing_a_bag_in_locker_of_one_available_capacity(){
        Locker locker = new Locker(1);
        Bag bag = new Bag();
        Ticket ticket = locker.store(bag);
        assertNotNull(ticket);
    }

    @Test
    public void should_throw_exception_when_storing_a_bag_in_locker_of_none_available_capacity(){
        Locker locker = new Locker(1);
        Bag firstBag = new Bag();
        Ticket firstTicket = locker.store(firstBag);
        Bag secondBag = new Bag();
        assertThrows(LockerFullException.class,()->locker.store(secondBag));
    }
}

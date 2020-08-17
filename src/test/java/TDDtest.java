import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void should_get_the_right_bag_when_picking_up_with_the_right_ticket_in_locker_of_two_capacity(){
        Locker locker = new Locker(2);
        Bag firstBag = new Bag();
        Ticket firstTicket = locker.store(firstBag);
        Bag secondBag = new Bag();
        Ticket secondTicket = locker.store(secondBag);
        assertEquals(secondBag,locker.pickUpBy(secondTicket));
    }

    @Test
    public void should_throw_exception_when_picking_up_with_the_wrong_ticket(){
        Locker locker = new Locker(1);
        Bag bag = new Bag();
        Ticket rightTicket = locker.store(bag);
        Ticket wrongTicket = new Ticket();
        assertThrows(WrongTicketException.class,()->locker.pickUpBy(wrongTicket));
    }
}

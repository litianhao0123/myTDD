import java.util.List;

public class PrimaryLockerRobot extends Robot{

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }


    public Ticket store(Bag bag) {
        Locker selectedLocker = getLockers().stream()
                .filter(locker -> !locker.isFull())
                .findFirst()
                .orElseThrow(LockerFullException::new);
        return selectedLocker.store(bag);
    }

    public Bag pickUpBy(Ticket ticket) {
        Locker usedLocker = getLockers().stream()
                .filter(locker -> !locker.invalidTicket(ticket))
                .findFirst()
                .orElseThrow(WrongTicketException::new);
        return usedLocker.pickUpBy(ticket);
    }
}

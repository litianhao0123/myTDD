import java.util.List;

public class SmartLockerRobot extends Robot{

    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    public Ticket store(Bag bag) {
        Locker maxAvailableLocker = getLockers().stream()
                .sorted().findFirst()
                .orElseThrow(LockerFullException::new);
        return maxAvailableLocker.store(bag);
    }

    public Bag pickUpBy(Ticket ticket) {
        Locker usedLocker = getLockers().stream()
                .filter(locker -> !locker.invalidTicket(ticket))
                .findFirst()
                .orElseThrow(WrongTicketException::new);
        return usedLocker.pickUpBy(ticket);
    }
}

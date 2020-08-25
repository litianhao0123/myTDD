import java.util.List;
import java.util.Optional;

public class SmartLockerRobot {

    private List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        Optional<Locker> maxAvailableLocker = lockers.stream().sorted().findFirst();
        if(maxAvailableLocker.isPresent()){
            return maxAvailableLocker.get().store(bag);
        }else{
            throw new LockerFullException();
        }
    }

    public Bag pickUpBy(Ticket ticket) {
        Optional<Locker> usedLocker = lockers.stream().filter(locker -> !locker.invalidTicket(ticket)).findFirst();
        if(usedLocker.isPresent()){
            return usedLocker.get().pickUpBy(ticket);
        }else{
            throw new WrongTicketException();
        }
    }
}

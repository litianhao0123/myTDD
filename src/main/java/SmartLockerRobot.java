import java.util.List;
import java.util.Optional;

public class SmartLockerRobot extends Robot{

    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    public Ticket store(Bag bag) {
        Optional<Locker> maxAvailableLocker = getLockers().stream().sorted().findFirst();
        if(maxAvailableLocker.isPresent()){
            return maxAvailableLocker.get().store(bag);
        }else{
            throw new LockerFullException();
        }
    }

    public Bag pickUpBy(Ticket ticket) {
        Optional<Locker> usedLocker = getLockers().stream().filter(locker -> !locker.invalidTicket(ticket)).findFirst();
        if(usedLocker.isPresent()){
            return usedLocker.get().pickUpBy(ticket);
        }else{
            throw new WrongTicketException();
        }
    }
}

import java.util.List;
import java.util.Optional;

public class PrimaryLockerRobot extends Robot{

    public PrimaryLockerRobot(List<Locker> lockers) {
        super(lockers);
    }


    public Ticket store(Bag bag) {
        Optional<Locker> selectedLocker = getLockers().stream().filter(locker -> !locker.isFull()).findFirst();
        if(selectedLocker.isPresent()){
            return selectedLocker.get().store(bag);
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

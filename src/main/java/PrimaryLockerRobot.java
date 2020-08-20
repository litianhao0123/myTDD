import java.util.List;

public class PrimaryLockerRobot {

    private List<Locker> lockers;

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }


    public Ticket store(Bag bag) {
        for(Locker locker : lockers){
            if(!locker.isFull()){
                return locker.store(bag);
            }
        }
        throw new LockerFullException();
    }

    public Bag pickUpBy(Ticket ticket) {
        for(Locker locker : lockers){
            if(!locker.invalidTicket(ticket)){
                return locker.pickUpBy(ticket);
            }
        }
        throw new WrongTicketException();
    }
}

import java.util.List;

public class SmartLockerRobot {

    private List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        int maxCapacity = 0;
        Locker maxCapacityLocker = null;
        for(Locker locker : lockers){
            if(locker.availableCapacity()>maxCapacity){
                maxCapacity = locker.availableCapacity();
                maxCapacityLocker = locker;
            }
        }
        if(maxCapacity==0){
            throw new LockerFullException();
        }
        return maxCapacityLocker.store(bag);
    }
}

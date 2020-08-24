import java.util.List;

public class LockerRobotManager {

    private List<Locker> lockers;

    public LockerRobotManager(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        int maxAvailableCapacity = 0;
        Locker maxAvailableCapacityLocker = null;
        for(Locker locker : lockers){
            if(locker.availableCapacity()>maxAvailableCapacity){
                maxAvailableCapacity = locker.availableCapacity();
                maxAvailableCapacityLocker = locker;
            }
        }
        return maxAvailableCapacityLocker.store(bag);
    }
}

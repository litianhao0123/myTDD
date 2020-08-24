import java.util.List;

public class LockerRobotManager {

    private List<Object> lockerRobotManagers;
    private List<Locker> lockers;

    public LockerRobotManager(List<Locker> lockers, List<Object> lockerRobotManagers) {
        this.lockerRobotManagers = lockerRobotManagers;
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        if(lockerRobotManagers!=null){
            return ((SmartLockerRobot)lockerRobotManagers.get(0)).store(bag);
        }
        int maxAvailableCapacity = 0;
        Locker maxAvailableCapacityLocker = null;
        for(Locker locker : lockers){
            if(locker.availableCapacity()>maxAvailableCapacity){
                maxAvailableCapacity = locker.availableCapacity();
                maxAvailableCapacityLocker = locker;
            }
        }
        if(maxAvailableCapacity==0){
            throw new LockerFullException();
        }
        return maxAvailableCapacityLocker.store(bag);
    }
}

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
            for(Object robot : lockerRobotManagers){
                if(robot.getClass().equals(SmartLockerRobot.class)){
                    return ((SmartLockerRobot) robot).store(bag);
                }
            }
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

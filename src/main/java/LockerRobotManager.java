import java.util.List;

public class LockerRobotManager extends SmartLockerRobot{

    private List<Object> lockerRobotManagers;

    public LockerRobotManager(List<Locker> lockers, List<Object> lockerRobotManagers) {
        super(lockers);
        this.lockerRobotManagers = lockerRobotManagers;
    }

    public Ticket store(Bag bag) {
        if(lockerRobotManagers!=null){
            for(Object robot : lockerRobotManagers){
                if(robot.getClass().equals(SmartLockerRobot.class)){
                    return ((SmartLockerRobot) robot).store(bag);
                }
            }
            for(Object robot : lockerRobotManagers){
                if(robot.getClass().equals(PrimaryLockerRobot.class)){
                    return ((PrimaryLockerRobot) robot).store(bag);
                }
            }
        }
        return super.store(bag);
    }
}

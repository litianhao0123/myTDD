import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LockerRobotManager extends SmartLockerRobot{

    private List<Robot> lockerRobotManagers;

    public LockerRobotManager(List<Locker> lockers, List<Robot> lockerRobotManagers) {
        super(lockers);
        this.lockerRobotManagers = lockerRobotManagers;
    }

    public Ticket store(Bag bag) {
        if(lockerRobotManagers!=null){
            return storeWith(bag);
        }
        return super.store(bag);
    }

    private Ticket storeWith(Bag bag){
        if(lockerRobotManagers.stream().noneMatch(Robot::isAvailable)){
            throw new LockerFullException();
        }
        Optional<Robot> selectedSmartRobot = lockerRobotManagers.stream()
                .filter(robot -> robot.isAvailable()&&robot.getClass().equals(SmartLockerRobot.class))
                .findFirst();
        if(selectedSmartRobot.isPresent()){
            return ((SmartLockerRobot)selectedSmartRobot.get()).store(bag);
        }
        Optional<Robot> selectedPrimaryRobot = lockerRobotManagers.stream()
                .filter(robot -> robot.isAvailable()&&robot.getClass().equals(PrimaryLockerRobot.class))
                .findFirst();
        return ((PrimaryLockerRobot)selectedPrimaryRobot.get()).store(bag);
    }
}

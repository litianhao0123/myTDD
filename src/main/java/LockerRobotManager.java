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
        if(lockerRobotManagers==null) return super.store(bag);
        Ticket ticket = storeWith(bag);
        if(ticket==null){
            return super.store(bag);
        }
        return ticket;
    }

    private Ticket storeWith(Bag bag){
        if(lockerRobotManagers.stream().noneMatch(Robot::isAvailable)){
            if(getLockers()==null) throw new LockerFullException();
            return null;
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

    public Bag pickUpBy(Ticket ticket) {
        Optional<Locker> usedLocker = getLockers().stream().filter(locker -> !locker.invalidTicket(ticket)).findFirst();
        if(usedLocker.isPresent()){
            return usedLocker.get().pickUpBy(ticket);
        }else{
            throw new WrongTicketException();
        }
    }
}

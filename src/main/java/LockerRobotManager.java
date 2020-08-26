import java.util.List;
import java.util.Optional;

public class LockerRobotManager extends SmartLockerRobot{

    private List<Robot> managedRobots;

    public LockerRobotManager(List<Locker> lockers, List<Robot> managedRobots) {
        super(lockers);
        this.managedRobots = managedRobots;
    }

    public Ticket store(Bag bag) {
        if(managedRobots ==null) return super.store(bag);
        return storeTryWithRobotFirst(bag);
    }

    private Ticket storeTryWithRobotFirst(Bag bag) {
        Robot selectedRobot = managedRobots.stream()
                .sorted().filter(Robot::isAvailable).findFirst().orElse(null);
        if(selectedRobot==null&&getLockers()==null) throw new LockerFullException();
        if(selectedRobot==null) return super.store(bag);
        return selectedRobot.store(bag);
    }

    public Bag pickUpBy(Ticket ticket) {
        Locker usedLocker = getLockers().stream()
                .filter(locker -> !locker.invalidTicket(ticket))
                .findFirst()
                .orElseThrow(WrongTicketException::new);
        return usedLocker.pickUpBy(ticket);
    }
}

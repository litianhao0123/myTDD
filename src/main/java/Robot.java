import java.util.List;

public class Robot implements Comparable<Robot>{
    private List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    public boolean isAvailable(){
        return lockers.stream().mapToInt(Locker::availableCapacity).sum()!=0;
    }

    public Ticket store(Bag bag){
        return store(bag);
    }

    @Override
    public int compareTo(Robot o) {
        if(this instanceof SmartLockerRobot && o instanceof PrimaryLockerRobot) return -1;
        else if(this.getClass()==o.getClass()) return 0;
        else return 1;
    }
}

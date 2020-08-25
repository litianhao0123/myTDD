import java.util.List;

public class Robot{
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

}

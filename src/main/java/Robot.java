import java.util.List;

public class Robot {

    private List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }


    public Ticket store(Bag bag) {
        for(Locker locker : lockers){
            if(!locker.isUnAvailable()){
                return locker.store(bag);
            }
        }
        return null;
    }
}

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket,Bag> bagPool = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if(isUnAvailable()){
            throw new LockerFullException();
        }
        Ticket ticket = new Ticket();
        bagPool.put(ticket,bag);
        return ticket;
    }

    private boolean isUnAvailable() {
        return bagPool.size()>=capacity;
    }

    public Bag pickUpBy(Ticket ticket) {
        if(notHasBag(ticket)){
            throw new WrongTicketException();
        }
        Bag bag = bagPool.get(ticket);
        bagPool.remove(ticket);
        return bag;
    }

    private boolean notHasBag(Ticket ticket) {
        return bagPool.containsKey(ticket);
    }
}

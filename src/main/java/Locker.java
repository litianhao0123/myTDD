import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket,Bag> bagPool = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if(isAvailable()){
            Ticket ticket = new Ticket();
            bagPool.put(ticket,bag);
            return ticket;
        }
        throw new LockerFullException();
    }

    private boolean isAvailable() {
        return bagPool.size()<capacity;
    }

    public Bag pickUpBy(Ticket ticket) {
        if(hasBag(ticket)){
            Bag bag = bagPool.get(ticket);
            bagPool.remove(ticket);
            return bag;
        }
        throw new WrongTicketException();
    }

    private boolean hasBag(Ticket ticket) {
        return bagPool.containsKey(ticket);
    }
}

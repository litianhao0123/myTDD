import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket,Bag> bagPool = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if(bagPool.size()<capacity){
            Ticket ticket = new Ticket();
            bagPool.put(ticket,bag);
            return ticket;
        }
        throw new LockerFullException();
    }

    public Bag pickUpBy(Ticket ticket) {
        if(bagPool.containsKey(ticket)){
            Bag bag = bagPool.get(ticket);
            bagPool.remove(ticket);
            return bag;
        }
        throw new WrongTicketException();
    }
}

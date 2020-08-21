import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket,Bag> bagPool = new HashMap<>();

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if(isFull()){
            throw new LockerFullException();
        }
        Ticket ticket = new Ticket();
        bagPool.put(ticket,bag);
        return ticket;
    }

    public boolean isFull() {
        return bagPool.size()>=capacity;
    }

    public Bag pickUpBy(Ticket ticket) {
        if(invalidTicket(ticket)){
            throw new WrongTicketException();
        }
        Bag bag = bagPool.get(ticket);
        bagPool.remove(ticket);
        return bag;
    }

    public boolean invalidTicket(Ticket ticket) {
        return !bagPool.containsKey(ticket);
    }

    public int availableCapacity(){
        return capacity-bagPool.size();
    }
}

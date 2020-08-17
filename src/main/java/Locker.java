public class Locker {

    private int capacity;
    private int count;

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if(++count<=capacity){
            return new Ticket();
        }
        throw new LockerFullException();
    }
}

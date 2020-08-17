public class Locker {

    private int capacity;

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        return new Ticket();
    }
}

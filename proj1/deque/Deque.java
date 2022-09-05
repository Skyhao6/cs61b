package deque;

public interface Deque<T> {
    public void addFirst(T x);
    public void addLast(T x);
    public T removeLast();
    public T removeFirst();
    public int size();
    public T get(int index);
    public void printDeque();
    default boolean isEmpty() {
        if(size() == 0)
            return true;
        return false;
    }

}

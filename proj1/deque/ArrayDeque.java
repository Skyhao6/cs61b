package deque;

public class ArrayDeque<T> implements Deque<T> {

    private int pre;
    private int next;
    private T[] array;
    private int capacity;
    private int size;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        capacity = array.length;
        pre = array.length - 1;
        next = 0;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if(size == array.length)
            resize(array.length * 2);
        array[pre] = x;
        size++;
        pre = pre == 0 ? array.length - 1 : pre - 1;
    }
    @Override
    public void addLast(T x) {
        if(size == array.length) {
            resize(array.length * 2);
        }
        array[next] = x;
        size++;
        next = (next + 1) % array.length;
    }
    @Override
    public T removeLast() {
        if(size == 0)
            return null;
        next = next == 0 ? array.length - 1 : next - 1;
        T ans = array[next];
        array[next] = null;
        size--;
        if(array.length >= 16 && size < array.length / 4)
            resize(array.length / 2);
        return ans;
    }
    @Override
    public T removeFirst() {
        if(size == 0)
            return null;
        pre = (pre + 1) % array.length;
        T ans = array[pre];
        array[pre] = null;
        size--;
        if(array.length >= 16 && size < array.length / 4)
            resize(array.length / 2);
        return ans;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public T get(int index) {
        if(index >= size)
            return null;
        return array[(pre + 1 + index) % array.length];
    }
    @Override
    public void printDeque() {
        for(int i = (pre + 1) % array.length; i != next - 1; i = (i + 1) % array.length) {
            System.out.print(array[i] + " ");
        }
        System.out.print(array[next - 1]);
    }
    /*@Override
    public boolean isEmpty() {
        return pre==next;
    }*/
    public void resize(int capacity) {

        T[] a = (T[]) new Object[capacity];
        for(int i = 1; i <= size; i++) {
            a[i] = array[(++pre) % array.length];
        }
        this.capacity = capacity;
        pre = 0;
        next = size + 1;
        array = a;
    }
}

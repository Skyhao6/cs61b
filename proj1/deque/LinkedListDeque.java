package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private ListNode sentinel;
    private int size;

    public class ListNode {
        public ListNode pre;
        public ListNode next;
        public T item;
        public ListNode(ListNode pre, ListNode next, T item) {
            this.pre = pre;
            this.next = next;
            this.item = item;
        }
    }

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new ListNode(null, null, null);
        sentinel.next = new ListNode(sentinel, sentinel, item);
        size = 1;
    }
    @Override
    public void addFirst(T item) {
        if(isEmpty()) {
            ListNode ln = new ListNode(sentinel, sentinel, item);
            sentinel.next = ln;
            sentinel.pre = ln;
            size = 1;
            return;
        }

        ListNode temp = sentinel.next;
        ListNode cur = new ListNode(sentinel.next, temp, item);
        cur.pre = sentinel;
        temp.pre = cur;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        if(isEmpty()) {
            ListNode ln = new ListNode(sentinel, sentinel, item);
            sentinel.next = ln;
            sentinel.pre = ln;
            size = 1;
            return;
        }
        ListNode temp = sentinel.pre;
        ListNode cur = new ListNode(temp, sentinel, item);
        temp.next = cur;
        sentinel.pre = cur;
        size += 1;
    }
    /*@Override
    public boolean isEmpty() {
        if(size() == 0)
            return true;
        return false;
    }*/
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        ListNode temp = sentinel.next;
        while(temp.next != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
    }
    @Override
    public T removeFirst() {
        if(isEmpty())
            return null;
        T ans = null;
        if(size() == 1) {
            ans = sentinel.next.item;
            sentinel.next = null;
            sentinel.pre = null;
            size = 0;
            return ans;
        }
        ListNode temp = sentinel.next;
        sentinel.next = temp.next;
        temp.next.pre = sentinel;
        ans = temp.item;
        temp.pre = null;
        temp.next = null;
        size -= 1;
        return ans;
    }
    @Override
    public T removeLast() {
        if(isEmpty())
            return null;
        T ans = null;
        if(size() == 1) {
            ans = sentinel.next.item;
            sentinel.next = null;
            sentinel.pre = null;
            size = 0;
            return ans;
        }
        ListNode temp = sentinel.pre;
        temp.pre.next = sentinel;
        sentinel.pre = temp.pre;
        ans = temp.item;
        temp.pre = null;
        temp.next = null;
        return ans;
    }
    @Override
    public T get(int index) {
        ListNode temp = sentinel.next;
        int i = 0;
        T ans = null;
        while(i < index && temp.next != sentinel) {
            temp = temp.next;
            i++;
        }
        ans = temp.item;
        return ans;
    }
    /*public T getRecursive(int index) {
        int i = 0;
        if(i == index)
            return
    }*/
    /*public Iterator<T> iterator() {
        return iterator();
    }

    public boolean equals(Object o) {
        return false;
    }*/
}
public class LinkedListDeque<T> {

    private class Deque {

        public T item;
        public Deque front;
        public Deque next;

        public Deque(T i, Deque f, Deque n) {
            item = i;
            front = f;
            next = n;
        }
    }

    private Deque sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Deque(null,null,null);
        sentinel.front = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Deque(null,null,null);
        sentinel.front = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for(int i = 0;i < other.size;i++) {
            addLast((T) get(i));
        }
    }


    public void addFirst(T item) {
        sentinel.next = new Deque(item,sentinel,sentinel.next);
        sentinel.next.next.front = sentinel.next;
        size += 1;
    }


    public void addLast(T item) {
        sentinel.front = new Deque(item, sentinel.front, sentinel);
        sentinel.front.front.next = sentinel.front;
        size += 1;
    }


    public boolean isEmpty() {
        return (size == 0);
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        Deque d = sentinel;
        while(d.next != sentinel) {
            System.out.print(d.next.item);
            System.out.print(" ");
            d = d.next;
        }
        System.out.print("\n");
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Deque d = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.front = sentinel;
        size -= 1;
        return d.item;
    }


    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Deque d = sentinel.front;
        sentinel.front = sentinel.front.front;
        sentinel.front.next = sentinel;
        size -= 1;
        return d.item;
    }


    public T get(int index) {
        if (size <= index) return null;
        Deque d = sentinel;
        while (true) {
            d = d.next;
            if(index == 0){
                return d.item;
            }
            index --;
        }
    }


    public T getRecursive(int index) {
        if (size <= index) return null;
        Deque d = Recursive(sentinel, index);
        return d.item;
    }

    private Deque Recursive(Deque d, int index) {
        if (index == 0) return d.next;
        else return Recursive(d.next, index - 1);
    }
}
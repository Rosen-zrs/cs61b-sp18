public class ArrayDeque<T> {
    private int size;
    private int front, back;
    private T[] sentinel;
    private int flag = 0;

    public ArrayDeque() {
        sentinel = (T[]) new Object[8];
        size = 0;
        front = 1;
        back = -1;
    } 

    public ArrayDeque(ArrayDeque other) {
        sentinel = (T[]) new Object[other.sentinel.length];
        System.arraycopy(sentinel, 0, other.sentinel, 0, other.sentinel.length);
        front = other.front;
        back = other.back;
    }

    private void resize(int x) {
        T[] b = (T[]) new Object[x];
        if (x > sentinel.length) {
            if (front < back) {        
                System.arraycopy(sentinel, front, b, 0, size);
                front = 0;
                back = size - 1;
                sentinel = b;
            }else {
                System.arraycopy(sentinel, front, b, front + x/2, sentinel.length - front);
                System.arraycopy(sentinel, 0, b, 0, back + 1);
                front += x /2;
                sentinel = b;
            }
        }else {
            if (front < back) {        
                System.arraycopy(sentinel, front, b, 0, size);
                front = 0;
                back = size - 1;
                sentinel = b;
            }else {
                System.arraycopy(sentinel, front, b, front - x, sentinel.length - front);
                System.arraycopy(sentinel, 0, b, 0, back + 1);
                front -= x;
                sentinel = b;
            }
        }
    }

    public void addFirst(T item) {
        if (flag == 0) {
            back = 0;
            flag = 1;
        }
        front = (sentinel.length + front - 1) % sentinel.length;
        sentinel[front] = item;
        size += 1;
        if (size == sentinel.length - 3) {
            resize(2 * sentinel.length);
        }
    }

    public void addLast(T item) {
        if (flag == 0) {
            front = 0;
            flag = 1;
        }
        back = (sentinel.length + back + 1) % sentinel.length;
        sentinel[back] = item;
        size += 1;
        if (size == sentinel.length - 3) {
            resize(2 * sentinel.length);
        }
    }

    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        int a = front;
        if (front > back) {
            for (int i = 0; i < sentinel.length - front; i++) {
                System.out.print(sentinel[a++] + " ");
            }
            for (int i = 0; i < back + 1; i++) {
                System.out.print(sentinel[i] + " ");
            }
        }else {
            for (int i = front; i < back + 1; i++) {
                System.out.print(sentinel[i] + " ");
            }
        }
        
        System.out.print('\n');
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T b = sentinel[front];
        sentinel[front] = null;
        front = (front + 1) % sentinel.length;
        size -= 1;
        if (size == sentinel.length / 4 && sentinel.length >= 16) {
            resize(sentinel.length / 2);
        }
        return b;
    }

    public T removeLast() {
        if (size == 0){return null;}
        T b = sentinel[back];
        sentinel[back] = null;
        back = (sentinel.length + back - 1) % sentinel.length;
        size -= 1;
        if (size == sentinel.length / 4 && sentinel.length >= 16) {
            resize(sentinel.length / 2);
        }
        return b;
    }

    public T get(int index) {
        if (index >= size | flag == 0) {
            return null;
        }
        return sentinel[(front + index) % sentinel.length];
    }
}
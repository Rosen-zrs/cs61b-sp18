public class ArrayDeque<T>{

    private int size;
    private int front,back;
    private T[] sentinel;

    public ArrayDeque(){
        sentinel = (T[])new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    public ArrayDeque(ArrayDeque other){
        sentinel = (T[]) new Object[other.sentinel.length];
        System.arraycopy(sentinel, 0, other.sentinel, 0, other.sentinel.length);
        front = other.front;
        back = other.back;
    }

    private void resize(int x){
        T[] b = (T[]) new Object[x];
        if(front < back){        
            System.arraycopy(sentinel, 0, b, 0, size);
            sentinel = b;
        }
        else{
            System.arraycopy(sentinel, front + x/2 , b, front + x/2, sentinel.length - front);
            System.arraycopy(sentinel, 0, b, 0, back);
            sentinel = b;
        }
    }

    public void addFirst(T item){
        sentinel[front] = item;
        front = (sentinel.length + front - 1) % sentinel.length;
        size += 1;
        if(size == sentinel.length - 3)
        {
            resize(2 * sentinel.length);
        }
    }

    public void addLast(T item){
        sentinel[back] = item;
        back = back + 1;
        size += 1;
        if(size == sentinel.length - 3){
            resize(2 * sentinel.length);
        }
    }

    public boolean isEmpty(){
        return (size == 0);
    }


    public int size(){
        return size;
    }


    public void printDeque(){
        for(int i = 0;i<size;i++){
            System.out.print(sentinel[i] + " ");
        }
        System.out.print('\n');
    }


    public T removeFirst(){
        T b = sentinel[front];
        sentinel[front] = null;
        front += 1;
        size -= 1;
        return b;
    }


    public T removeLast(){
        T b = sentinel[back];
        sentinel[back] = null;
        back -= 1;
        size -= 1;
        return b;
    }


    public T get(int index){
        return sentinel[index];
    }

}
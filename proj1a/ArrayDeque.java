public class ArrayDeque<T>{

    private int size;
    private int front,back;
    private T[] sentinel;

    //构造函数
    public ArrayDeque(){
        sentinel = (T[])new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    //深层复制构造函数
    public ArrayDeque(ArrayDeque other){
        sentinel = (T[]) new Object[other.length];
        System.arraycopy(sentinel, 0, b, 0, other.length);
        front = other.front;
        back = other.back;
    }

    //调整双头数组的大小
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

    //在双头数组前添加元素
    public void addFirst(T item){
        sentinel[front] = item;
        front = (sentinel.length + front - 1) % sentinel.length;
        if(size == sentinel.length - 3)
        {
            resize(2 * sentinel.length);
        }
    }

    //在双头数组后添加元素
    public void addLast(T item){
        sentinel[back] = item;
        back = back + 1;
        if(size == sentinel.length - 3){
            resize(2 * sentinel.length);
        }
    }

    //判断双头数组是否为空
    public boolean isEmpty(){
        return (size == 0);
    }

    //返回双头数组的大小
    public int size(){
        return size;
    }

    //打印双头队列
    public void printDeque(){
        for(int i = 0;i<size;i++){
            System.out.print(sentinel[i] + " ");
        }
        System.out.print('\n');
    }

    //删除双头队列的头数据
    public T removeFirst(){
        T b = sentinel[front];
        sentinel[front] = null;
        front += 1;
        return b;
    }

    //删除双头队列的尾数据
    public T removeLast(){
        T b = sentinel[back];
        sentinel[back] = null;
        back -= 1;
        return b;
    }

    //根据索引获取双头队列数据
    public T get(int index){
        return sentinel[index];
    }

}
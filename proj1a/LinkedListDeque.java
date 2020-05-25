public class LinkedListDeque<T>{

    private class Deque{

        public T item;
        public Deque front;
        public Deque next;

        public Deque(T i, Deque f, Deque n){
            item = i;
            front = f;
            next = n;
        }
    }

    private Deque sentinel;
    private int size;

    //创建空双向队列
    public LinkedListDeque(){
        sentinel = new Deque(null,null,null);
        sentinel.front = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //创建双向队列副本
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Deque(null,null,null);
        sentinel.front = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for(int i = 0;i < other.size;i++){
            addLast((T) get(i));
        }
    }

    //在双头队列前面添加元素
    public void addFirst(T item){
        sentinel.next = new Deque(item,sentinel,sentinel.next);
        sentinel.next.next.front = sentinel.next;
        size += 1;
    }

    //在双头队列后面添加元素
    public void addLast(T item){
        sentinel.front = new Deque(item, sentinel.front, sentinel);
        sentinel.front.front.next = sentinel.front;
        size += 1;
    }

    //判断双头队列是否为空
    public boolean isEmpty(){
        return (size == 0);
    }

    //返回双头队列的大小
    public int size(){
        return size;
    }

    //打印双头队列
    public void printDeque(){
        Deque d = sentinel;
        while(d.next != sentinel){
            System.out.print(d.next.item);
            System.out.print(" ");
            d = d.next;
        }
        System.out.print("\n");
    }

    //删除双头队列的头元素
    public T removeFirst(){
        if(size == 0) return null;
        Deque d = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.front = sentinel;
        return d.item;
    }

    //删除双头队列的尾元素
    public T removeLast(){
        if(size == 0) return null;
        Deque d = sentinel.front;
        sentinel.front = sentinel.front.front;
        sentinel.front.next = sentinel;
        return d.item;
    }

    //通过循环根据索引获取双头队列的对应元素
    public T get(int index){
        if(size <= index) return null;
        Deque d = sentinel;
        while(true){
            d = d.next;
            if(index == 0){
                return d.item;
            }
            index --;
        }
    }

    //通过递归根据索引获取双头队列的对应元素
    public T getRecursive(int index){
        if(size <= index) return null;
        Deque d = Recursive(sentinel, index);
        return d.item;
    }

    private Deque Recursive(Deque d, int index){
        if(index == 0) return d.next;
        else return Recursive(d.next, index - 1);
    }
    
    

}
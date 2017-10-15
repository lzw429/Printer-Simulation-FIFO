public class Queue<T> //双向链表实现的队列
{
    private int size;
    //front与rear不存数据
    public Node<T> front;
    public Node<T> rear;

    //构造方法
    public Queue() {
        front = new Node<T>(null, null, null);
        rear = new Node<T>(null, null, null);
        front.next = rear;
        rear.prev = front;
        this.size = 0;
    }

    //成员方法
    public int getSize() {
        return this.size;
    }

    public boolean empty() {
        return this.size == 0;
    }


    public void enqueue(T t) {
        rear.prev = rear.prev.next = new Node<T>(t, rear.prev, rear);
        size++;
    }

    public T dequeue() {
        if (size == 0)
            return null;
        Node<T> deleted = front.next;
        deleted.next.prev = front;
        front.next = deleted.next;
        size--;
        return deleted.data;
    }
}

class Node<T> {
    //数据域
    public T data;
    //指针域
    public Node<T> prev;
    public Node<T> next;

    //构造方法
    public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
}
package generics.messageQueue2021;

public class Main {
    public static void main(String[] args){
        MessageQueue<Integer> queue = new MessageQueue<>(12);

        queue.enqueue(10);
        queue.dequeue();
    }
}

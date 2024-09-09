package threads.igSwTDE.messageQueue2021;

public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(10);

        Publisher x = new Publisher(queue, 3);
        Publisher y = new Publisher(queue, 5);
        Subscriber z = new Subscriber(queue, 2);

        Thread t1 = new Thread(x);
        Thread t2 = new Thread(y);
        Thread t3 = new Thread(z);

        t1.start();
        t2.start();
        t3.start();
    }
}

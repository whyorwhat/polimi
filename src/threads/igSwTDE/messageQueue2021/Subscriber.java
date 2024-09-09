package threads.igSwTDE.messageQueue2021;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Subscriber implements Runnable {

    private MessageQueue queue;
    private int time;

    public Subscriber(MessageQueue queue, int time) {
        this.queue = queue;
        this.time = time;
    }

    @Override
    public void run() {
        while(true){
            try {
                queue.dequeue();
                sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }

    }
}

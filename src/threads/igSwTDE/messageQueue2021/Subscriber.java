package threads.igSwTDE.messageQueue2021;

import static java.lang.Thread.sleep;

public class Subscriber implements Runnable {

    private final MessageQueue queue;
    private final int time;

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

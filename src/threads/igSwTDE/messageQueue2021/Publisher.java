package threads.igSwTDE.messageQueue2021;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Publisher implements Runnable{

    private final MessageQueue queue;
    private final int time;

    public Publisher(MessageQueue queue, int time) {
        this.queue = queue;
        this.time = time;
    }

    @Override
    public void run() {
        while(true){
            Random random = new Random();
            int number = random.nextInt(100);
            String text = Integer.toString(number);
            try {
                queue.enqueue(text);
                sleep(time);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }
}

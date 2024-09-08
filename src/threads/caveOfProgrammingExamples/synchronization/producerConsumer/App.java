package threads.caveOfProgrammingExamples.synchronization.producerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);    //synchronized già incluso qui

    public static void producer() throws InterruptedException {   //Put viene eseguito finché la coda non è piena (10)
        Random random = new Random();

        while(true){
            queue.put(random.nextInt(100)); //Da 0 a 99
        }
    }

    public static void consumer() throws InterruptedException {   //Take aspetta finché non viene aggiunto qualcosa alla coda, e viene poi preso
        Random random = new Random();

        while(true){
            Thread.sleep(100);

            if(random.nextInt(10) == 0){
                Integer value = queue.take();

                System.out.println("Taken value " + value + ". Queue size is " + queue.size());
            }
        }
    }
}

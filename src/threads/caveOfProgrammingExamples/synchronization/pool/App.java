package threads.caveOfProgrammingExamples.synchronization.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i=0; i<5; i++){
            executor.submit(new Processor(i));
        }
        executor.shutdown();    //Aspetta tutti i thread di completare prima di terminare

        System.out.println("All tasks submitted.");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);    //Aspetta un giorno
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("All tasks completed.");
    }
}

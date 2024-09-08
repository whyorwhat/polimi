package threads.caveOfProgrammingExamples.synchronization.worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
    private final Random random = new Random();

    // Buona pratica creare oggetti lock separati
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();

    public void stageOne() {    //2 thread possono eseguire il metodo contemporaneamente
        synchronized (lock1) {  //ma se un thread è già entrato nel synchronized block, l'altro dovrà aspettare finché il primo non avrà lasciato il blocco (anche tramite un wait/notify)
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100));
        }
    }
    public void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list2.add(random.nextInt(100));
        }
    }

    public void process(){
        for(int i=0; i<1000; i++){
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting...");
        long startTime = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                process();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                process();
            }
        });

        t1.start();
        t2.start();

        try {
            // Join aspetta che terminino tutti i thread lanciati sull'oggetto su cui la join viene chiamata:
            // sospende l'esecuzione del codice legato all'oggetto su cui la join viene chiamata
            // finché non hanno concluso l'esecuzione tutti i thread che sono stati lanciati su un certo oggetto
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));

        System.out.println("List1 size: " + list1.size() + ", List2 size: " + list2.size());
    }
}

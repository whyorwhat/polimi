package caveOfProgrammingExamples.synchronization;

public class Synchronization {
    private int count = 0;

    private synchronized void increment() {
        count++;
    }

    public void doStuff() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count is " + count);
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronization synchronization = new Synchronization();
        synchronization.doStuff();
    }
}

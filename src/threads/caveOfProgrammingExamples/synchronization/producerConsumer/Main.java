package threads.caveOfProgrammingExamples.synchronization.producerConsumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                try {
                    App.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                try {
                    App.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

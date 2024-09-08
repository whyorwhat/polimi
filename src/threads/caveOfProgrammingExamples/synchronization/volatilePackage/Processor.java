package threads.caveOfProgrammingExamples.synchronization.volatilePackage;

public class Processor extends Thread {
    private volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stopThread() {
        running = false;
    }
}

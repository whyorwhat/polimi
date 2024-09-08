package threads.caveOfProgrammingExamples.demo;

class Runner2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Demo2 {
    public static void main(String[] args) {
        new Thread(new Runner2()).start();
    }
}

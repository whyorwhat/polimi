package threads.caveOfProgrammingExamples.demo;

class Runner3 extends Thread {
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

public class Demo3 {
    public static void main(String[] args) {
        Runner3 runner = new Runner3();
        runner.start();
    }
}

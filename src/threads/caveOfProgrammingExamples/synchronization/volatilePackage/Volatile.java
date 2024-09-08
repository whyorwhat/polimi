package threads.caveOfProgrammingExamples.synchronization.volatilePackage;

import java.util.Scanner;

public class Volatile {
    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();

        System.out.println("Press ENTER to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.stopThread();
    }
}

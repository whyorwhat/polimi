package threads.caveOfProgrammingExamples.synchronization.waitNotify;

import java.util.Scanner;

public class Processor {
    public void produce() throws InterruptedException { //Viene eseguito prima questo del consumer
        synchronized (this) {   //1. Questo prende il controllo del lock (Processor) e si mette in pausa
            System.out.println("Producer thread running...");
            wait(); //Object class method. Aspetta all'infinito. 2. Quando arriva qui si mette in attesa rilasciando all'altro (agli altri) synchronized il lock, permettendo di essere eseguito (eseguiti)

            //6. Questo blocco synchronized riprende il lock
            System.out.println("Resumed");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {   //SAME LOCK OF PRODUCER. 3. Viene eseguito dopo che lo sleep ha finito perché il producer si è messo in attesa
            System.out.println("Waiting fo ENTER key...");
            scanner.nextLine(); //Waiting for ENTER key
            System.out.println("ENTER key pressed");
            notify();   //notify() e wait() possono essere chiamati SOLO all'interno di un blocco synchronized. 4. Notifica l'altro thread che hanno il blocco sullo stesso oggetto e che sono in wait(), di risvegliarsi
            //Per notificare TUTTI gli altri thread bisogna usare notifyAll.
            //Con due soli thread è più efficiente notify().

            Thread.sleep(5000); // Prova per dimostrare che il lock è rilasciato solo dopo che tutte le istruzioni nel blocco terminano
            //5. Il blocco finisce e viene rilasciato il lock
        }
    }
}

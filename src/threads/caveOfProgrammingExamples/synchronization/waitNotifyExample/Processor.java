package threads.caveOfProgrammingExamples.synchronization.waitNotifyExample;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
    private final LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;   //Lunghezza della lista
    private Object lock = new Object();

    // Inserisce elementi nella lista
    public void produce() throws InterruptedException {
        int value = 0;

        while(true){
            synchronized (lock){
                while(list.size() == LIMIT) {
                    lock.wait();    //A. Lock si mette in pausa se la lista è piena
                    // Per essere sicuri che al risveglio siamo nella condizione giusta (la lista è NON VUOTA), eseguiamo il loop per effettuare di nuovo un controllo
                }

                list.add(value++);  //Incrementa la variabile value di 1 e aggiungilo
                lock.notify();  //B. Risveglia consumer
            }
        }
    }

    // Rimuove elementi dalla lista
    public void consume() throws InterruptedException {
        Random random = new Random();

        while(true){
            synchronized (lock){
                while(list.isEmpty()) {
                    lock.wait();    //B. Lock si mette in pausa se la lista è vuota
                }
                System.out.print("List size: " + list.size());
                int value = list.removeFirst();
                System.out.println(". Value removed: " + value);

                lock.notify();  //A. Risveglia il producer
            }

            Thread.sleep(random.nextInt(100));  //Limitiamo la velocità con cui viene estratto l'oggetto dalla lista (non c'è limite invece sulla velocità di inserimento nella lista nel producer)
        }
    }
}


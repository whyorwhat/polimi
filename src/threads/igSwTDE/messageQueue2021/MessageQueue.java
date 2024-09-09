package threads.igSwTDE.messageQueue2021;

import java.util.ArrayList;
import java.util.List;

// FIFO queue of messages (type is String)
// Messages are saved in data List of max size size
public class MessageQueue {
    private List<String> data;
    private int size;

    public MessageQueue(int size) {
        data = new ArrayList<>();
        this.size = size;
    }

    public void enqueue(String message) throws InterruptedException {
        synchronized(this){
            while(data.size() >= size){
                // CODA PIENA
                System.out.println("Full queue...");
                wait();
            }
            // CODA NON PIENA
            data.add(message);
            System.out.println("Added to queue: " + message);
            notifyAll();
        }
    }

    public String dequeue() throws InterruptedException {
        synchronized(this){
            while(data.isEmpty()){
                // CODA VUOTA
                System.out.println("Empty queue...");
                wait();
            }
            // CODA NON VUOTA
            String removedElement = data.remove(0);
            System.out.println("Removed element: " + removedElement);
            notifyAll();
            return removedElement;
        }
    }
}

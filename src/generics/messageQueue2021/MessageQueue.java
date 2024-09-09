package generics.messageQueue2021;

import java.util.ArrayList;
import java.util.List;

// FIFO queue of messages (type is generic)
// Messages are saved in data List of max size size
public class MessageQueue <T>{
    private List<T> data;
    private int size;

    public MessageQueue(int size) {
        data = new ArrayList<>();
        this.size = size;
    }

    public void enqueue(T message) {
        if(data.size() <= size){
            // CODA NON PIENA
            data.add(message);
            System.out.println("Added to queue: " + message);
        }
    }

    public T dequeue() {
        if(!data.isEmpty()){
            // CODA NON VUOTA
            T removedElement = data.remove(0);
            System.out.println("Removed element: " + removedElement);
            return removedElement;
        }
        return null;
    }
}
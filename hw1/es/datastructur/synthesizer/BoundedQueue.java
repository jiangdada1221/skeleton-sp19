package es.datastructur.synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();
    int fillCount();
    void enqueue(T x);
    T dequeue(); //delete and return the item in the front
    T peek(); //not delete, just return
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    default boolean ifFull() {
        if (capacity() == fillCount()) {
            return true;
        }
        return false;
    }

    @Override
    Iterator<T> iterator();
}

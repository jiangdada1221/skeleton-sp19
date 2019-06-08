package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        first = 0;
        last = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (fillCount == capacity()){
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last]  = x;
        last += 1;
        if (last == rb.length) {
            last = 0;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount -= 1;
        T result = rb[first];
        rb[first] = null;
        first ++;
        if (first == rb.length) {
            first = 0;
        }
        return result;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new iterForA();
    }

    private class iterForA<T> implements Iterator<T>{
        private int index = 0;
        @Override
        public boolean hasNext() {
            if (index <= fillCount-1) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int indexreal = index + first;
            if (indexreal > capacity()-1) {
                indexreal -= capacity();
            }
            T result = (T) rb[indexreal];
            index ++;
            return result;
        }
    }
    @Override
    public boolean equals(Object o) {
        ArrayRingBuffer<T> arb = (ArrayRingBuffer<T>) o;
        if (! arb.getClass().equals(this.getClass())) {
            return false;
        }

//
        if (arb.fillCount()!= fillCount() || arb.capacity()!= capacity()) {
            return false;
        }
        int index = 0;
        for (T t: arb) {
            int indexReal = index + first;
            if (indexReal > capacity()-1) {
                indexReal -= capacity();
            }
            if (t != this.rb[indexReal]) {
                return false;
            }
            index += 1;
        }
        return true;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.

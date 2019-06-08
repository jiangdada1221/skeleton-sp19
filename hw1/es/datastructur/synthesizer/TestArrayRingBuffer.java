package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        for (int i: arb) {
            System.out.println(i);
        }
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        arb2.enqueue(1);
        arb2.enqueue(2);
        arb2.enqueue(3);
//        assertTrue(arb.equals(arb2));
        System.out.println(arb.equals(arb2));
//        System.out.println(arb2.getClass() == arb.getClass());

        assertEquals(10,arb.capacity());
        assertEquals(3,arb.fillCount());
        assertTrue(1==arb.peek());
        assertEquals(1,(int)arb.peek());
        System.out.println(arb.peek().getClass()); // is an integer 
        assertTrue(1==arb.dequeue());
        System.out.println(arb.equals(arb2));
        assertEquals(2,arb.fillCount());
        assertTrue(2==arb.dequeue());
//        System.out.println(1==arb.peek());
        System.out.println(arb.getClass());
    }
}

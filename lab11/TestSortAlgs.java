import edu.princeton.cs.algs4.Queue;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {

    }

    @Test
    public void testMergeSort() {
        Queue<String> test = new Queue<>();
//        String[] string = new String[500];
        test.enqueue("jyp");
        test.enqueue("xzy");
        test.enqueue("hi");
        test.enqueue("love");
        test.enqueue("die");
        Queue<String> result = MergeSort.mergeSort(test);
        assertEquals("die", result.dequeue());
        assertEquals("hi", result.dequeue());
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items A Queue of items
     * @return true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testSpeed() {
        double time11 = System.currentTimeMillis();
        for (int i=0;i<=9;i++) {
            Queue<Integer> testM = new Queue<>();
            for (int j = 0;j<= 300000;j++) {
                testM.enqueue(StdRandom.uniform(0,10000));
        }
        MergeSort.mergeSort(testM);
        }
        double time12 = System.currentTimeMillis();

        double time21 = System.currentTimeMillis();
        for (int i=0;i<=9;i++) {
            Queue<Integer> testS = new Queue<>();
            for (int j = 0;j<= 300000;j++) {
                testS.enqueue(StdRandom.uniform(0,10000));
            }
            QuickSort.quickSort(testS);
        }
        double time22 = System.currentTimeMillis();

        System.out.println("the average runtime for merge sort is "+ (time12 - time11)/10);
        System.out.println("the average runtime for quick sort is "+ (time22 - time21)/10);
    }


}

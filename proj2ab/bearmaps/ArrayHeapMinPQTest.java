package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.JUnit4;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class ArrayHeapMinPQTest {
    @Test
    public void testcontains() {
        ArrayHeapMinPQ<String> jyp = new ArrayHeapMinPQ<>();
        jyp.add("jyp",0.66);
        jyp.add("xzy",0.77);
        jyp.add("loves",1.11);
        assertTrue(jyp.contains("loves"));
        assertTrue(jyp.contains("xzy"));
    }

    @Test
    public void testSmall() {
        ArrayHeapMinPQ<String> jyp = new ArrayHeapMinPQ<>();
        jyp.add("jyp",0.66);
        jyp.add("xzy",0.77);
        jyp.add("loves",1.11);
        assertEquals("jyp",jyp.getSmallest());
        assertEquals("jyp",jyp.removeSmallest());
        assertEquals("xzy",jyp.getSmallest());
        assertEquals("xzy",jyp.removeSmallest());
        assertEquals("loves",jyp.removeSmallest());
        assertFalse(jyp.contains("jyp"));
    }

    @Test
    public void testadd() {
        ArrayHeapMinPQ<String> jyp = new ArrayHeapMinPQ<>();
        for (int i = 1;i <500; i ++) {
            jyp.add("hi" + i, StdRandom.uniform(0.0,3.0));
        }

        System.out.println(jyp.getSmallest());

        assertEquals(499,jyp.size());
    }

    @Test
    public void Changepri() {
        ArrayHeapMinPQ<String> jyp = new ArrayHeapMinPQ<>();
        jyp.add("jyp",0.66);
        jyp.add("xzy",0.77);
        jyp.add("loves",1.11);
        jyp.changePriority("jyp",88);
        assertEquals("xzy",jyp.getSmallest());
    }

    @Test
    public void testSpeed() {
//        ArrayHeapMinPQ<String> jyp = new ArrayHeapMinPQ<>();
//
//        for (int i = 1;i <1000000; i ++) {
//            jyp.add("hi" + i, StdRandom.uniform(0.0,3.0));
//        }
//
//        double refer1a = System.currentTimeMillis();
//        for (int i = 1;i < 1000000; i ++)
//            jyp.removeSmallest();
//        double refer2a = System.currentTimeMillis();
//        System.out.println("the remove time for heap is " + (refer2a-refer1a));

        NaiveMinPQ<String> xzy = new NaiveMinPQ<>();

        for (int i = 1;i <3000; i ++) {
            xzy.add("hi" + i, StdRandom.uniform(0.0,3.0));
        }
        double refer1b = System.currentTimeMillis();
        for (int i = 1;i < 3000;i++)
            xzy.removeSmallest();
        double refer2b = System.currentTimeMillis();
        System.out.println("the remove time for naive is " + (refer2b-refer1b));

    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.runClasses(ArrayHeapMinPQTest.class);
    }
}

package bearmaps;


import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {
    private ArrayList<Node> array;
    private int size;
    private TreeMap<T,Integer> set;

    public ArrayHeapMinPQ() {
        this.size = 0;
        this.array = new ArrayList<>();
        this.set = new TreeMap<>();
    }

    private class Node implements Comparable<Node> {
        private T item;
        private double Priority;

        Node(T t, double priority) {
            item = t;
            Priority = priority;
        }

        private void ChangePriority(double NewPriority) {
            this.Priority = NewPriority;
        }
        @Override
        public int compareTo(Node o) {
            double Difference = this.Priority - o.Priority;
            if (Difference > 0)
                return 1;
            else if (Difference < 0)
                return -1;
            else
                return 0;
        }
    }

    @Override
    public void add(T item, double priority) {
        if (size == 0){
            Node Zero = new Node(item,0);
            array.add(Zero);
        }
        else if (contains(item))
            throw new IllegalArgumentException();
        Node Input = new Node(item,priority);
        size ++;
        set.put(item,size);
        array.add(Input);
        swimUp(size);
    }
    private int parent(int index) {
        return index / 2;
    }

    @Override
    public boolean contains(T item) {
       return set.containsKey(item);
    }


    @Override
    public T getSmallest() {
        if (size == 0)
            throw new NoSuchElementException();
        return array.get(1).item;
    }

    @Override
    public T removeSmallest() {
        if (size == 0)
            throw new NoSuchElementException();
        T result = array.get(1).item;
        Switch(1,size);
        set.remove(array.get(size).item);
        array.remove(size);
        size -=1;
        swimDown(1);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    private void Switch(int index,int parent) {
        Node temp = array.get(index);
        int refer1 = set.get(temp.item);
        int refer2 = set.get(array.get(parent).item);
        set.replace(temp.item,refer2);
        set.replace(array.get(parent).item,refer1);
        array.set(index,array.get(parent));
        array.set(parent,temp);
    }

    private void swimUp(int index) {
        if (index ==1 || array.get(index).compareTo(array.get(parent(index))) >= 0)
            return;
        Switch(index,parent(index));
        swimUp(parent(index));
    }

    private void swimDown(int index) {
//        if (array.get(index).compareTo(array.get(index*2)) < 0 &&
//                array.get(index).compareTo(array.get(index*2+1)) < 0 )
//            return;
//        if (array.get(index*2).compareTo(array.get(index*2+1)) > 0){
//            Switch(index,index*2+1);
//            swimDown(index*2+1);
//        }
        if (index * 2 > size)
            return;
        if (index * 2 == size){
            if (array.get(index).compareTo(array.get(index*2)) <=0)
                return;
            Switch(index,index*2);
        }
        else {
            if (array.get(index).compareTo(array.get(index*2)) <= 0 &&
                array.get(index).compareTo(array.get(index*2+1)) <= 0 )
                return;
            else{
                if (array.get(index*2).compareTo(array.get(index*2+1)) > 0){
                    Switch(index,index*2+1);
                    swimDown(index*2+1);
                }
                Switch(index,index*2);
                swimDown(index*2);
            }
        }


    }
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item))
            throw new NoSuchElementException();
        int index = set.get(item);
        double P = array.get(index).Priority;
        array.get(index).ChangePriority(priority);
        if (P > priority)
            swimUp(index);
        else
            swimDown(index);
    }

    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        array.add("jyp");
        array.add("xzy");
        array.add("love");
        array.set(0,"pyj");
        for (String i : array) {
            System.out.println(i);
        }
    }
}

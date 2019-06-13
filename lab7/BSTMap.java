import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class BSTMap<k, v> implements Map61B<k, v> {
    private class bTree implements Comparable<k> {
        k key;
        v value;
        bTree left, right;

        public bTree(k key, v value, bTree left, bTree right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(k o) {

            if (key instanceof String) {
                String a = (String) key;
                String b = (String) o;
                return a.compareTo(b);
            } else {
                int a = (int) key;
                int b = (int) o;
                return a - b;
            }
        }
    }

    private bTree tree;
    private int size;

    @Override
    public void clear() {
        tree = null;
        size = 0;
    }

    @Override
    public boolean containsKey(k key) {
        bTree bt = tree;
        while (bt != null) {
            int diff = bt.compareTo(key);
            if (diff == 0)
                return true;
            if (diff > 0)
                bt = bt.left;
            else
                bt = bt.right;
        }
        return false;
    }

    @Override
    public v get(k key) {
        bTree bt = tree;
        while (bt != null) {
            int diff = bt.compareTo(key);
            if (diff == 0)
                return bt.value;
            if (diff > 0)
                bt = bt.left;
            else
                bt = bt.right;
        }
        return null;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(k key, v value) {
        if (containsKey(key))
            return;
        else{
            size += 1;
            bTree node = new bTree(key,value,null,null);
            bTree bt = this.tree;
            bTree prev = bt;
            int refer = 0;
            if (bt == null) {
                this.tree = node;
                return;
            }else{
            while (bt != null) {
                int diff = bt.compareTo(key);
                prev = bt;
                if (diff > 0) {
                    bt = bt.left;
                    refer = 0;
                }
                else {
                    bt = bt.right;
                    refer = 1;
                }
            }
            if (refer == 1) {
                prev.right = node;
            }
            else
                prev.left = node;
        }
    }
    }

    @Override
    public Set<k> keySet() {
        /* TreeSet has already sort the key in natural order*/
        Set<k> set = new TreeSet<>();
        return setHelper(tree,set);

    }
    public Set<k> setHelper(bTree bt,Set<k> set) {
        if (bt == null) {
            return set;
        }
        else {
            set.add(bt.key);
            setHelper(bt.left,set).addAll(setHelper(bt.right,set));
            return set;
        }
    }

    @Override
    public v remove(k key) {
        v value = this.get(key);
        helper(this.tree,key);
        return value;
    }
    public bTree helper(bTree bt, k key){
        if (bt == null) {
            return null;
        }
        if (bt.compareTo(key) < 0) {
            bt.right = helper(bt.right,key);
        }
        else if (bt.compareTo(key) > 0) {
            bt.left = helper(bt.left,key);
        }
        /* find the key in BST */
        else if (bt.left == null)
            return bt.right;
        else if (bt.right == null)
            return bt.left;
        else
            bt.right = swapSmallest(bt.right,bt);
        return bt;
    }
    private bTree swapSmallest(bTree bt, bTree right){
        if (right.left == null) {
            bt.key = right.key;
            bt.value = right.value;
            return right.right;
        }
        else {
            right.left = swapSmallest(bt,right.left);
            return right;
        }
    }
    @Override
    public v remove(k key, v value) {
        /* similar to remove key */
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<k> iterator() {
        Set<k> set = this.keySet();
        return set.iterator();
    }


    public void printInOrder() {
        Set<k> set = this.keySet();
        for (k i: set) {
            System.out.println(i);
        }
    }
}

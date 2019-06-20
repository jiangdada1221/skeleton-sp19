import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class MyHashMap<k,v> implements Map61B<k,v> {
    private int size = 0;
    private int initialSize;
    private double loadFactor;
    private int Size;
    private ArrayList<Entry<k,v>>[] Alist;
    public MyHashMap() {
        this.initialSize = 16;
        this.Alist = new ArrayList[initialSize];
        this.loadFactor = 0.75;
        this.Size = this.initialSize;
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.Alist = new ArrayList[initialSize];
        this.loadFactor = 0.75;
        this.Size = this.initialSize;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.Alist = new ArrayList[initialSize];
        this.Size = this.initialSize;
    }


    private class Entry<k,v> {
        k key;
        v value;
        public Entry(k key, v value) {
            this.key = key;
            this.value = value;
        }
    }


    @Override
    public void clear() {
        /*remove all keys and values*/
        Alist = new ArrayList[Size];
        size = 0;
    }

    @Override
    public boolean containsKey(k key) {
        for (ArrayList<Entry<k,v>> al:this.Alist) {
            if (al == null) {
                continue;
            }
            for (Entry<k,v> entry: al) {
                if (entry.key.equals(key))
                    return true;
            }

        }

        return false;
    }

    @Override
    public v get(k key) {
        for (ArrayList<Entry<k,v>> al:this.Alist) {
            if (al == null)
                continue;
            for (Entry<k,v> entry: al) {
                if (entry.key.equals(key))
                    return entry.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(k key, v value) {
//        if (containsKey(key)) {
//            int buckToput = (0x7fffffff & key.hashCode ()) % this.Size;
//            for (Entry<k,v> entry :Alist[buckToput]) {
//                if (entry.key.equals(key))
//                    entry.value = value;
//            }
//            return;
//        }          this implement makes code slow;
        Entry<k,v> EntryToPut = new Entry<>(key,value);
        int bucketNum = (0x7fffffff & key.hashCode ()) % this.Size;
        if (Alist[bucketNum] == null){
            ArrayList<Entry<k,v>> ar = new ArrayList<>();
            ar.add(EntryToPut);
            Alist[bucketNum] = ar;
            size ++;
        }else {
            for (Entry<k,v> entry: Alist[bucketNum]) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
            Alist[bucketNum].add(EntryToPut);
            size ++;
        }

        if ((double) size() / Size > loadFactor) {
            Alist = resize();
        }

    }

    private ArrayList<Entry<k,v>>[] resize() {
        Size = Alist.length * 2;
        int buckToPut;
        ArrayList<Entry<k,v>>[] newAList = new ArrayList[Size];
        for (ArrayList<Entry<k,v>> al : this.Alist) {
            if (al ==null)
                continue;
            for (Entry<k,v> entry: al ) {
                buckToPut = (entry.key.hashCode() & 0x7fffffff) % Size;
                if (newAList[buckToPut] == null) {
                    ArrayList<Entry<k,v>> ar = new ArrayList<>();
                    ar.add(entry);
                    newAList[buckToPut] = ar;
                }
                else {

                    newAList[buckToPut].add(entry);
                }
            }
        }
        return newAList;
    }

    @Override
    public Set<k> keySet() {
        Set<k> hashSet = new HashSet<>();
        for (ArrayList<Entry<k,v>> ar: Alist) {
            if (ar == null)
                continue;
            for (Entry<k,v> entry: ar) {
                hashSet.add(entry.key);
            }
        }
        return hashSet;
    }

    @Override
    public v remove(k key) {
        return null;
    }

    @Override
    public v remove(k key, v value) {
        return null;
    }

    @Override
    public Iterator<k> iterator() {
        HashSet<k> set = (HashSet)keySet();
        return set.iterator();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> b = new MyHashMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        System.out.println(b.Size);
//       Set<String> set = b.keySet();
//        for (String s : b ) {
//            System.out.println(s);
//        }

    }
}

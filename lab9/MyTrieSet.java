import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import edu.princeton.cs.algs4.TrieSET;

public class MyTrieSet implements TrieSet61B {
    private HashMap<Character,Node> root;


    public MyTrieSet() {
        this.root = new HashMap<>(4);
//        this.root.put('!',new Node(false));
    }

    private class Node {
        boolean isKey;
        HashMap<Character,Node> hashmap;
        Node(boolean isKey) {
            this.isKey = isKey;
            hashmap = new HashMap<>(4);
        }
    }

    @Override
    public void clear() {
        root = new HashMap<>();
//        root.put('!',new Node(false));
    }

    @Override
    public boolean contains(String key) {
//        int length = key.length();
//        Node node = this.node;
//        for (int i = 0; i < length-1; i ++) {
//            boolean refer = false;
//            for (Node n: node.haset) {
//                if (n.ch == key.charAt(i)) {
//                    refer = true;
//                    node = n;
//                    break;
//                }
//            }
//            if (refer == false)
//                return false;
//        }
//        for (Node n: node.haset) {
//            if (n.ch == key.charAt(length-1)) {
//                if (n.isKey)
//                    return true;
//                else
//                    return false;
//            }
//        }
//        return false;
        if (key.length() < 1)
            throw new IllegalArgumentException();
        return helpCon(root,key);
    }
    private boolean helpCon(HashMap<Character,Node> root, String key) {
        if (key.length() == 1)
            return root.get(key.charAt(0)) != null && root.get(key.charAt(0)).isKey;
        else {
            Node n = root.get(key.charAt(0));
            if (n != null) {
                return helpCon(n.hashmap,key.substring(1));
            }
            else
                return false;
        }
    }

    @Override
    public void add(String key) {  // 用 while（for）循环更简单 这太复杂了！
//        int length = key.length();
//        for (int i = 0; i < length - 1; i ++) {
//            if (key.charAt(i) != node.ch) {
//                break;
//            }
//
//        }
//        Node n = this.node;
//        Node Add = new Node('!',false);
//        for (int i = 0; i < length - 1; i ++) {
//            Node newNode = new Node(key.charAt(i),false);
//            n.haset.add(newNode);
//            n = newNode;
//        }
//        Node newNode = new Node(key.charAt(length - 1),true);
//        n.haset.add(newNode);
        HashMap<Character,Node> goal = root;
        int i ;
        for (i = 0; i <= key.length() - 1;i ++) {
            if (goal.get(key.charAt(i)) == null) {
                break;
            }
            goal = goal.get(key.charAt(i)).hashmap;
        }
        addHelp(goal,key.substring(i));
    }
    private void addHelp(HashMap<Character,Node> root, String key) {
        if (key.length() == 1)
            root.put(key.charAt(0),new Node(true));
        else {
            Node ToPut = new Node(false);
            root.put(key.charAt(0),ToPut);
            addHelp(ToPut.hashmap,key.substring(1));
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        int length = prefix.length();
        HashMap<Character,Node> goal = root;
        for (int i = 0; i < length - 1; i ++) {
            if (goal.get(prefix.charAt(i)) == null)
                return result;
            goal = goal.get(prefix.charAt(i)).hashmap;
        }
        if (goal.get(prefix.charAt(length - 1)) == null)
            return result;
        else {
            Node n = goal.get(prefix.charAt(length - 1));
            goal = goal.get(prefix.charAt(length - 1)).hashmap;
            if (n.isKey) {
                result.add(prefix);
                return PrefixHelper(prefix,goal,result);
            }else
                return PrefixHelper(prefix,goal,result);
        }
    }
    private List<String> PrefixHelper(String con, HashMap<Character,Node> root, List<String> result) {
        if (root.size() == 0) {
            return result;
        }
        else {
            for (Character key: root.keySet()) {
                if (root.get(key).isKey) {
                    result.add(con + key);
                }
                result = PrefixHelper(con + key,root.get(key).hashmap, result);
            }
            return result;
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        return null;
    }

    public static void main(String[] args) {
        /*test*/
        MyTrieSet jyp = new MyTrieSet();
        jyp.add("Jyp");
        jyp.add("JYP");
        jyp.add("jyp");
//        System.out.println(jyp.contains("loves"));
//        System.out.println(jyp.contains("JYP"));
//        System.out.println(jyp.contains("JYp"));
//        System.out.println(jyp.contains("jyp"));
        double time1 = System.currentTimeMillis();
        for (int i = 0;i < 60 ; i ++) {
            jyp.add("jyp" + i);
        }
        System.out.println(jyp.contains("jyp888"));
        double time2 = System.currentTimeMillis();
        System.out.println("the time for add(my Trie) is " + (time2 - time1));
        List<String> result = jyp.keysWithPrefix("jy");
        for (String s : result)
            System.out.println(s);
//
//        TrieSET ts = new TrieSET();
//        double time3 = System.currentTimeMillis();
//        for (int i = 0;i < 20000 ; i ++) {
//        ts.add("jyp" + i);
//        }
//        double time4 = System.currentTimeMillis();
//        System.out.println("the time for add(built in) is " + (time4 - time3));
//        System.out.println("that 's good , the time used for both is very close");

}
}

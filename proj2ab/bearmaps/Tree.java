package bearmaps;

public class Tree   {
    private Node node;
    private Tree L;
    private Tree R;
    private int size;
    public Tree() {
        node = null;
        L = null;
        R = null;
    }
    public Tree(Node n) {
        L = null;
        R = null;
        node = n;
        size = 0;
    }


    public void add(Node n) {
        if (size == 0){
            node = n;
            size ++;
            return;
        }
        helper(this,n);
    }
    private Tree helper(Tree t, Node n) {

        if (t == null) {
            size ++;
            t = new Tree(n);
            return t;
        }
        else {
            int diff = n.compareTo(t.node);
            if (diff > 0) {
                n.changeSta();
                t.R = helper(t.R, n);
                return t;
            } else if (diff < 0) {
                n.changeSta();
                t.L = helper(t.L, n);
                return t;
            }
            return t;
        }
    }

    public static void main(String[] args) {
        /* test */
        Tree jyp = new Tree();
        Node node = new Node(new Point(1.0,2.0));
        jyp.add(node);
//        System.out.println(jyp.node.point);
        Node node2 = new Node(new Point(1.1,2.2));
        Node node3 = new Node(new Point(0.8,1.2));
        Node node4 = new Node(new Point(3.3,0.8));
        Node node5 = new Node(new Point(1.3,2.4));
        Node node6 = new Node(new Point(0.3,1.8));
        jyp.add(node2);
        jyp.add(node3);
        jyp.add(node4);
        jyp.add(node5);
        jyp.add(node6);
        System.out.println(jyp.size);
        System.out.println(jyp.node.point);
        System.out.println(jyp.L.node.point);
        System.out.println(jyp.R.node.point);
        System.out.println(jyp.L.R.node.point);
        System.out.println(jyp.R.L.node.point);
        System.out.println(jyp.R.R.node.point);
    }

}

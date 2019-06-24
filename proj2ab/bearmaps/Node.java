package bearmaps;

public class Node implements Comparable<Node>{
    public Point point;
    private boolean LF;
    public Node(Point p) {
        this.point = p;
        LF = true;
    }
    public void changeSta() {
        if (LF == true)
            LF = false;
        else
            LF = true;
    }
    @Override
    public int compareTo(Node o) {
        if (LF) {
            double diff = this.point.getX() - o.point.getX();
            if (diff == 0)
                return 0;
            else
                return (int) (Math.abs(diff)/diff);
        }else {
            double diff = this.point.getY() - o.point.getY();
            if (diff == 0)
                return 0;
            else
                return (int) (Math.abs(diff)/diff);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass())
            return false;
        else{
            Node no = (Node)o;
            return no.point.equals(this.point);
        }
    }
}

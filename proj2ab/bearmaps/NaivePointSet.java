package bearmaps;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point Goal = new Point(x,y);
        Point smallest = points.get(0);
        double SmaDis = Point.distance(Goal,smallest);
        for (Point p: points) {
            if (Point.distance(p,Goal) < SmaDis){
                smallest = p;
                SmaDis = Point.distance(Goal,p);
            }
        }
        return smallest;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1,2.2);
        Point p2 = new Point(3.3,4.4);
        Point p3 = new Point(-2.9,4.2);
        Point p4 = new Point(3.0,4.1);
        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        for (int i = 0;i <= 5000000;i ++)
            points.add(new Point(StdRandom.uniform(0.0,6.0),StdRandom.uniform(0.0,6.0)));
        NaivePointSet nn = new NaivePointSet(points);
        double time1 = System.currentTimeMillis();
        Point ret = nn.nearest(3.0,4.0);
        double time2 = System.currentTimeMillis();
        System.out.println("the time used for Naive is " + (time2 - time1));
        System.out.println("the x coordinate is " + ret.getX());
        System.out.println("the y coordinate is " + ret.getY());
    }

}

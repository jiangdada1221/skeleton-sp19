package bearmaps.hw4.integerhoppuzzle;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;

import java.util.ArrayList;
import java.util.List;

/**
 * The Integer Hop puzzle implemented as a graph.
 * Created by hug.
 */
public class IntegerHopGraph implements AStarGraph<Integer> {

    @Override
    public List<WeightedEdge<Integer>> neighbors(Integer v) {
        ArrayList<WeightedEdge<Integer>> neighbors = new ArrayList<>();
        neighbors.add(new WeightedEdge<>(v, v * v, 10));
        neighbors.add(new WeightedEdge<>(v, v * 2, 5));
        neighbors.add(new WeightedEdge<>(v, v / 2, 5));
        neighbors.add(new WeightedEdge<>(v, v - 1, 1));
        neighbors.add(new WeightedEdge<>(v, v + 1, 1));
        return neighbors;
    }

    @Override
    public double estimatedDistanceToGoal(Integer s, Integer goal) {
        // possibly fun challenge: Try to find an admissible heuristic that
        // speeds up your search. This is tough!
        double sqrt = Math.sqrt(goal);       // this method can reduce pop from 298 to 94
        double divide = goal/2;             // wrong estimate will return wrong result!
        if (Math.abs(goal-s)<5)
            return Math.abs(goal-s);
//
        else if ((double)s / divide >=0.8 && (double) s / divide <=1.2)
            return 4;
        else if (s>=goal-10 && s<=goal+10)
            return Math.abs(goal-s);
        else if ((double)s / sqrt >=0.8 && (double) s / sqrt <=1.2)
            return 5;
        else
            return 5;

    }
}

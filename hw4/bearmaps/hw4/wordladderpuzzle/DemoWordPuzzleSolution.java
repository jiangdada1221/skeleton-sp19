package bearmaps.hw4.wordladderpuzzle;


import bearmaps.hw4.AStarSolver;
import bearmaps.hw4.LazySolver;
import bearmaps.hw4.ShortestPathsSolver;
import bearmaps.hw4.SolutionPrinter;

/**
 * Showcases how the AStarSolver can be used for solving word ladders.
 * NOTE: YOU MUST REPLACE LazySolver WITH AStarSolver OR THIS DEMO WON'T WORK!
 * Make sure to set your current working directory to be the one
 * containing words10000.txt.
 * Created by hug.
 */

/**
 * To correct this,
 * go to the “Edit Configurations” option under the Run menu of IntelliJ.
 * Change the working directory to point to the folder that ends
 * with bearmaps/hw4/input on your computer.
 */
public class DemoWordPuzzleSolution {
    public static void main(String[] args) {
        String start = "intention";
        String goal = "execution";

        WordGraph wg = new WordGraph();

        ShortestPathsSolver<String> solver = new AStarSolver<>(wg, start, goal, 10);
        SolutionPrinter.summarizeSolution(solver, "<-");
    }
}

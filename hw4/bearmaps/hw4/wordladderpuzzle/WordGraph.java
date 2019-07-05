package bearmaps.hw4.wordladderpuzzle;

import bearmaps.hw4.AStarGraph;
import bearmaps.hw4.WeightedEdge;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A class that represents the graph of all english words. Word p has an
 * edge to word q if the edit distance between p and q is 1. For example,
 * there is an edge from "horse" to "hose", and "hose" to "horse". There is
 * no edge from "dog" to "deg", because deg isn't a word (at least according to
 * words10000.txt).
 * Created by hug.
 */
public class WordGraph implements AStarGraph<String> {
    private Set<String> words;
    /* have to be URL!*/
    private static final String WORDFILE = "/Users/yuepengjiang/Downloads/cs61b/skeleton-sp19/hw4/bearmaps/hw4/wordladderpuzzle/words10000.txt";

    /**
     * Reads the wordfile specified by the wordfile variable.
     */
    private void readWords() {
        words = new HashSet<>();

        In in = new In(WORDFILE);
        while (!in.isEmpty()) {
            String w = in.readString();
            words.add(w);
        }
    }

    /**
     * Computes the edit distance between a and b. From
     * https://rosettacode.org/wiki/Levenshtein_distance.
     */
    private static int editDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int[] costs = new int[b.length() + 1];
        for (int j = 0; j < costs.length; j++) {
            costs[j] = j;
        }           // because the substitue weight is 1 in this case, so you can change this to an 1-D array
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                        a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1); // 循环是循环box的每一层 不是列！
                nw = costs[j];           //nw 还是原来那层的， 然后更新costj 为新的一层
                costs[j] = cj;           // 这样nw为change的  costj为右下一层（因为j+1后还没更新j+1，但是j已经更新）所以costj为同层的左边那个！
            }
        }
        return costs[b.length()];
    }


    /**
     * Creates a new Word.
     */
    public WordGraph() {
        readWords();
    }

    @Override
    public List<WeightedEdge<String>> neighbors(String s) {
        List<WeightedEdge<String>> neighbs = new ArrayList<>();   // for the challenge I can put words in a BST based on size
        for (String w : words) {                                    // and then iterate over the branch that size +- 1 compared to the given s
            if (editDistance(s, w) == 1) {
                neighbs.add(new WeightedEdge(s, w, 1));
            }
        }
        return neighbs;
    }

    @Override
    public double estimatedDistanceToGoal(String s, String goal) {
        return editDistance(s, goal);
    }

    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";
        System.out.println(editDistance(s1,s2));
    }
}

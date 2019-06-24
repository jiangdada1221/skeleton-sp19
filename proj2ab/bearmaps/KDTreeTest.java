package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.sql.SQLSyntaxErrorException;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void test() {
        int[] tes = new int[100000];
        double time1 = System.currentTimeMillis();
        StdRandom.shuffle(tes);
        double time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
    }
}

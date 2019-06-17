package hw2;

import edu.princeton.cs.introcs.StdStats;

import static edu.princeton.cs.introcs.StdRandom.*;
import static edu.princeton.cs.introcs.StdStats.*;
public class PercolationStats {
    private double[] data;
    private int t;
    public PercolationStats(int n, int t, PercolationFactory pf) {
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException();
        }
        this.t = t;
        data = new double[t];
        for (int i = 0; i <= t - 1; i++) {
            Percolation per = pf.make(n);
            while (!per.percolates()) {
                per.open(uniform(n), uniform(n));
            }
            data[i] = (double)per.numberOfOpenSites() / (double) (n*n);
        }
    }

    public double mean() {
        return StdStats.mean(this.data);
    }

    public double stddev() {
        return StdStats.stddev(this.data);
    }

    public double confidenceLow() {
        return this.mean() - 1.96* stddev()/Math.sqrt(t);
    }

    public double confidenceHight() {
        return this.mean() + 1.96* stddev()/Math.sqrt(t);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(50,100000,pf);
        System.out.println(ps.mean());
        System.out.println(ps.confidenceLow()+ " -> " +ps.confidenceHight());
    }
}

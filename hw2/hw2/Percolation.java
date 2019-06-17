package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n,op;
    private WeightedQuickUnionUF wq;
    private int[][] array;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.op = 0;
        this.n = n;
        this.wq = new WeightedQuickUnionUF((n+2) * (n+2) + 2); //  + 1 is top virtual
        this.array = new int[n+2][n+2];
        for (int i = 0; i < n; i ++) {
            wq.union(toOneD(n,i+1),(n+2) * (n+2) ); // bottom
            wq.union(toOneD(1,i+1),(n+2)*(n+2) + 1); // top
        }
    }

    public void open(int row, int col) {
        if (isOpen(row+1,col+1)) {
            return;
        }else {
            if (isOpen(row+2,col + 1))
                wq.union(toOneD(row+2,col+1),toOneD(row+1,col+1));
            if (isOpen(row ,col + 1))
                wq.union(toOneD(row,col+1),toOneD(row+1,col+1));
            if (isOpen(row + 1,col+ 2))
                wq.union(toOneD(row+1,col+2),toOneD(row+1,col+1));
            if (isOpen(row + 1,col))
                wq.union(toOneD(row+1,col),toOneD(row+1,col+1));
            this.op += 1;
            array[row+1][col+1] = 1;
        }

    }
    private int toOneD(int row, int col) {
        return (row+1)*(this.n+2) + col+1;
    }

    public boolean isOpen(int row, int col) {
        /* return whether the site is open */
        return array[row][col] != 0;
    }

    public boolean isFull(int row, int col) {
        return wq.connected(toOneD(row+1,col+1),(n+2)*(n+2)+1);
    }

    public int numberOfOpenSites() {
        /* return the number of open sites */
        return this.op;
    }

    public boolean percolates() {
        return wq.connected((n+2)*(n+2),(n+2)*(n+2)+1);
    }

    public static void main(String[] args) {
        Percolation per = new Percolation(10);
//        per.open(0,0);
//        per.open(1,0);
//        System.out.println(per.percolates());
//        System.out.println(per.numberOfOpenSites());
//        per.open(2,0);
//        System.out.println(per.percolates());
        while (!per.percolates()) {
            per.open(StdRandom.uniform(10),StdRandom.uniform(10));
        }
        System.out.println(per.numberOfOpenSites());
    }
}

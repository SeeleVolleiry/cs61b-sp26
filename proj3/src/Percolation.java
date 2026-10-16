// import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // Adding necessary instance variables.
    public int[][] grid ; // 2D Array: 0-blocked, 1-open
    public int size;
    public int openNum;
    private WeightedQuickUnionUF WQU;
    // public double p; // probability

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        // The constructor should take time proportional to N^2.
        if (N <= 0) {
            throw new IllegalArgumentException("N must greater than 0!");
        }
        grid = new int[N][N];
        size = N;
        openNum = 0;
        WQU = new WeightedQuickUnionUF(N * N);
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        // take constant time plus a constant number of calls to the union-find methods.
        validate(row, col);
        if (isOpen(row, col)) {
            return ;
        }
        this.grid[row][col] = 1;
        openNum += 1;
        // 如果上下左右open，就union
        int root = WQU.find(xyTo1D(row, col));
        if (inBounds(row, col-1) && this.isOpen(row, col-1)) { // left
            WQU.union(xyTo1D(row, col-1), root);
        }
        if (inBounds(row, col+1) && this.isOpen(row, col+1)) { // right
            WQU.union(xyTo1D(row, col+1), root);
        }
        if (inBounds(row-1, col) && this.isOpen(row-1, col)) { // up
            WQU.union(xyTo1D(row-1, col), root);
        }
        if (inBounds(row+1, col) && this.isOpen(row+1, col)) { // down
            WQU.union(xyTo1D(row+1, col), root);
        }
    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        // take constant time plus a constant number of calls to the union-find methods.
        validate(row, col);
        return (this.grid[row][col] == 1);
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        // take constant time plus a constant number of calls to the union-find methods.
        validate(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        int idx = xyTo1D(row, col);
        for (int top = 0; top < size; top++) {
            if (WQU.connected(top, idx)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        // number of open sites
        // take constant time plus a constant number of calls to the union-find methods.
        return this.openNum;
    }

    public boolean percolates() {
        // does the system percolate?
        // take constant time plus a constant number of calls to the union-find methods.
        for(int c = 0; c < size; c++) {
            if(isFull(size-1, c)) {
                return true;
            }
        }
        return false;
    }
    // Strong recommendation, write an xyTo1D(int r, int c) method, e.g. xyTo1D(2, 4) = 14
    public int xyTo1D(int row, int col) {
        return (row * size + col);
    }

    // 「越界就抛异常」和「越界就跳过」是两种不同行为，不能放进同一个方法。
    // ① 只判"在不在界内"，返回 boolean，越界不报错 —— 只在邻居检查时使用它
    private boolean inBounds(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    // 校验"调用者传进来的参数"，越界就抛出异常 —— 只在 open / isOpen / isFull 开头用
    private void validate(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException("row/col out of range: " + row + ", " + col);
        }
    }

}

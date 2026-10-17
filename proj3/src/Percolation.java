import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // Adding necessary instance variables.
    private int[][] grid; // 2D Array: 0-blocked, 1-open
    private int size;
    private int openNum;
    private WeightedQuickUnionUF uf;      // 含 顶+底 两个虚拟节点 —— 只给 percolates 用
    private WeightedQuickUnionUF ufTop;   // 只含 顶 虚拟节点    —— 只给 isFull 用

    private int top;      // 顶虚拟节点下标 = N*N
    private int bottom;   // 底虚拟节点下标 = N*N + 1

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        // The constructor should take time proportional to N^2.
        if (N <= 0) {
            throw new IllegalArgumentException("N must greater than 0!");
        }
        grid = new int[N][N];
        size = N;
        openNum = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufTop = new WeightedQuickUnionUF(N * N + 1);
        top = N * N;
        bottom = N * N + 1;
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        // take constant time plus a constant number of calls to the union-find methods.
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        this.grid[row][col] = 1;
        openNum += 1;

        int id = xyTo1D(row, col);
        if (row == 0)         { uf.union(id, top);    ufTop.union(id, top); }  // 顶行 → 顶节点
        if (row == size - 1)  { uf.union(id, bottom); }                        // 底行 → 底节点（ufTop 里没有它）
        // 如果上下左右open，就union
        if (inBounds(row, col - 1) && this.isOpen(row, col - 1)) { // left
            int idLeft = xyTo1D(row, col - 1);
            uf.union(idLeft, id);
            ufTop.union(idLeft, id);
        }
        if (inBounds(row, col + 1) && this.isOpen(row, col + 1)) { // right
            int idRight = xyTo1D(row, col + 1);
            uf.union(idRight, id);
            ufTop.union(idRight, id);
        }
        if (inBounds(row - 1, col) && this.isOpen(row - 1, col)) { // up
            int idUp = xyTo1D(row - 1, col);
            uf.union(idUp, id);
            ufTop.union(idUp, id);
        }
        if (inBounds(row + 1, col) && this.isOpen(row + 1, col)) { // down
            int idDown = xyTo1D(row + 1, col);
            uf.union(idDown, id);
            ufTop.union(idDown, id);
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
//        validate(row, col);
//        if (!isOpen(row, col)) {
//            return false;
//        }
//        int idx = xyTo1D(row, col);
//        for (int top = 0; top < size; top++) {
//            if (WQU.connected(top, idx)) {
//                return true;
//            }
//        }
//        return false;
        validate(row, col);
        // 必须 open，且所在组与"顶虚拟节点"同组
        return isOpen(row, col) && ufTop.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        // number of open sites
        // take constant time plus a constant number of calls to the union-find methods.
        return this.openNum;
    }

    public boolean percolates() {
        // does the system percolate?
        // take constant time plus a constant number of calls to the union-find methods.
//        for(int c = 0; c < size; c++) {
//            if(isFull(size-1, c)) {
//                return true;
//            }
//        }
//        return false;
        // 顶节点和底节点同组 ⟺ 渗流
        return uf.connected(top, bottom);
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

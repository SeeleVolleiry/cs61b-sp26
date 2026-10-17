# Mini-Project 3: Percolation(过滤/渗流)

Lectures needed for this project:
- Lecture 14(Disjoints Sets), or Chapter 14 of the textbook.

Gain an understanding of applying the disjoint sets data structure to a real-world situation.（理解如何把并查集用到真实场景）

# Setup

Follow the `Assignment Workflow Guide` to get started with this assignment. This starter code is in the `proj3` folder.

# Task 1: Percolation

In this program, we’ll write a program to estimate the value of the percolation threshold via Monte Carlo simulation.

渗流模型(Percolation System)的目的是为了探究导体材料与绝缘体材料组成的混合系统，怎样才能成为一个电导体系统。

We model a percolation system using an N-by-N grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full open site in the bottom row.

To model a percolation system, complete the `Percolation` class with the following API:
```java
public class Percolation {
  public Percolation(int N);                // create N-by-N grid, with all sites initially blocked
  public void open(int row, int col);       // open the site (row, col) if it is not open already
  public boolean isOpen(int row, int col);  // is the site (row, col) open?
  public boolean isFull(int row, int col);  // is the site (row, col) full?
  public int numberOfOpenSites();           // number of open sites
  public boolean percolates();              // does the system percolate?
}
```
1. Fill out `Percolation.java` by implementing all the methods given in the above API.

In percolation problem, there is a threshold value p*.

2. Your task is to write a computer program to estimate p*.
    - The WeightedQuickUnionUF class is provided by Princeton’s algs4 library, and has the following API:
   ```java
        public class WeightedQuickUnionUF {
        public WeightedQuickUnionUF(int n);      // creates a UF with n elements
        public int count();                      // number of disjoint sets
        public int find(int p);                  // the root of p's set
        public boolean connected(int p, int q);  // whether p and q are in the same set
        public void union(int p, int q);         // join p and q's sets together
        }
   ```

Note:
- Your code must use the WeightedQuickUnionUF class! It has already been implemented for you, so do not reimplement the Union Find ADT.
- We’ve released a set of videos that contain some hints for how to approach this homework! Please do watch them; they might save you a lot of time on this assignment.

Hint:
- Virtual Sites: virtual top site, virtual bottom site.
- isFull: check for a connection to top site.
- percolates: check for a connection between top site and bottom site.

# Testing



# Task 2: Backwash(回流)

为什么会出现backwash？

Task 1中的 WQUUF 类特定了两个虚拟节点top和bottom。当两条路径都与bottom连接时，即使其中一个原本不应该full的，也会因为另一条full的路径，连接到top。
   
    top site -> path1 -> bottom <--|
    path2 -> bottom <--------------|

两条原本不同的路径，却通过虚拟节点相通了！

解决办法：
- backwash的原因很明白了：因为虚拟底部节点，所以会在isFull方法上判断错误，而percolates方法不会。
- 因此，错误根源在于virtual bottom site。这一虚拟节点存在时，percolates是正确的但isFull不会。该虚拟节点不存在时，两者的正确性又会相反。
- 考虑到时间复杂度的要求，采用虚拟节点是必要的。
- 一个方法需要虚拟的bottom节点，一个不需要。所以，可以建立两个 WQUUF 对象。一个对象有top和bottom（用于percolates），一个对象只有top（用于isFull）。

# Task 3: Percolation Threshold(Ungraded/不计分)

This part of the project will not be graded, but it is highly recommended that you at least read though and think about the following points.

`Monte Carlo simulation`: To estimate the percolation threshold, consider the following computational experiment:
- Initialize all sites to be blocked.
- Repeat the following until the system percolates:
    - Choose a site uniformly at random among all blocked sites.
    - Open the site.
- The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

Open PercolationStats.java and take a look at the provided constructor and methods. Then, run the main method, and interpret the results. What do these numbers tell you about the solution to the Percolation Problem?

If you’ve implemented Percolation.java correctly, you should now have a 95% confidence interval for the percolation threshold p.

# Task 4: Runtime Analysis






# Outputs

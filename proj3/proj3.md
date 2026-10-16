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

# Testing






# Task 2: Backwash






# Task 3: Percolation Threshold






# Task 4: Runtime Analysis






# Outputs

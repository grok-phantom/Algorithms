import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private int[][] grid;
  // creates n-by-n grid, which all sites initially blocked
  public Percolation(int n){
    grid = new int[n][n];
    for(int i=0; i<n; ++i){
      for (int j = 0; j < n; j++) {
        grid[i][j] = 0;
      }
    }
  }

  // open the site (row, col) if it is not open already
  public void open(int row, int col){}

  // is the size (row, col) open?
  public boolean isOpen(int row, int col){return true;}

  // is the size (row, col) full?
  public boolean isFull(int row, int col){return true;}

  // return the number of open sites
  public int numberOfOpenSites(){}

  // does the system percolate
  public boolean percolates(){}


  // test client (optional)
  public static void main(String[] args) {

  }
}


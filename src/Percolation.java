import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * The type Percolation.
 */
public class Percolation {
  private final State[][] grid;
  private final WeightedQuickUnionUF weightedQuickUnionUF;
  private int countOpen = 0;

  /**
   * creates n-by-n grid, which all sites initially blocked.
   *
   * @param n the n
   */
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException();
    }
    grid = new State[n][n];
    weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; j++) {
        grid[i][j] = State.BLOCKED;
      }
    }
  }


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int n = 10;
    Percolation percolation = new Percolation(n);
    System.out.println(percolation.percolates());
  }

  /**
   * Open.
   *
   * @param row the row
   * @param col the col
   */
  public void open(int row, int col) {
    if (!validate(row, col)) {
      throw new IllegalArgumentException("WRONG COORDINATE");
    }
    if (isOpen(row, col)) {
      return;
    }

    grid[row - 1][col - 1] = (row-1 == 0) ? State.FULL :State.OPEN;
    countOpen += 1;

    addUnion(row, col, -1, 0);
    addUnion(row, col, 1, 0);
    addUnion(row, col, 0, -1);
    addUnion(row, col, 0, 1);
  }

  /**
   * Is open boolean.
   *
   * @param row the row
   * @param col the col
   * @return the boolean
   */
  public boolean isOpen(int row, int col) {
    if (!validate(row, col)) {
      throw new IllegalArgumentException();
    }

    return grid[row - 1][col - 1] != State.BLOCKED;
  }

  /**
   * Is full boolean.
   *
   * @param row the row
   * @param col the col
   * @return the boolean
   */
  public boolean isFull(int row, int col) {
    if (!validate(row, col)) {
      throw new IllegalArgumentException();
    }
    if (!isOpen(row, col)) {
      return false;
    }
    for (int i = 0; i < grid.length; ++i) {
      if (isOpen(1, i + 1) && weightedQuickUnionUF.find(i) == weightedQuickUnionUF.find(
              (row - 1) * grid.length + col - 1)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Number of open sites int.
   *
   * @return the int
   */
  public int numberOfOpenSites() {
    return countOpen;
  }

  private boolean validate(int row, int col) {
    return row > 0 && row <= grid.length && col > 0 && col <= grid.length;
  }

  private void addUnion(int row, int col, int rowShift, int colShift) {
    int newRow = row + rowShift;
    int newCol = col + colShift;
    if (validate(newRow, newCol) && isOpen(newRow, newCol)) {
      weightedQuickUnionUF.union((row - 1) * grid.length + col - 1,
              (newRow - 1) * grid.length + newCol - 1);
      int rootNewSite = weightedQuickUnionUF.find((newRow-1)*grid.length + newCol -1);
    }
  }

  /**
   * Percolates boolean.
   *
   * @return the boolean
   */
  public boolean percolates() {
    for (int i = 0; i < grid.length; ++i) {
      if (isFull(grid.length, i + 1)) {
        return true;
      }
    }
    return false;
  }


  private enum State {
    /**
     * Blocked state.
     */
    BLOCKED,
    /**
     * Open state.
     */
    OPEN,
    /**
     *  Full state.
     */
    FULL
  }
}


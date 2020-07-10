import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * The type Percolation.
 */
public class Percolation {
  private final State[] grid;
  private final int nn;
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
    grid = new State[n * n];
    nn = n;
    weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; j++) {
        grid[i * n + j] = State.BLOCKED;
      }
    }
  }


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int n = 3;
    byte a = 8;
    System.out.println(a&0x4);
//    Percolation percolation = new Percolation(n);
//    percolation.open(1, 2);
//    System.out.println(percolation.percolates());
//    percolation.open(2, 3);
//    percolation.open(2, 2);
//    System.out.println(percolation.percolates());
//    percolation.open(3, 2);
//    System.out.println(percolation.percolates());
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

    grid[toPt(row, col)] = (row - 1 == 0) ? State.FULL : State.OPEN;
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

    return grid[toPt(row, col)] != State.BLOCKED;
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
    int root = weightedQuickUnionUF.find(toPt(row, col));
    return grid[root] == State.FULL;
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
    return row > 0 && row <= nn && col > 0 && col <= nn;
  }

  private void addUnion(int row, int col, int rowShift, int colShift) {
    int newRow = row + rowShift;
    int newCol = col + colShift;
    int pt = toPt(row, col);
    int newPt = toPt(newRow, newCol);
    if (validate(newRow, newCol) && isOpen(newRow, newCol)) {
      int rootNewSite = weightedQuickUnionUF.find(newPt);
      if (grid[pt] == State.FULL && grid[rootNewSite] != State.FULL) {
        grid[rootNewSite] = State.FULL;
      } else if (grid[pt] != State.FULL && grid[rootNewSite] == State.FULL) {
        grid[pt] = State.FULL;
      }
      weightedQuickUnionUF.union(pt, newPt);
    }
  }

  private int toPt(int row, int col) {
    return (row - 1) * nn + col - 1;
  }

  /**
   * Percolates boolean.
   *
   * @return the boolean
   */
  public boolean percolates() {
    for (int i = 0; i < nn; ++i) {
      if (isFull(nn, i + 1)) {
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
     * Full state.
     */
    FULL
  }
}


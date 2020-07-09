import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


/**
 * The type Percolation stats.
 */
public class PercolationStats {
  private static final double CONFIDENCE_95 = 1.96;
  private final double[] stat;
  private final int mtrails;

  /**
   * Instantiates a new Percolation stats.
   *
   * @param n      the n
   * @param trails the trails
   */
  public PercolationStats(int n, int trails) {
    if (n <= 0 || trails <= 0) {
      throw new IllegalArgumentException("Wrong argument");
    }

    stat = new double[trails];
    mtrails = trails;
    int squareN = n * n;
    for (int i = 0; i < mtrails; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int randRow = StdRandom.uniform(n);
        int randCol = StdRandom.uniform(n);
        percolation.open(randRow + 1, randCol + 1);
      }
      stat[i] = percolation.numberOfOpenSites() / (double) squareN;
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    PercolationStats percolationStats = new PercolationStats(64, 150);
    System.out.printf("mean = %f%n", percolationStats.mean());
    System.out.printf("stddev = %f%n", percolationStats.stddev());
    System.out.printf("95%% confidence interval = [%f, %f]%n",
            percolationStats.confidenceLo(), percolationStats.confidenceHi());
  }

  /**
   * Mean double.
   *
   * @return the double
   */
  public double mean() {
    return StdStats.mean(stat);
  }

  /**
   * Stddev double.
   *
   * @return the double
   */
  public double stddev() {
    return StdStats.stddev(stat);
  }

  /**
   * Confidence lo double.
   *
   * @return the double
   */
  public double confidenceLo() {
    return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(mtrails);
  }

  /**
   * Confidence hi double.
   *
   * @return the double
   */
  public double confidenceHi() {
    return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(mtrails);
  }
}

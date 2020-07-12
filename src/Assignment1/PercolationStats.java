package Assignment1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * The type Percolation stats.
 */
public class PercolationStats {
  private static final double CONFIDENCE_95 = 1.96;
  private final int trialCount;
  private final double[] trialResults;


  /**
   * Instantiates a new Percolation stats.
   *
   * @param n      the n
   * @param trials the trials
   */
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("N and T must be <= 0");
    }
    int gridSize = n;
    trialCount = trials;
    trialResults = new double[trialCount];

    for (int trial = 0; trial < trialCount; trial++) {
      Percolation percolation = new Percolation(gridSize);
      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, gridSize + 1);
        int col = StdRandom.uniform(1, gridSize + 1);
        percolation.open(row, col);
      }
      int openSites = percolation.numberOfOpenSites();
      double result = (double) openSites / (gridSize * gridSize);
      trialResults[trial] = result;
    }
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    int gridSize = 10;
    int trialCount = 10;
    if (args.length >= 2) {
      gridSize = Integer.parseInt(args[0]);
      trialCount = Integer.parseInt(args[1]);
    }
    PercolationStats ps = new PercolationStats(gridSize, trialCount);

    StdOut.printf("mean                    = %f%n", ps.mean());
    StdOut.printf("stddev                  = %f%n", ps.stddev());
    StdOut.printf("95%% confidence interval = [%f, %f]%n", ps.confidenceLo(), ps.confidenceHi());
  }

  /**
   * Mean double.
   *
   * @return the double
   */
  public double mean() {
    return StdStats.mean(trialResults);
  }

  /**
   * Stddev double.
   *
   * @return the double
   */
  public double stddev() {
    return StdStats.stddev(trialResults);
  }

  /**
   * Confidence lo double.
   *
   * @return the double
   */
  public double confidenceLo() {
    return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trialCount));

  }

  /**
   * Confidence hi double.
   *
   * @return the double
   */
  public double confidenceHi() {
    return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trialCount));
  }
}
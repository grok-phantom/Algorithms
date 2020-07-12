package Assignment1;

public class QuickUnionUN {
  private final int[] id;
  private int[] sz;

  public QuickUnionUN(int N) {
    id = new int[N];
    sz = new int[N]; // add for weight_union
    for (int i = 0; i < N; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }

  private int root(int i) {
//    while (i != id[i]) i = id[i];
    while (i != id[i]){
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  private void union(int p, int q) {
    int i = root(p);
    int j = root(q);
    id[i] = j;
  }

  public void weight_union(int p, int q) {
    int i = root(p);
    int j = root(q);
    if (i == j) return;
    if (sz[i] < sz[j]) {
      id[i] = j;
      sz[j] = sz[i];
    } else {
      id[j] = i;
      sz[i] = sz[j];
    }
  }

}
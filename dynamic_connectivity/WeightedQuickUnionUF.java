
/*
 * Weighted quick-union
 * - Modify quick-union to avoid tall trees.
 * - Keep track of size of each tree (number of objects).
 * - Balance by linking root of smaller tree to root of bigger tree.
 */


public class WeightedQuickUnionUF
{
  private final int[] id;
  private final int[] treeSize;  
  private int numConnected;

  public WeightedQuickUnionUF(int numComponents)
  {
    id = new int[numComponents];
    treeSize = new int[numComponents];
    numConnected = numComponents;

    for (int i = 0; i < numComponents; i++)
    {
      id[i] = i;
      treeSize[i] = 1;
    }

  }


  public int find(int component)
  {
    while (id[component] != component)
    { 
      id[component] = id[id[component]]; // path compression
      component = id[component];
    }
    return component;
  }

  /*
   * p and q are connected iff they have the same root
   */
  public boolean connected(int p, int q)
  {
    return find(p) == find(q);
  }

  /*
   * change the root of p to that of q
   */
  public void union(int p, int q)
  {
    final int rp = find(p);
    final int rq = find(q);

    if (rp == rq) return;

    if (treeSize[rp] < treeSize[rq])
    {
      id[rp] = rq;
      treeSize[rq] += treeSize[rp];
    }
    else
    {
      id[rq] = rp;
      treeSize[rp] += treeSize[rq];
    }

    --numConnected;
  }

  private void print(final int[] array, final String label)
  {
    final int padding = label.length();
    for (int i=0; i<padding+2+(2*array.length); i++)
      System.out.printf("=");
    System.out.println("");

    System.out.printf("%-" + (padding+2) + "s", " ");
    for (int i = 0; i < array.length; i++)
      System.out.printf("%3d", i);
    System.out.println("");

    System.out.printf("%-" + padding + "s%s", label, "[]");
    for (int i = 0; i < array.length; i++)
      System.out.printf("%3d", array[i]);
    System.out.println("");
  }

  public void print()
  {
    print(id, "id");
    print(treeSize, "tree size");
  }

  public static void main(String[] args)
  {
    WeightedQuickUnionUF qf = new WeightedQuickUnionUF(10);
    qf.union(4,3);
    qf.union(3,8);
    qf.union(6,5);
    qf.union(9,4);
    qf.union(2,1);
    qf.union(5,0);
    qf.union(7,2);
    qf.union(6,1);
    qf.union(7,3);
    qf.print();
   
    /* ======= Expected Result =======
    index 0  1  2  3  4  5  6  7  8  9
     id[] 6  2  6  4  6  6  6  6  4  4
    */
  }
}
public class QuickUnionUF
{
    private int[] id;

  public QuickUnionUF(int N)
  {
    id = new int[N];
    for (int i = 0; i < N; i++)
      id [i] = i;
  }

  /*
   * p and q are connected iff they have the same root
   */
  public boolean connected(int p, int q)
  {
    return root(p) == root(q);
  }

  /*
   * Merge components containing p and q by changing the root of p to
   * the root of q
   */
  public void union(int p, int q)
  {
    id[root(p)] = root(q);
  }

  private int root(int i)
  {
    while (i != id[i])
      i = id[i];

    return i;
  }

  public int count()
  {
    return id.length;
  }

  public int find(int p)
  {
    for (int i = 0; i < id.length; ++i)
      if (id[i] == p) return i;

    return -1;
  }

  public void printConnectivity()
  {
    int N = id.length;

    for (int i1 = 0; i1 < N; i1++)
    {
      System.out.print(i1);
      for (int i2 = 0; i2 < N; i2++)
      {
        if (i1 != i2 && connected(i1, i2))
        {
          System.out.print(" --> " + i2);
        }
      }
      System.out.println("");
    }
  }

  public void printID()
  {
    int N = id.length;

    System.out.println("========id[]=========");
    for (int i = 0; i < N; i++)
    {
      System.out.print(" " + i);
    }
    System.out.println("");

    for (int i = 0; i < N; i++)
    {
      System.out.print(" " + id[i]);
    }
    System.out.println("");

  }

  public static void main(String[] args)
  {
    QuickUnionUF qf = new QuickUnionUF(10);
    qf.union(4,3);
    qf.union(3,8);
    qf.union(6,5);
    qf.union(9,4);
    qf.union(2,1);
    qf.connected(8,9);
    qf.connected(5,4);
    qf.union(5,0);
    qf.union(7,2);
    qf.union(6,1);
    qf.union(7,3);
    qf.printConnectivity();
    qf.printID();

    /* Expected result
       ========id[]=======
       0 1 2 3 4 5 6 7 8 9
       1 8 1 8 3 0 5 1 8 8
    */
   
  }
}
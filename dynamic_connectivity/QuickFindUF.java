// Implementation of Quick-find (eager approach) from the Algorithms course from
// Princeton University on Coursera
// initialisation - N
// union - N
// find  - 1
// Takes N^2 array accesses to process sequence of N union commands on N objects



public class QuickFindUF
{
  private int[] id;

  public QuickFindUF(int N)
  {
    id = new int[N];
    for (int i = 0; i < N; i++)
      id [i] = i;
  }

  /*
   * p and q are connected iff they have the same id
   */
  public boolean connected(int p, int q)
  {
    return id[p] == id[q];
  }

  /*
   * Merge components containing p and q by changing all entries
   * whose id equals id[p] to id[q].
   * E.g. union (4, 3), change ID of the first to that of the second one.
   */
  public void union(int p, int q)
  {
    final int pid = id[p];

    for (int i = 0; i < id.length; ++i)
      if (id[i] == pid) id[i] = id[q];
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
    QuickFindUF qf = new QuickFindUF(10);
    qf.union(4,3);
    qf.union(3,8);
    qf.union(6,5);
    qf.union(9,4);
    qf.union(2,1);
    qf.connected(8,9);
    qf.connected(5,0);
    qf.union(5,0);
    qf.union(7,2);
    qf.union(6,1);
    qf.printConnectivity();
    qf.printID();

    /* Expected result
       ========id[]=======
       0 1 2 3 4 5 6 7 8 9
       1 1 1 8 8 1 1 1 8 8
    */
   
  }

}
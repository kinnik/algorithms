import java.util.*;

public class Selection
{
    public static Integer[] top(Comparable[] a, int k)
    {
        shuffle(a);

        int lo = 0, hi = a.length-1;
        int j1;
        int j2 = a.length-k;

        while (lo < hi)
        {
            j1 = partition(a, lo, hi);
            

            if      (j1 > j2) hi = j1-1;
            else if (j1 < j2) lo = j1+1;
            else              break;
        }

        Integer[] results = new Integer[k];

        for (int i = j2; i < a.length; ++i)
            results[i-j2] = (Integer) a[i];
        
        return results;
    }

    public static void main(String args[])
    {
        Integer[] a = new Integer[100];
        for (int i=0; i < a.length; ++i)
        {
            if (i > 90) a[i] = new Integer(100);
            else        a[i] = new Integer(i);
        }

        Integer[] results = Selection.top(a, 10);
        show(results);
    }

    private static void show(Comparable[] a)
    {
        for (int i = 0; i < a.length; ++i)
            System.out.print(a[i] + " ");

        System.out.println();
    }

    private static void exchange(Comparable[] a, int i, int j)
    {
        final Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void shuffle(Comparable[] a)
    {
        final Random random = new Random();
        
        for (int i = a.length-1; i > 0; --i)
        {
            final int r = random.nextInt(i+1);
            exchange(a, i, r);
        }
    }

    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo;
        int j = hi+1;

        while (true)
        {
            while (less(a[++i], a[lo]))
                if (i == hi) break;

            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            exchange(a, i, j);
        }

        exchange(a, lo, j);
        return j;
    }

    private static boolean less(Comparable a, Comparable b)
    {
        return a.compareTo(b) < 0;
    }
}

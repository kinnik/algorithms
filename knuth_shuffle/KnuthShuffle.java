import java.util.Random;

public class KnuthShuffle
{
    // creates a random number generator
    protected static Random random = new Random();

    // disallow instantiation
    private KnuthShuffle() {}

    // shuffle the elements in the given array using the Knuth shuffle algorithm
    public static void shuffle(Object[] a)
    {
        for (int i = a.length-1; i >= 1; --i)
        {
            // get a random number between 0 and i inclusive (hence i+1)
            final int r = random.nextInt(i+1);
            exchange(a, i, r);
        }
    }

    // swaps the position of the two elements at the given indeces in the array a
    protected static void exchange(Object[] a, int i, int j)
    {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // unit-test
    public static void main(String args[])
    {
        String[] eeny = {"eeny", "meeny", "miny", "moe"};
        KnuthShuffle.shuffle(eeny);
        for (String s: eeny)
            System.out.print(s + "\t");

        System.out.println();
    }
}

/**
 * Reverses a given array.
 */

import java.util.LinkedList;

public class ReverseArray {

    // using an extra space
    // time O(N); space O(N)
    public static int[] reverse(int[] a) {
        final int       last = a.length - 1;
        final int[] reversed = new int[a.length];

        for (int i = last; i >= 0; --i)
            reversed[last-i] = a[i];

        return reversed;
    }

    // using an extra space and external data structure
    // time O(N); space O(N)
    public static int[] reverse2(int[] a) {
        LinkedList<Integer> stack = new LinkedList<Integer>();

        for (int i = 0; i < a.length; ++i)
            stack.addFirst(a[i]);

        final int[] reversed = new int[a.length];
        int index = 0;
        for (int i: stack)
            reversed[index++] = i;

        return reversed;
    }


    // using no extra space; modifies the given array
    // time O(N); space O(1)
    public static void reverse3(int[] a) {
        final int last = a.length - 1;
        final int  mid = a.length / 2;

        for (int i = 0; i < mid; ++i) {
            final int tmp = a[i];
            a[i]      = last-i;
            a[last-i] = tmp;
        }

    }

    private static void print(int[] a) {
        for (int i: a)
            System.out.print(i + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < a.length; ++i)
            a[i] = i;

        print(a);
        reverse3(a);

        print(a);
        a = reverse2(a);

        print(a);
        a = reverse(a);

        print(a);
    }
}


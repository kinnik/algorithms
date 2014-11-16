/**
 * Implementation of Josephus problem (Exercise 1.3.37 from Algorithms
 * 4th Edition by Robert Sedgewick and Kevin Wayne) using a Queue.
 * In the Josephus problem from antiquity, N people are in dire straits
 * and agree to the following strategy to reduce the population. They
 * arrange themselves in a circle (at positions numbered from O to N-1) and
 * proceed around the circle, eliminating every Mth person until only one
 * person is left. Legend has it that Josephus figured out where to sit to
 * avoid being eliminated. Write a Queue client Josephus that takes M and N
 * from the command line and prints out the order in which people are
 * eliminated (and thus would show Josephus where to sit in the circle).
 * $ java Josephus 2 7
 * 1 3 5 0 4 2 6
 */

package algorithms.queues;

// Uses the Queue datastructure as required by the problem.
import data_structures.queues.*;

public class Josephus
{
    private int M, N;
    private Queue<Integer> people;

    /**
     * Places people in the 'circle'
     * throws IllegalArgumentException if M is less than or equal to zero
     * throws IllegalArgumentEXception if N is less than or equal to zero
     * throws IllegalArgumentException if N is less than M
     */
    public Josephus(int M, int N)
    {
        if (M <= 0) throw new IllegalArgumentException();
        if (N <= 0) throw new IllegalArgumentException();
        if (N <  M) throw new IllegalArgumentException();

        this.M = M;
        this.N = N;
        people = new Queue<Integer>();

        for (int i = 0; i < N; ++i) { people.enqueue(i); }
    }

    public void printEliminationOrder()
    {
        int personExamined = 0;
        int counter = 0;
        while (!people.isEmpty())
        {
            personExamined = people.dequeue();
            ++counter;

            if (counter % M != 0) people.enqueue(personExamined);
            else                  System.out.print(personExamined + " ");
        }

        System.out.println();
    }

    public static void main(String args[])
    {
        if (args.length != 2)
            throw new IllegalArgumentException("Usage: Josephus M N (where M is the position to be eliminated and N is the total number of people)");
        
        final int M = Integer.parseInt(args[0]);
        final int N = Integer.parseInt(args[1]);

        Josephus josephus = new Josephus(M, N);
        josephus.printEliminationOrder();
    }
 }

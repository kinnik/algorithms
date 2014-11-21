/**
 * An integer array contains elements in increasing order till some point and 
 * then decreasing order. 
 * Return the index of maximum number.
 * Solution should be less than O(n). Ex - {1,2,3,4,5,3,1}
 */

public class BinarySearchMax
{
    public static int find(int[] numbers)
    {
        int lo = 0;
        int hi = numbers.length - 1;

        while (lo < hi)
        {
            final int mid = (hi+lo)/2;

            if (numbers[mid] < numbers[mid+1])  lo = mid+1;
            else                                hi = mid;
        }

        return lo;
    }

    public static void main(String[] args)
    {
        final int[] numbers_1 = {1,2,3,4,5,3,1};
        final int[] numbers_2 = {1,2,3,4,5,5,3,1};
        final int[] numbers_3 = {1,2,2,3,4,5,5,3,3,3,1,1};
        final int[] numbers_4 = {1,15,12,11,10,9,8,7,6,5};
        final int[] numbers_5 = {1,2,3,4,5,9,11,88,4,3,1};

        final int ans_1 = 4;
        final int ans_2 = 4;
        final int ans_3 = 5;
        final int ans_4 = 1;
        final int ans_5 = 7;

        assert ans_1 == find(numbers_1);
        assert ans_2 == find(numbers_2);
        assert ans_3 == find(numbers_3);
        assert ans_4 == find(numbers_4);
        assert ans_5 == find(numbers_5);

        System.out.println(numbers_1[find(numbers_1)]);
        System.out.println(numbers_2[find(numbers_2)]);
        System.out.println(numbers_3[find(numbers_3)]);
        System.out.println(numbers_4[find(numbers_4)]);
        System.out.println(numbers_5[find(numbers_5)]);

    }
}

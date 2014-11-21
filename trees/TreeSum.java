/**
 *
 *     6
 *    / \
 *    3  5
 *   / \  \
 *   2  5  4
 *     / \
 *    7   4
 *
 * 6->3->2    =  632 
 * 6->3->5->7 = 6357 
 * 6->3->5->4 = 6354 
 * 6->5->4    =  654 
 *
 * Answer: 632+6357+6354+654=13997
 *
 * javac algorithms/trees/TreeSum.java && java -ea algorithms.trees.TreeSum
 */

 package algorithms.trees;

 import java.util.*;

 public class TreeSum
 {
     public static int findSum(TreeNode node)
     {
        final int   depth = depth(node);
        final int[] path  = new int[depth];

        final LinkedList<Integer> collectedSums = new LinkedList<Integer>();
        findSum(node, path, collectedSums, 0);
       
        int total = 0; 
        for (Integer pathSum: collectedSums) total+=pathSum;
        
        return total;
     }

     private static void findSum(TreeNode node, int[] path, LinkedList<Integer> collectedSums,
                                 int level)
     {
         if (node == null)
         {
             return;
         }
         else
         {
             path[level] = node.data;

             if (node.left == null && node.right == null)
             {
                int sum = 0;
                for (int i = level; i >= 0; --i)
                    sum += path[i] * Math.pow(10, (level-i));

                collectedSums.add(sum);
             }
             else
             {
                findSum(node.left,  path, collectedSums, level+1);
                findSum(node.right, path, collectedSums, level+1);
                path[level] = 0;
             }
         }
     }

     private static int depth(TreeNode n)
     {
         if (n == null) return 0;

         return 1 + Math.max(depth(n.left), depth(n.right));
     }

     private static class TreeNode
     {
         TreeNode left, right;
         int data;

         TreeNode(int data) { this.data = data; }
     }

     public static void main(String[] args)
     {
         TreeNode root = new TreeNode(6);

                   root.left  = new TreeNode(3);
                   root.right = new TreeNode(5);

               root.left.left = new TreeNode(2);
              root.left.right = new TreeNode(5);
             root.right.right = new TreeNode(4);
                                      
         root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        final int answer   = 13997;
        final int totalSum = findSum(root);
        assert answer == totalSum;
        System.out.println(totalSum);
     }
 }

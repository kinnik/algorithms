/**
  * Write a program that breaks up a string of words with no spaces into
  * a string with the appropriate spaces.
  * Fewest number of words must be returned.
  * e.g. peanutbutterfly -> peanut, butterfly
  */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class WordTokeniser
{
    final RWayTrie<Integer> trie;

    public WordTokeniser(String dictionaryPath) throws FileNotFoundException
    {
        trie = new RWayTrie<Integer>();
        final Scanner scanner = new Scanner(new File(dictionaryPath));

        // Trie is a key-value data structure. By using a position as a value
        // we can use the retrieved value to further retrieve associated 
        // values, such as definitions in the case of dictionary
        int wordPosition = 0;
        while (scanner.hasNext()) trie.put(scanner.next(), wordPosition++);

        assert trie.size() == wordPosition;
    }

    public Iterable<String> tokenise(String query)
    {
        final List<String> queue = new LinkedList<String>();
        final String     upQuery = query.toUpperCase();

        int begin = 0;
        String token;

        while (begin < upQuery.length())
        {
            // get the longest prefix length
            final int length = trie.search(trie.root, upQuery.substring(begin), 0, 0);

            if (length == 0) break; // no more valid words

            // slice the string and save it in the queue
            final int end = begin+length;

            token = upQuery.substring(begin, end);
            queue.add(token);

            // move the beginning index
            begin = end;
        }

        return queue;
    }

    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.err.println("Usage: WordTokeniser <path/to/dictionary> <concatenated_words>");
            System.exit(0);
        }

        final String    dictionaryPath = args[0];
        final String concatenatedWords = args[1];
        try
        {
            final WordTokeniser tokeniser = new WordTokeniser(dictionaryPath);
            final Iterable<String> tokens = tokeniser.tokenise(concatenatedWords);
            for (String s: tokens) System.out.println("> " + s);
        }
        catch (FileNotFoundException e)
        {
            System.err.println(dictionaryPath + " not found.");
            System.exit(-1);
        }
    }

    /** A simplified 26-way trie implementation based on the version
      * developed by Robert Sedgewick and Kevin Wayne
      * http://algs4.cs.princeton.edu/52trie/TrieST.java.html
      */
    private static class RWayTrie<Value>
    {
        private static final int  R = 26;
        private static final char UPPERCASE_OFFSET = 'A';

        private Node root;
        private int  count;

        public void put(String key, Value value)
        {
            root = put(root, key, value, 0);
        }

        private Node put(Node node, String key, Value value, int depth)
        {
            if (node == null) node = new Node();

            if (depth == key.length())
            {
                if (node.value == null) ++count; // in case it already exists

                node.value = value;
                return node;
            }

            final int c  = key.charAt(depth) - UPPERCASE_OFFSET;
            node.next[c] = put(node.next[c], key, value, depth+1);

            return node;
        }

        public Value get(String key)
        {
            final Node node = get(root, key, 0);
            
            if (node == null) return null;

            return (Value) node.value;
        }

        private Node get(Node node, String key, int depth)
        {
            if (node == null) return null;

            if (depth == key.length()) return node;

            final int c = key.charAt(depth) - UPPERCASE_OFFSET;

            return get(node.next[c], key, depth+1);
        }

        public int size()
        {
            return count;
        }

        private void collect(Node node, String prefix, List<String> q)
        {
            if (node == null) return;

            if (node.value != null) q.add(prefix);

            for (char c = 0; c < R; ++c)
                collect(node.next[c], prefix + c, q);
        }

        private int search(Node node, String query, int depth, int length)
        {
            if (node == null)   return length;

            int maxLength = length;
            if (node.value != null) maxLength = depth;

            if (depth == query.length()) return maxLength;

            final int c = query.charAt(depth) - UPPERCASE_OFFSET;
            return search(node.next[c], query, depth+1, maxLength);
        }

        private static class Node
        {
            private Object value;
            private Node[] next = new Node[R];
        }
    }
}

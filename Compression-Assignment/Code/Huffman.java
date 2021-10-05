/******************************************************************************
 *  Compilation:  javac Huffman.java
 *
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 * Add instructions and documentation related to your Huffman algorithm here...
 *
 ******************************************************************************/

/**
 * Add in your information about each method etc. here
 *
 *
 * @author Ethan Hammond
 */
public class Huffman {

    // alphabet size of extended ASCII
    private static final int R = 256;
    private static BinaryIn binaryIn;
    private static BinaryOut binaryOut;

    // Do not instantiate.
    private Huffman() {
    }

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them using
     * Huffman codes with an 8-bit alphabet; and writes the results to standard
     * output.
     */
    public static void compress() {
        // Read the input
        char[] input = binaryIn.readString().toCharArray();

        // Tabulate frequency counts, build Huffman trie and code table
        int[] frequency = new int[R];
        for (char ch : input) {
            frequency[ch]++;
        }
        Node head = buildTrie(frequency);
        String[] table = new String[R];
        buildCode(table, head, "");
        // build Huffman trie
        writeTrie(head);
        // build code table
        buildCode(table, head, "");
        // print number of bytes in original uncompressed message
        binaryOut.write(input.length);

        // use Huffman code to encode input
        for (char ch : input) {
            for (char code : table[ch].toCharArray()) {
                binaryOut.write(code == '1');
            }
        }
        binaryOut.close();
    }

    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void decompress() {

        Node head = readTrie();
        int numBits = binaryIn.readInt(); // Bytes in original message
        int i = 0;
        
        while (i++ < numBits) {
            Node node = head;
            // decode using the Huffman trie
            while (!node.isLeaf()) {
                // read in Huffman trie from input stream
                node = binaryIn.readBoolean() ? node.right : node.left;
            }
            binaryOut.write(node.ch, 8);
        }

        binaryOut.close();

    }

    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq) {

        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0)
                pq.insert(new Node('\0', 0, null, null));
            else
                pq.insert(new Node('\1', 0, null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            binaryOut.write(true);
            binaryOut.write(x.ch, 8);
            return;
        }
        binaryOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left, s + '0');
            buildCode(st, x.right, s + '1');
        } else {
            st[x.ch] = s;
        }
    }

    private static Node readTrie() {
        boolean isLeaf = binaryIn.readBoolean();
        if (isLeaf) {
            return new Node(binaryIn.readChar(), -1, null, null);
        } else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    // Counts Bits
    private static int bitCounter(String input) {
        int count = 0;
        BinaryIn binaryIn = new BinaryIn(input);

        while (!binaryIn.isEmpty()) {
            binaryIn.readBoolean();
            count++;
        }

        return count;
    }

    /**
     * Sample client that calls {@code compress()} if the command-line argument is
     * "compress" an {@code decompress()} if it is "decompress".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if (args.length != 3 && !args[0].equals("compress") && !args[0].equals("decompress")) {
            throw new IllegalArgumentException();
        }

        binaryIn = new BinaryIn(args[1]);
        binaryOut = new BinaryOut(args[2]);

        if (args[0].equals("compress")) {
            Stopwatch compressionTimer = new Stopwatch();
            compress();
            StdOut.printf("\nTime taken:\t" + compressionTimer.elapsedTime());

            double ogBits = bitCounter(args[1]);
            double compBits = bitCounter(args[2]);
            double compRatio = (compBits / ogBits) * 100;

            StdOut.printf("\nInput:\t%s", args[1]);
            StdOut.printf("\nCompressed:\t%s", args[2]);
            StdOut.printf("\nOriginal bits:\t" + ogBits);
            StdOut.printf("\nCompressed bits:\t" + compBits);
            StdOut.printf("\nRatio:\t" + compRatio);

        } else {

            Stopwatch compressionTimer = new Stopwatch();
            decompress();
            StdOut.printf("\nTime taken:\t" + compressionTimer.elapsedTime());

            double compBits = bitCounter(args[2]);

            StdOut.printf("\nInput:\t%s", args[1]);
            StdOut.printf("\nDecompressed:\t%s", args[2]);
            StdOut.printf("\nDecompressed bits:\t" + compBits);
        }
        

    }

}

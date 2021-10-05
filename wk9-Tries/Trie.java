//import Trie.TrieNode;

public class Trie {

    // Alphabet size (# of symbols) we pick 26 for English alphabet
    static final int ALPHABET_SIZE = 26;

    // class for Trie node
    static class TrieNode {

        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        // isEndOfWord is true if the node represents end of a word i.e. leaf node
        boolean isEndOfWord;

        TrieNode() {

            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }

    }

    static TrieNode root;

    // If not key present, inserts into trie
    // If the key is prefix of Trie node,
    // marks leaf node
    static void insert(String key) {

        int height;
        int index;

        TrieNode current = root;

        for (height = 0; height < key.length(); height++) {

            index = key.charAt(height) - 'a';

            if (current.children[index] == null) {

                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEndOfWord = true;
    }

    // Returns true if key presents in trie, else false
    static boolean search(String key) {

        int height;
        int letter;

        TrieNode current = root;

        for (height = 0; height < key.length(); height++) {

            letter = key.charAt(height) - 'a';

            // Return false if key is not present
            if (current.children[letter] == null) {
                return false;
            }

            current = current.children[letter];
        }

        return (current != null && current.isEndOfWord == true);

    }

    // Driver
    public static void main(String args[]) {

        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = { "bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver" };
        String output[] = { "Not present in trie", "Present in trie" };
        root = new TrieNode();
        // Construct trie
        int i;

        for (i = 0; i < keys.length; i++) {

            insert(keys[i]);
        }
        // Search for different keys

        if (search("bank") == true) {
            System.out.println(output[1]);
        } else {
            System.out.println(output[0]);
        }
    }

    // end of class
}
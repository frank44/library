package datastructures;

/**
 * Trie implementation that is backed by an array. Use one of the static factory methods to create one of the predefined
 * tries.
 *
 * Static Factory Methods
 *      createLowercaseTrie() - Memory efficient but only supports a-z.
 *      createAsciiTrie() - Supports all printable ASCII characters at the cost of ~3x the memory.
 *
 * Supported Operations
 *      void add(String str) - Adds the given string into the trie.
 *      int getPrefixCount(String str) - Returns the number of strings in the trie with a certain prefix.
 *      int getWordCount(String str) - Returns the number of words in the trie that exactly match str.
 *      int size() - Return the total number of strings in the trie.
 *      ArrayTrie moveTo(char a) - Returns the child trie specified by the given character. This is useful for
 *          iterating through the tree nodes.
 */
public class ArrayTrie {
    private static int ALPHABET_SIZE;
    private static char CHAR_OFFSET;

    private int wordCount = 0;
    private int prefixCount = 0;
    private ArrayTrie[] next;

    private ArrayTrie(int alphabetSize, char startingChar) {
        ALPHABET_SIZE = alphabetSize;
        CHAR_OFFSET = startingChar;
        next = new ArrayTrie[ALPHABET_SIZE];
    }

    private ArrayTrie() {
        next = new ArrayTrie[ALPHABET_SIZE];
    }

    public static ArrayTrie createLowercaseTrie() {
        return new ArrayTrie(26, 'a');
    }

    // Supports all printable ascii characters, values 32 - 126
    public static ArrayTrie createAsciiTrie() {
        return new ArrayTrie(95, ' ');
    }

    public void add(String str) {
        ArrayTrie cur = this;
        this.prefixCount++;

        for (int i=0; i<str.length(); i++) {
            cur = cur.getOrSet(str.charAt(i));
            cur.prefixCount++;
        }
        cur.wordCount++;
    }

    public int getPrefixCount(String str) {
        ArrayTrie cur = this;
        for (int i=0; i<str.length(); i++) {
            cur = cur.next[str.charAt(i) - CHAR_OFFSET];
            if (cur == null) {
                break;
            }
        }
        if (cur == null) {
            return 0;
        }
        return cur.prefixCount;
    }

    public int getWordCount(String str) {
        ArrayTrie cur = this;
        for (int i=0; i<str.length(); i++) {
            cur = cur.next[str.charAt(i) - CHAR_OFFSET];
            if (cur == null) {
                break;
            }
        }
        if (cur == null) {
            return 0;
        }
        return cur.wordCount;
    }

    public int size() {
        return prefixCount;
    }

    public ArrayTrie moveTo(char a) {
        return next[a - CHAR_OFFSET];
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getPrefixCount() {
        return prefixCount;
    }

    private ArrayTrie getOrSet(char a) {
        ArrayTrie t = next[a - CHAR_OFFSET];
        if (t == null) {
            return next[a - CHAR_OFFSET] = new ArrayTrie();
        }
        return t;
    }
}
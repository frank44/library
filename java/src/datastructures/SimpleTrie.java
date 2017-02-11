package datastructures;

/** Basic Trie implementation. Assumes lowercase letters.
 * Supports the following operations:
 *      void add(String str) - adds str into the trie
 *      int getPrefixCount(String str)
 *      int getWordCount(String str)
 *      int size()
 */
public class SimpleTrie {
    int wordCount = 0;
    int prefixCount = 0;
    SimpleTrie[] next = new SimpleTrie[26];

    public void add(String str) {
        SimpleTrie cur = this;
        this.prefixCount++;

        for (int i=0; i<str.length(); i++) {
            cur = cur.getOrSet(str.charAt(i));
            cur.prefixCount++;
        }
        cur.wordCount++;
    }

    public int getPrefixCount(String str) {
        SimpleTrie cur = this;
        for (int i=0; i<str.length(); i++) {
            cur = cur.next[str.charAt(i) - 'a'];
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
        SimpleTrie cur = this;
        for (int i=0; i<str.length(); i++) {
            cur = cur.next[str.charAt(i) - 'a'];
            if (cur == null) {
                break;
            }
        }
        if (cur == null) {
            return 0;
        }
        return cur.wordCount;
    }

    private SimpleTrie getOrSet(char a) {
        SimpleTrie t = next[a - 'a'];
        if (t == null) {
            return next[a - 'a'] = new SimpleTrie();
        }
        return t;
    }

    public int size() {
        return prefixCount;
    }
}
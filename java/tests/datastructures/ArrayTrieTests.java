package datastructures;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArrayTrieTests {

    private ArrayTrie trie;

    @Before
    public void setup() {
        trie = ArrayTrie.createAsciiTrie();
    }

    @Test
    public void addAndCountTest() {
        assertEquals(0, trie.getWordCount("hello"));
        trie.add("hello");
        assertEquals(1, trie.getWordCount("hello"));
        assertEquals(1, trie.getPrefixCount("he"));
        trie.add("hello");
        assertEquals(2, trie.getWordCount("hello"));
    }

    @Test
    public void sizeTest() {
        for (char i = 'a'; i < 'm'; i++) {
            trie.add(i + "");
            assertEquals(i + 1 - 'a', trie.size());
        }
    }

    @Test
    public void getPrefixCountTest() {
        trie.add("foobar");
        trie.add("f13890476!@#%!%");
        trie.add("foo0as");
        assertEquals(3, trie.getPrefixCount("f"));
        assertEquals(2, trie.getPrefixCount("foo"));
        assertEquals(1, trie.getPrefixCount("foobar"));
    }

    @Test
    public void iteratingTest() {
        String str = "abCDe4!<x z";
        trie.add(str);


        ArrayTrie iter = trie;
        for (int i=0; i<str.length(); i++) {
            iter = iter.moveTo(str.charAt(i));
            assertEquals(1, iter.getPrefixCount());
            assertEquals(i == str.length() - 1 ? 1 : 0, iter.getWordCount());
        }
        assertNull(iter.moveTo('Z'));
    }

    // Taken from Hackerrank problem: https://www.hackerrank.com/challenges/contacts test case #4
    @Test
    public void stressTest() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("ArrayTrieTestdata.txt").getFile());
        File outputFile = new File(classLoader.getResource("ArrayTrieTestOutputData.txt").getFile());
        Scanner in = new Scanner(inputFile);
        Scanner out = new Scanner(outputFile);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String op = in.next();
            String str = in.next();
            if (op.equals("add")) {
                trie.add(str);
            } else {
                assertEquals(out.nextInt(), trie.getPrefixCount(str));
            }
        }
    }
}

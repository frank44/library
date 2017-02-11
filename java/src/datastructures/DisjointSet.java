package datastructures;

import java.util.*;

/**
 * Tree data structure used to keep track of disjoint sets using O(N) additional space. It supports the following
 * operations:
 * 		boolean contains(int key) - Does key exist?
 * 		void merge(int key1, int key2) - Merge the set containing key1 with the set containing key2
 * 		int getRepresentative(int key) - Gets the representative of the given member. Returns -1 if key is not found.
 * 		int getSetSize(int key) - Returns the size of the set containing key.
 * 		int size() - Returns the total number of individual elements in the tree.
 * 		boolean tryAddSet(int newKey) - Tries to add a new set with the given key unless one already exists.
 * 		HashSet<Integer> getRepresentativeKeySet() - Returns a set of keys representing every disjoint set. O(1)
 */
public class DisjointSet {
    private class TreeNode { // Helper class used to represent set elements
        int parent; // -1 denotes to a representative

        // Set size, also used for merging. Proven to be as efficient as union-by-rank.
        // Note that  only accurate for representative nodes
        int size;

        TreeNode(int p, int s) {
            parent = p;
            size = s;
        }

        boolean isRepresentative() {
            return parent == -1;
        }
    }

    private Map<Integer, TreeNode> tree = new HashMap<>();
    private Set<Integer> representativeKeySet = new HashSet<>(); // Possible optimization: this could be turned off
    private int numDisjointSets = 0;

    public boolean contains(int key) {
        return tree.containsKey(key);
    }

    // Uses path compression to speed up future calls
    // Returns -1 if the key is not found
    public int getRepresentative(int key) {
        TreeNode node = tree.getOrDefault(key, null);
        if (node == null) {
            return -1;
        }

        int curKey = key;
        while (!node.isRepresentative()) { // Compute answer
            curKey = node.parent;
            node = tree.get(curKey);
        }

        int representativeKey = curKey; // save result

        // 2nd pass for path-compression
        node = tree.get(key);

        while (!node.isRepresentative()) {
            curKey = node.parent;
            node.parent = representativeKey;
            node = tree.get(curKey);
        }

        return representativeKey;
    }

    public void merge(int key1, int key2) {
        int head1 = getRepresentative(key1);
        int head2 = getRepresentative(key2);
        if (head1 == head2) { // No-op merge
            return;
        }

        TreeNode n1 = tree.get(head1);
        TreeNode n2 = tree.get(head2);

        // Merge the smaller set into the larger
        if (n1.size < n2.size) {
            n1.size += n2.size;
            n2.parent = head1;
            representativeKeySet.remove(head2);
        } else {
            n2.size += n1.size;
            n1.parent = head2;
            representativeKeySet.remove(head1);
        }
        numDisjointSets--;
    }

    public int getSetSize(int key) { // Number of elements that are a part of this set
        key = getRepresentative(key);
        return tree.get(key).size;
    }

    public int size() { // Number of disjoint sets
        return numDisjointSets;
    }

    public boolean tryAddSet(int key) {
        if (tree.containsKey(key)) {
            return false;
        }
        tree.put(key, new TreeNode(-1, 1));
        numDisjointSets++;
        representativeKeySet.add(key);
        return true;
    }

    Set<Integer> getRepresentativeKeySet() {
        return Collections.unmodifiableSet(representativeKeySet);
    }
}

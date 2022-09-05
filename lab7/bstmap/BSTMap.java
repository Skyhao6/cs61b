package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    private BTSNode root;
    private int size = 0;

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    public boolean containsKey(BTSNode node, K key) {
        if(node == null) return false;
        int cmp = key.compareTo(node.key);
        if(cmp < 0)
            return containsKey(node.left, key);
        else if(cmp > 0)
            return containsKey(node.right, key);
        return true;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(BTSNode node, K key) {
        if(node == null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp < 0)
            return get(node.left, key);
        else if(cmp > 0)
            return get(node.right, key);
        return node.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        size += 1;
    }

    public BTSNode put(BTSNode node, K key, V value) {
        if(node == null) return new BTSNode(key,value);
        int cmp = key.compareTo(node.key);
        if(cmp < 0)
            node.left = put(node.left, key, value);
        else if(cmp > 0)
            node.right = put(node.right, key, value);
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private class BTSNode {
        BTSNode left;
        BTSNode right;
        V val;
        K key;
        public BTSNode(K k, V v) {
            key = k;
            val = v;
        }
    }


}

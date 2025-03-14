package hr.fer.oop.pair;

import java.util.*;

class SimplePairHashSet implements Iterable<Pair>{
    private Map<Pair, Object> internalMap;

    public SimplePairHashSet() {
        internalMap = new HashMap<>();
    }

    public Map<Pair, Object> getInternalMap() {
        return internalMap;
    }

    public boolean add(Pair newItem) {
        if (!contains(newItem)) {
            internalMap.put(newItem, null);
            return true;
        }
        return false;
    }

    public int size() {
        return internalMap.size();
    }

    public boolean contains(Object item) {
        // if (item == null)
        //     return false;
        // if (item.getClass() != Pair.class)
        //     return false;
        return internalMap.containsKey(item);
    }

    public boolean remove(Object item) {
        if (!contains(item)) {
            return false;
        }
        internalMap.remove(item);
        return true;

    }

    @Override
    public Iterator<Pair> iterator() {
        return new SimplePairHashSetIterator();
    }
    private class SimplePairHashSetIterator implements Iterator<Pair> {
        private int id;
        private Iterator<Pair> keyIterator;
        public SimplePairHashSetIterator() {
            keyIterator = internalMap.keySet().iterator();
            id = 0;

        }

        @Override
        public boolean hasNext() {
            return keyIterator.hasNext();
        }

        @Override
        public Pair next() {
            return keyIterator.next();
        }
    
        
    }

}

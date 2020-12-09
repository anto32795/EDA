package material.maps;

import java.util.*;

/**
 * Separate chaining table implementation of hash tables. Note that all
 * "matching" is based on the equals method.
 *
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro, Sergio Pérez
 */
public class HashTableMapSC<K, V> implements Map<K, V> {
    //TODO: Ejercicio para los alumnos, implementar todo

    private class HashEntry<T, U> implements Entry<T, U> {

        protected T key;
        protected U value;

        public HashEntry(T k, U v) {
            key = k;
            value = v;
        }

        @Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
            return key;
        }

        public U setValue(U val) {
            U old = this.value;
            this.value = val;
            return old;
        }

        @Override
        public boolean equals(Object o) {
            if(o == null) return false;
            else if(o == this) return true;
            HashEntry<T,U> aux = (HashEntry<T, U>) o;
            return (aux.key.equals(this.key) && aux.value.equals(this.value));
        }

        /**
         * Entry visualization.
         */
        @Override
        public String toString() {
            return "(" + key + "," + value + ")";
        }
    }

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {
// TODO: THIS
        private List<HashEntry<T, U>> [] bucket;
        private List<HashEntry<T,U>> list_entry;
        private int pos_bucket;
        private int pos_list;

        public HashTableMapIterator(List<HashEntry<T, U>>[] map, int numElems) {
            this.bucket = map;
            this.pos_list = 0;
            if (numElems == 0) {
                this.pos_bucket = bucket.length;
                this.list_entry = this.bucket[this.pos_bucket-1];
            } else {
                this.pos_bucket = 0;
                this.list_entry = this.bucket[this.pos_bucket-1];
                goToNextBucket(0);
            }
        }


        @Override
        public boolean hasNext() {
            return (this.pos_bucket < this.bucket.length);
        }

        @Override
        public Entry<T, U> next() {
            if (hasNext()) {
                int currentPos = this.pos_bucket;
                goToNextBucket(this.pos_bucket + 1);
                List<HashEntry<T,U>> entry_list = this.bucket[currentPos];
                //return this.bucket[currentPos];
            } else {
                throw new IllegalStateException("The map has not more elements");
            }
            return null;
        }

        @Override
        public void remove() {
            //NO ES NECESARIO IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");

        }

        /**
         * Returns the index of the next position starting starting from a given index.
         * (if the parameter is already a valid position then does nothing)
         */
        private int goToNextBucket(int i) {
            throw new RuntimeException("Not yet implemented");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
        public HashTableMapIterator<T, U> it;

        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public T next() {
            return it.next().getKey();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            //NO ES NECESARIO IMPLEMENTARLO
            throw new RuntimeException("Not yet implemented");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
        public HashTableMapIterator<T, U> it;

        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
            this.it = it;
        }

        @Override
        public U next() {
            return it.next().getValue();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }


    /**
     * The array of Lists.
     */
    private LinkedList<HashEntry<K,V>>[] bucket;
    private int capacity, prime, n;

    /**
     * Creates a hash table with prime factor 109345121 and capacity 1000.
     */
    public HashTableMapSC() {
        this.capacity = 1000;
        this.prime = 109345121;
        this.bucket = new LinkedList[capacity];
        this.n = 0;
    }

    /**
     * Creates a hash table with prime factor 109345121 and given capacity.
     *
     * @param cap initial capacity
     */
    public HashTableMapSC(int cap) {
        this.capacity = cap;
        this.prime = 109345121;
        this.bucket = new LinkedList[capacity];
        this.n = 0;
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p   prime number
     * @param cap initial capacity
     */
    public HashTableMapSC(int p, int cap) {
        this.capacity = cap;
        this.prime = p;
        this.bucket = new LinkedList[capacity];
        this.n = 0;
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return the hash value
     */
    protected int hashValue(K key) {
        Random rand = new Random();
        int a = rand.nextInt() % this.prime;
        int b = rand.nextInt() % this.prime;
        return ((key.hashCode() * a + b) % this.prime ) % this.capacity;
    }


    @Override
    public int size() {
        return this.capacity;
    }

    @Override
    public boolean isEmpty() {
        return this.n == 0;
    }

    @Override
    public V get(K key) {
        checkKey(key);
        LinkedList<HashEntry<K,V>> entry_list = this.bucket[hashValue(key)];
        if(entry_list == null){
            return null;
        }
        else if(entry_list.size() == 1){
            return entry_list.getFirst().value;
        }
        else {
            for(HashEntry<K,V> entry: entry_list){
                if(entry.key.equals(key)){
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        checkKey(key);
        HashEntry<K,V> newEntry = new HashEntry<>(key, value);
        LinkedList<HashEntry<K,V>> entry_list = this.bucket[hashValue(key)];
        if(entry_list == null){
            entry_list = new LinkedList<>();
        }
        entry_list.add(newEntry);
        if(entry_list.size() == 1){
            return null; // no previous entry
        }else{
            return entry_list.getFirst().value;
        }

    }

    @Override
    public V remove(K key) {
        checkKey(key);
        LinkedList<HashEntry<K,V>> entry_list = this.bucket[hashValue(key)];
        HashEntry<K,V> found_entry = null;
        if(entry_list == null)
            return null;
        else if(entry_list.size() == 1){
            V toReturn = entry_list.getFirst().value;
            entry_list.removeFirst();
            this.n--;
            return toReturn;
        }
        else {
            for(HashEntry<K,V> entry: entry_list){
                if(entry.key.equals(key)){
                    found_entry = entry;
                    break;
                }
            }
            if(found_entry == null) {
                return null;
            }
            else {
                V toreturn = found_entry.getValue();
                entry_list.remove(found_entry);
                this.n--;
                return toreturn;
            }
        }
    }


    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new RuntimeException("Not yet implemented");

    }

    @Override
    public Iterable<K> keys() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Iterable<V> values() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        throw new RuntimeException("Not yet implemented");
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
        if(k == null) throw new RuntimeException("Invalid key: null");
    }


    /**
     * Increase/reduce the size of the hash table and rehashes all the entries.
     */
    protected void rehash(int newCap) {
        this.capacity = newCap;
        LinkedList<HashEntry<K,V>>[] old_bucket = this.bucket;
        this.bucket = new LinkedList[newCap];

        for(LinkedList<HashEntry<K,V>> entry_list: old_bucket){
            if(entry_list != null){
                for(HashEntry<K,V> entry: entry_list){
                    if(entry != null)
                        this.put(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}

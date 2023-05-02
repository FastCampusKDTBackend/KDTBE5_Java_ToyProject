package com.smartstore.util;
// TODO: 2023-05-02 implement resize method
public class CustomHashMap<K, V> implements Map<K, V> {
    private class Node<K, V> implements Entry<K, V> {
        //Key is unmodifiable
        private final K key;
        private V value;
        private Node<K,V> next;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node<K, V> e = this;
            while (e != null){
                sb.append("\t").append("[").append(" key :  ").append(e.getKey()).append(", value : ").append(e.getValue()).append(" ]\n");
                e = e.next;
            }
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomHashMap { \n");
        for(int i = 0; i < entries.length; i++){
            if(entries[i] != null){
                sb.append(entries[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /*
        entries[] (if size = 5)
        +----------+---------+----------+----------+-----------+----------------+
        |          |    0    |    1     |    2     |     3     |       4        |
        |entries   | Entry   | Entry2   | Entry3   | Entry...  | Entry.size-1   |
        |          | (Head1) | (Head2)  | (Head3)  | (Head...) | (Head.size-1)  |
        +----------+---------+----------+----------+-----------+----------------+
        ⬇️⬇️
        +--------------------------+--------------------------+--------------------------+--------------------------+--------------------------+
        | Head1    index 0         | Head2   index 1          | Head3    index 2         |        ...               | Head4   index 4          |
        | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE |
        +--------------------------+--------------------------+--------------------------+--------------------------+--------------------------+
        | 1: [key, value]          | null                     | null                     | 1: [key, value]          | 1: [key, value]          |
        | 2: [key, value] (1.next) |                          |                          | 2: [key, value] (1.next) | 2: [key, value] (1.next) |
        |                          |                          |                          |                          | 3: [key, value] (2.next) |
        | 1,2,3... is not index    |                          |                          | 1,2,3... is not index    | 1,2,3... is not index    |
        +--------------------------+--------------------------+--------------------------+--------------------------+--------------------------+
        Too big SIZE have chance to increasing wasted memory usage
        Too small Size have chance to increasing Hash collisions, negative affect to performance
        ideal size is expected entries * 0.75
     */
    private Node<K, V>[] entries;

    //The default initial capacity - MUST be a power of two.
    //use bitwise to code readability
    //1 << 4 = 16
    private static final int CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;

    public CustomHashMap(){
        entries = new Node[Math.round(CAPACITY*DEFAULT_LOAD_FACTOR)];
    }
    @Override
    public void put(K key, V value){
        //Get Entries index from hash code 0 to size-1
        int hash = key.hashCode() % CAPACITY;
        Node<K, V> e = entries[hash];

        //if entries[hash] is not exists
        if(e == null){
            //create new Entry
            entries[hash] = new Node<K, V>(key, value);
            size++;
        }
        else{
            //loop until e.next is not null
            while (e.next != null){
                //if key exists, set value as new value, key is Unique
                if (e.getKey().equals(key)){
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }
            //if key == final entry's key, set value, key is Unique
            if(e.getKey().equals(key)){
                e.setValue(value);
                return;
            }

            //if key doesn't exist
            //add new Entry end of the entries[hash]
            e.next = new Node<K, V>(key, value);
            size++;
        }

    }

    @Override
    public V get(K key){
        int hash = key.hashCode() % CAPACITY;
        Node<K, V> e = entries[hash];

        //if entries[hash] is not exists, return null
        if (e == null) {
            return null;
        }

        //loop until e.next is not null
        while (e != null){
            //if key exists, return value
            if(e.getKey().equals(key)){
                return e.getValue();
            }
            e = e.next;
        }
        //if key doesn't exist, return null
        return null;
    }

    @Override
    public boolean remove(K key){
        int hash = key.hashCode() % CAPACITY;
        Node<K, V> e = entries[hash];

        //if entries[hash] is not exists, return false
        if (e == null){
            return false;
        }

        //if first Entry's e.key equals key
        if(e.getKey().equals(key)){
            //shift second Entry to first(remove)
            entries[hash] = e.next;
            e.next = null;
            size--;
            return true;
        }

        Node<K, V> prev = e;
        e = e.next;

        //loop until e is not null
        while (e != null){
            if (e.getKey().equals(key)) {
                //shift next Entry to current(remove)
                prev.next = e.next;
                e.next = null;
                size--;
                return true;
            }
            prev = e;
            e = e.next;
        }

        //if key not exists, return false
        return false;
    }

    @Override
    public int size() {return this.size;}

    public boolean isEmpty() {
        return this.size == 0;
    }


}

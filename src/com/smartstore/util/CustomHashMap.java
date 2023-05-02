package com.smartstore.util;

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
        for(int i = 0; i < CAPACITY_SIZE; i++){
            if(entries[i] != null){
                sb.append(entries[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /*
        entries
        +--------------------------+--------------------------+--------------------------+--------------------------+--------------------------+
        | Head1                    | Head2                    | Head3                    |        ...               | Head.SIZE                |
        | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE | key.hashcode() % CA_SIZE |
        +--------------------------+--------------------------+--------------------------+--------------------------+--------------------------+
        | 1: [key, value]          | null                     | null                     | 1: [key, value]          | 1: [key, value]          |
        | 2: [key, value] (1.next) |                          |                          | 2: [key, value] (1.next) | 2: [key, value] (1.next) |
        | 3: [key, value] (2.next) |                          |                          | 3: [key, value] (2.next) | 3: [key, value] (2.next) |
        |                          |                          |                          |                          | 4: [key, value] (3.next) |
        +--------------------------+--------------------------+--------------------------+--------------------------+--------------------------+
        Too big SIZE have chance to increase unnecessary indexes
     */
    private Node<K, V>[] entries;

    private final int CAPACITY_SIZE = 5;

    public CustomHashMap(){
        entries = new Node[CAPACITY_SIZE];
    }
    @Override
    public void put(K key, V value){
        //Get Entries index from hash code 0 to size-1
        int hash = key.hashCode() % CAPACITY_SIZE;
        Node<K, V> e = entries[hash];

        //if entries[hash] is not exists
        if(e == null){
            //create new Entry
            entries[hash] = new Node<K, V>(key, value);
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
        }

    }

    @Override
    public V get(K key){
        int hash = key.hashCode() % CAPACITY_SIZE;
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
        int hash = key.hashCode() % CAPACITY_SIZE;
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
                return true;
            }
            prev = e;
            e = e.next;
        }

        //if key not exists, return false
        return false;
    }

    @Override
    public int size() {
        return CAPACITY_SIZE;
    }

}

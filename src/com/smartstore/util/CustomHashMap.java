package com.smartstore.util;

public class CustomHashMap<K, V> implements Map<K, V> {
    //Entry Class for CustomHashMap
    private class Entry<K, V>{
        //Key is unmodifiable
        private final K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Entry <K, V> e = this;
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
        +--------------------+--------------------+--------------------+--------------------+--------------------+
        | Head1              | Head2              | Head3              |        ...         | Head.SIZE          |
        | key.hash % SIZE    | key.hash % SIZE    | key.hash % SIZE    | key.hash % SIZE    | key.hash % SIZE    |
        +--------------------+--------------------+--------------------+--------------------+--------------------+
        | [key, value]       | null               | null               | [key, value]       | [key, value]       |
        | [key, value]       |                    |                    |                    | [key, value]       |
        | [key, value]       |                    |                    |                    | [key, value]       |
        |                    |                    |                    |                    | [key, value]       |
        +--------------------+--------------------+--------------------+--------------------+--------------------+
        Too big SIZE have chance to increase unnecessary indexes
     */
    private Entry<K, V>[] entries;

    private final int CAPACITY_SIZE = 5;

    public CustomHashMap(){
        entries = new Entry[CAPACITY_SIZE];
    }
    @Override
    public void put(K key, V value){
        //Get Entries index from hash code 0 to size-1
        int hash = key.hashCode() % CAPACITY_SIZE;
        Entry<K, V> e = entries[hash];

        //if entries[hash] is not exists
        if(e == null){
            //create new Entry
            entries[hash] = new Entry<K, V>(key, value);
        }
        else{
            //loop until e.next is not exists
            while (e.next != null){
                //if key exists, set value as new one
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
            e.next = new Entry<K, V>(key, value);
        }

    }

    @Override
    public V get(K key){
        int hash = key.hashCode() % CAPACITY_SIZE;
        Entry<K, V> e = entries[hash];

        //if entries[hash] is not exists, return null
        if (e == null) {
            return null;
        }

        //loop until e.next is not exists
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
        Entry<K, V> e = entries[hash];

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

        Entry<K, V> prev = e;
        e = e.next;

        //loop until e.next is not exists
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

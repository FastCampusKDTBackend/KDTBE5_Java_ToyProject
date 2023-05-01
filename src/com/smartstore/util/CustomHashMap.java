package com.smartstore.util;

public class CustomHashMap<K, V> implements Map<K, V> {
    //Entry Class for CustomHashMap
    private class Entry<K, V>{
        private final K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        //Key is immutable
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
        for(int i = 0 ; i < SIZE ; i++){
            if(entries[i] != null){
                sb.append(entries[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /*
        Entries
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
    private final int SIZE = 5;

    private Entry<K, V>[] entries;

    public CustomHashMap(){
        entries = new Entry[SIZE];
    }
    @Override
    public void put(K key, V value){
        //Get Entries index from hash code 0 to size-1
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = entries[hash];

        //if entries[hash] is empty
        if(e == null){
            //create new Entry
            entries[hash] = new Entry<K, V>(key, value);
        }
        else{
            //loop until entries value not exists
            while (e.next != null){
                //if key exists, set value as new one
                if (e.getKey() == key){
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }
            //if key == final entry's key, set value
            if(e.getKey() == key){
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
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = entries[hash];

        //if entries[hash] is empty, return null
        if (e == null) {
            return null;
        }

        //loop until entries value not exists
        while (e != null){
            //if key exits, return value
            if(e.getKey() == key){
                return e.getValue();
            }
            e = e.next;
        }
        //if key doesn't exist, return null
        return null;
    }

    @Override
    public boolean remove(K key){
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = entries[hash];

        //if entries[hash] is empty, return false
        if (e == null){
            return false;
        }

        //if first Entry's key == key
        if(e.getKey() == key){
            //shift second Entry to first(remove)
            entries[hash] = e.next;
            e.next = null;
            return true;
        }

        Entry<K, V> prev = e;
        e = e.next;

        while (e != null){
            if (e.getKey() == key) {
                //shift next Entry to current(remove)
                prev.next = e.next;
                e.next = null;
                return true;
            }
            prev = e;
            e = e.next;
        }

        //if key not exits, return false
        return false;
    }

}

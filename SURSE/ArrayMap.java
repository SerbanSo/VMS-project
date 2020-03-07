import java.util.*;

public class ArrayMap<K, V> extends AbstractMap<K, V>{
    ArrayList<ArrayMapEntry<K, V>> list;

    // Inner class; Entry for the ArrayMap
    public class ArrayMapEntry<K, V> implements Map.Entry<K, V>{
        K key;
        V value;

        // Constructor
        public ArrayMapEntry(K key, V value){
            this.key = key;
            this.value = value;
        }

        // Return the key
        @Override
        public K getKey() {
            return key;
        }

        // Return the value
        @Override
        public V getValue() {
            return value;
        }

        // Replace the value and return the old value
        @Override
        public V setValue(V v) {
            V oldValue = this.value;
            this.value = v;
            return oldValue;
        }

        // Return a string describing the current Entry
        @Override
        public String toString() {
            return value.toString();
        }

        // Equals function took from the documentation
        // https://docs.oracle.com/javase/7/docs/api/java/util/Map.Entry.html#equals(java.lang.Object)
        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof ArrayMapEntry))
                return false;

            return this.hashCode() == obj.hashCode();
        }

        // Hash function took from the documentation
        // https://docs.oracle.com/javase/7/docs/api/java/util/Map.Entry.html#hashCode()
        @Override
        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
        }
    }

    // Constructor
    public ArrayMap(){
        list = new ArrayList<>();
    }

    // Put the (key, value) pair into the list
    public V put(K key, V value){
        V oldValue = null;

        // If the key doesn't exists, insert the new pair
        if(!containsKey(key)) {
            list.add(new ArrayMapEntry<>(key, value));
        }
        else {

            // If the key exists, replace it's value
            for (ArrayMapEntry<K, V> arrayMapEntry : list) {
                if (key.equals(arrayMapEntry.getKey())) {
                    oldValue = arrayMapEntry.getValue();
                    arrayMapEntry.setValue(value);
                    break;
                }
            }
        }

        // Return the replaced value, or null if there is no replaced value
        return oldValue;
    }

    // Return true if it contains the specified key or false otherwise
    public boolean containsKey(Object key){
        for(ArrayMapEntry<K, V> a : list){
            if(a.getKey().equals(key))
                return true;
        }
        return false;
    }

    // Return the value of the key or null if it doesn't exists
    public V get(Object key){
        for(ArrayMapEntry<K, V> a : list){
            if(a.getKey().equals(key))
                return a.getValue();
        }
        return null;
    }

    // Return the size of the list
    public int size(){
        return list.size();
    }

    // Return an entry Set
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        set.addAll(this.list);

        return set;
    }

    // Return a string containing the desired output
    @Override
    public String toString(){
        Iterator<ArrayMapEntry<K, V>> i = list.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        while (i.hasNext()){
            sb.append("[" + i.next().toString() + "]" + (i.hasNext()? ", ":""));
        }

        sb.append(']');
        return sb.toString();
    }
}

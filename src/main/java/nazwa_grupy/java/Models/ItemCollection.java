package nazwa_grupy.java.Models;

import nazwa_grupy.java.Iterator_DBProcessor.CollectionIterator;
import java.util.ArrayList;

public class ItemCollection<T> {
    
    private ArrayList<T> collection = new ArrayList<T>();
    private String name;
    
    
    public ItemCollection(String name) {
        
        this.name = name;
    }

    public CollectionIterator<T> getIterator() {
        
        return new CollectionIterator<T>(collection);
    }
    
    public void add(T item) {
        
        collection.add(item);
    }
    
    public void remove(T item) {
        
        collection.remove(item);
    }

    public void clear() {
        collection.clear();
    }
    
    public String getName(){
        return this.name;
    }
}
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
    
    public void add(T t) {
        
        collection.add(t);
    }
    
    public void remove(T t) {
        
        collection.remove(t);
    }
}
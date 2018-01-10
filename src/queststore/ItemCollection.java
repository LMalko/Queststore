import java.util.ArrayList;


public class ItemCollection<T>{

    ArrayList<T> itemCollection = new ArrayList<T>();
    String category;
    CollectionIterator<T> iterator;

    public ItemCollection(String category) {

        this.category = category;
        this.iterator = new CollectionIterator<T>(this.category);
    }

    public CollectionIterator<T> getIterator() {

        return this.iterator;
    }
    
    public void add(T item) {
        
        itemCollection.add(item);
    }
    
    public void remove(T item) {
        
        itemCollection.remove(item);
    }
    

}
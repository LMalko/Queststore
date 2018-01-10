import java.util.ArrayList;


public class ItemCollection<T>{

    ArrayList<T> itemCollection = new ArrayList<T>();
    String category;

    public CollectionIterator<T> getIterator() {

        return new CollectionIterator<T>(category);
    }

    public ItemCollection(String category) {
        
        this.category = category;
    }
    
    public void add(T item) {
        
        itemCollection.add(item);
    }
    
    public void remove(T item) {
        
        itemCollection.remove(item);
    }
    


}
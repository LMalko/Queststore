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
    


}
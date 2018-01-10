import java.util.ArrayList;

public class CollectionIterator<T> implements Iterator<T> {
    
    private ArrayList<T> list;
    private int index;
    
    public CollectionIterator(ArrayList<T> list) {
        
        this.list = list;
    }
    
    @Override
    public boolean hasNext() {
        
        return index < list.size();
    }
    
    @Override
    public T next() {
        
        return list.get(index++);
    }
}
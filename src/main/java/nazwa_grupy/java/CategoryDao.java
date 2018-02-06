import java.sql.Connection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class CategoryDao {

    private static ItemCollection<Category> allCategories = new ItemCollection<>("Category");
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");
    private static final int categoryNameIndex = 0;

    public void importCategories() {
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> category = databaseProcessor.getArrayListFromQuery("SELECT * category");

        for(int i=0; i < category.size(); i++){
            String name = category.get(i).get(categoryNameIndex);
            Category newCategory = new Category(name);
            addCategory(newCategory);
        }
    }

    public ItemCollection<Category> getCategories(){
        return allCategories;
    }

    public void addCategory(Category category){
        allCategories.add(category);
    }

    public Category getCategoryByName(String name) {
        CollectionIterator<Category> categoryIterator = allCategories.getIterator();

        while(categoryIterator.hasNext()) {
            Category category = categoryIterator.next();
            if (category.getCategoryName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public void addCategoryToDatabase(Category category) {
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO categories (name) VALUES ( " + "'" +
                                                        category.getCategoryName() +
                                                        "')");
    }
}

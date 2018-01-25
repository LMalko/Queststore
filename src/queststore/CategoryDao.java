import java.sql.Connection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class CategoryDao{

    private static ItemCollection<Category> allCategories = new ItemCollection<>("Category");
    private JDBConnection databaseConnection = new JDBConnection("jdbc:sqlite:db/questStore.db");

    public void importCategories(){
        databaseConnection.connectToDatabase();

        ArrayList<ArrayList<String>> category = databaseConnection.getArrayListFromQuery("SELECT * FROM category");

        for(int i =0; i < category.size(); i++){

            String name = category.get(i).get(0);
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

    public void addCategoryToDatabase(Category category){
      databaseConnection.executeUpdateAgainstDatabase("INSERT INTO artifacts (" + "'" +
                                                        category.getCategoryName() + "')");
    }

    public Category getCategoryByName(String name){
        CollectionIterator<Category> categoryIterator = allCategories.getIterator();
        while(categoryIterator.hasNext()){
            Category category = categoryIterator.next();
            if (category.getCategoryName().equals(name)){
                return category;
            }
        }
        return null;
    }
}

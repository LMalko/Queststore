package nazwa_grupy.java.DAOs;

import nazwa_grupy.java.Iterator_DBProcessor.CollectionIterator;
import nazwa_grupy.java.Iterator_DBProcessor.DBStatementProcessor;
import nazwa_grupy.java.Models.Category;
import nazwa_grupy.java.Models.ItemCollection;

import java.util.ArrayList;

public class CategoryDao {

    private static ItemCollection<Category> allCategories = new ItemCollection<>("Category");
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");

    public void importCategories(){
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> category = databaseProcessor.getArrayListFromQuery("SELECT * FROM categories");

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

    public Category getCategoryByName(String name){
        CollectionIterator<Category> categoryIterator = allCategories.getIterator();
        while(categoryIterator.hasNext()){
            Category category = categoryIterator.next();
            if (category.getCategoryName().equals(name)){
                return category; }
        }
        return null;
    }

    public void addCategoryToDatabase(Category category){
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO categories (name) VALUES ( " + "'" +
                                                        category.getCategoryName() +
                                                        "')");
    }

}

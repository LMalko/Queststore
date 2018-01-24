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
        String row;
        for(int i =0; i < category.size(); i++){

            String name = category.get(i).get(0);
            Category newCategory = new Category(name);
            addCategory(newCategory);
        }
    }

    public void exportCategory(){

        CollectionIterator<Category> categoryIterator = allCategories.getIterator();

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/categories.csv"));
            StringBuilder sb = new StringBuilder();

            while(categoryIterator.hasNext()){
                Category category = categoryIterator.next();
                sb.append(category.getCategoryName());
                sb.append("\n");
            }

            br.write(sb.toString());
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public ItemCollection<Category> getCategories(){
        return allCategories;
    }

    public void addCategory(Category category){
        allCategories.add(category);
    }

    private void closeReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

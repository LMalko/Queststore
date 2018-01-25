import java.sql.Connection;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class CategoryDao{

    private static ItemCollection<Category> allCategories = new ItemCollection<>("Category");
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");

    public void importCategories(){
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> category = databaseProcessor.getArrayListFromQuery("SELECT category FROM artifacts");
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

    // public ArrayList<String> getAllCategories(){
    //     databaseProcessor.connectToDatabase();
    //
    //     ArrayList<ArrayList<String>> categories = databaseProcessor.getArrayListFromQuery("SELECT category FROM artifacts");
    //     ArrayList<String> allCategories = new ArrayList<String>();
    //     for(int i =0; i < categories.size(); i++){
    //         String category = categories.get(i).get(0);
    //         allCategories.add(category);
    //         }
    //     return allCategories;
    // }
}

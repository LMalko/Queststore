import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class CategoryDao{

    private static ItemCollection<Category> allCategories = new ItemCollection<>("Category");
    private BufferedReader bufferedReader = null;

    public void importCategories(){
        String filename = "csv/categories.csv";

        try{
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = bufferedReader.readLine()) != null){
                String[] parts = row.split("\n");
                String name = parts[0];
                Category newCategory = new Category(name);
                addCategory(newCategory);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        } finally {
            closeReader(bufferedReader);
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
        while(groupIterator.hasNext()){
            Category category = categoryIterator.next();
            if (category.getCategoryName().equals(name)){
                return category;
            }
        }
        return null;
    }
}
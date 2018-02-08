package nazwa_grupy.java.Models;

public class Category {

    private String categoryName;

    public Category(String name){
        this.categoryName = name;
    }

    public String getCategoryName(){
        return this.categoryName;
    }
}
import java.util.ArrayList;

class Category{
    private String categoryName;

    public Category(String name){
        this.categoryName = name;
    }

    public String getCategoryName(){
        return this.categoryName;
    }

    public void setCategoryName(String name){
        this.categoryName = name;
    }

}
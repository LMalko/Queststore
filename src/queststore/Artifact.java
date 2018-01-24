import java.util.ArrayList;


public class Artifact{
    private Integer id;
    private String name;
    private int price;
    private String category;

    private static Integer nextID = 1;




    public Artifact(String name, int price, String category){
        this.name = name;
        this.price = price;
        this.category = category;

        this.id = getNextID();
    }

    public int getArtifactId(){
        return this.id;
    }

    public String getArtifactName(){
        return this.name;
    }

    public int getArtifactPrice(){
        return this.price;
    }

    public String getArtifactCategory(){
        return this.category;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setCategory(String category){
        this.category = category;
    }

    private Integer getNextID() {
        Integer newID = nextID;
        nextID++;
        return newID;
}

    @Override
    public String toString(){
        return  String.format("ID: %d, NAME: %s, PRICE: %d, TYPE: %s",
                              this.id,
                              this.name,
                              this.price,
                              this.category);
    }


}

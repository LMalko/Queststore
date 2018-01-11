import java.util.ArrayList;


public class Artifact{
    private int id;
    private String name;
    private int price;
    private String category;


    public Artifact(int id, String name, int price, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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

    @Override
    public String toString(){
        return  String.format("ID: %d, NAME: %s, PRICE: %d, TYPE: %s",
                              this.id,
                              this.name,
                              this.price,
                              this.category);
    }

    
}

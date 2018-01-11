import java.util.ArrayList;


public class Artifact{
    private int id;
    private String name;
    private int price;
    private String category;
    private static ArrayList<Artifact> artifactsCollection = new ArrayList<Artifact>();

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
// ItemCollection<Artifact>
    public static ArrayList<Artifact> getArtifacts(){
        ArtifactsDao artifactsDao = new ArtifactsDao();
        artifactsDao.importArtifacts();
        return artifactsCollection;
    }

    public void addArtifact(Artifact artifact){
        artifactsCollection.add(artifact);
    }
}

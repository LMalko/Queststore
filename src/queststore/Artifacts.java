public class Artifacts{
    private int id;
    private String name;
    private int price;
    private String category;
    private static ArrayList<Artifacts> artifactsCollection = new ArrayList<Artifacts>();

    public Artifacts(int id, String name, int price, String category){
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

    public ArrayList<Artifacts> getArtifacts(){
        return artifactsCollection;
    }

    public void addArtifact(Artifacts artifact){
        artifactsCollection.add(artifact);
    }
}

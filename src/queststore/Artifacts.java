public class Artifacts{
    private int id;
    private String name;
    private int price;
    private String category;
    private ArrayList<int> ownersCollection;

    public Artifacts(int id, String name, int price, String category, ArrayList<int> ownersCollection){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.ownersCollection = ownersCollection;
    }
}

import java.util.ArrayList;


public class Crowdfund{
    int id;
    String name;
    int totalPrice;
    int priceLeft;
    Student contributors;
    private static ArrayList<Crowdfund> corwdfundCollection = new ArrayList<Crowdfund>();

    public Crowdfund(int id, String name, int totalPrice, int priceLeft, Student contributors){
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.priceLeft = priceLeft;
        this.contributors = contributors;
    }

    public int getCrowdfundId(){
        return this.id;
    }

    public String getCrowdfundName(){
        return this.name;
    }

    public int getCrowdfundTotalPrice(){
        return this.totalPrice;
    }

    public int getCrowdfundPriceLeft(){
        return this.priceLeft;
    }

    public Student getCrowdfundContributors(){
        return this.contributors;
    }

    public void setCrowdfundId(int id){
        this.id = id;
    }

    public void setCrowdfundName(String name){
        this.name = name;
    }

    public void setCrowdfundTotalPrice(int totalPrice){
        this.totalPrice = totalPrice;
    }

    public void setCrowdfundPriceLeft(int priceLeft){
        this.priceLeft = priceLeft;
    }

    public void setCrowdfundId(Student contributors){
        this.contributors = contributors;
    }

    public ArrayList<Crowdfund> getCrowdfund(){
        return corwdfundCollection;
    }

    public void addCrowdfund(Crowdfund crowdfund){
        corwdfundCollection.add(crowdfund);
    }
}

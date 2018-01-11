import java.util.ArrayList;


public class Crowdfund{
    int id;
    String name;
    int totalPrice;
    int account;
    String contributorEmail;
    private static ItemCollection<Crowdfund> crowdfundCollection = new ItemCollection<>("Crowdfunds");

    public Crowdfund(int id, String name, int totalPrice, int account, String contributorEmail){
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.account = account;
        this.contributorEmail = contributorEmail;
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

    public int getCrowdfundAccount(){
        return this.account;
    }

    public String getCrowdfundContributorEmail(){
        return this.contributorEmail;
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

    public void setCrowdfundAccount(int priceLeft){
        this.account = account;
    }

    public void setCrowdfundId(String contributorEmail){
        this.contributorEmail = contributorEmail;
    }

    public ArrayList<Crowdfund> getCrowdfund(){
        return crowdfundCollection;
    }

    public void addCrowdfund(Crowdfund crowdfund){
        crowdfundCollection.add(crowdfund);
    }

    public int getPriceLeft(){
        return this.totalPrice - this.account;
    }

    public Crowdfund getSpecyficCrowdfund(String name){
        CollectionIterator<Crowdfund> crowdfundIterator = crowdfundCollection.getIterator();
        while(crowdfundIterator.hasNext()){
            if (name.equals(crowdfundIterator.next().getCrowdfundName())){
                return crowdfundIterator.next();
            }
        }
    }
}

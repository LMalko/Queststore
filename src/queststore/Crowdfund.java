import java.util.ArrayList;


public class Crowdfund{
    int id;
    String name;
    int totalPrice;
    int account;
    Student contributors;
    private static ArrayList<Crowdfund> corwdfundCollection = new ArrayList<Crowdfund>();

    public Crowdfund(int id, String name, int totalPrice, int account, Student contributors){
        this.id = id;
        this.name = name;
        this.totalPrice = totalPrice;
        this.account = account;
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

    public int getCrowdfundAccount(){
        return this.account;
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

    public void setCrowdfundAccount(int priceLeft){
        this.account = account;
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

    public int getPriceLeft(){
        return this.totalPrice - this.account;
    }

    // public Crowdfund getSpecyficCrowdfund(String name){
    //     for (Crowdfund element : corwdfundCollection){
    //         if (name.equals(element.getCrowdfundName())){
    //             return element;
    //         }
    //     }
    // }
}

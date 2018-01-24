import java.util.ArrayList;
import java.sql.Connection;



public class CrowdfundDao{

    private ItemCollection<Crowdfund> crowdfundCollection;
    private JDBConnection databaseConnection = new JDBConnection("jdbc:sqlite:db/questStore.db");

    public void importCrowdfunds(){

        crowdfundCollection = new ItemCollection<>("Crowdfunds");
        databaseConnection.connectToDatabase();


        ArrayList<ArrayList<String>> artifacts = databaseConnection.getArrayListFromQuery("SELECT * FROM crowdfunds");
        for(int i =0; i < artifacts.size(); i++){

            int id = Integer.parseInt(artifacts.get(i).get(0));
            String name = artifacts.get(i).get(1);
            int totalPrice = Integer.parseInt(artifacts.get(i).get(2));
            int account = Integer.parseInt(artifacts.get(i).get(3));
            int founderID = Integer.parseInt(artifacts.get(i).get(4));

            Crowdfund crowdfund = new Crowdfund(id, name, totalPrice, account, founderID);
            addCrowdfund(crowdfund);
            }
        }
                
    public void exportCrowdfund(){

        CollectionIterator<Crowdfund> crowdfundIterator = crowdfundCollection.getIterator();
    }

    public ItemCollection<Crowdfund> getCrowdfunds(){
        importCrowdfunds();
        return crowdfundCollection;
    }

    public void addCrowdfund(Crowdfund crowdfund){
        crowdfundCollection.add(crowdfund);
    }

    public void addCrowdfundToDatabase(Crowdfund crowdfund){
        databaseConnection.executeUpdateAgainstDatabase("INSERT INTO crowdfunds (name, total_price, account, founder_id) VALUES ( " + "'" +
                                                        crowdfund.getCrowdfundName() + "', " +
                                                        crowdfund.getCrowdfundTotalPrice() + ", " +
                                                        crowdfund.getCrowdfundAccount() + ", " +
                                                        crowdfund.getCrowdfundFounderID() +
                                                        ")");
    }
}

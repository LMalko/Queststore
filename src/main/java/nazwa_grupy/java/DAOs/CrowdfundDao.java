package nazwa_grupy.java.DAOs;

import nazwa_grupy.java.Iterator_DBProcessor.CollectionIterator;
import nazwa_grupy.java.Iterator_DBProcessor.DBStatementProcessor;
import nazwa_grupy.java.Models.Crowdfund;
import nazwa_grupy.java.Models.ItemCollection;

import java.util.ArrayList;
import java.sql.Connection;



public class CrowdfundDao{

    private static ItemCollection<Crowdfund> crowdfundCollection = new ItemCollection<>("Crowdfunds");
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");

    public void importCrowdfunds(){
        crowdfundCollection.clear();
        databaseProcessor.connectToDatabase();


        ArrayList<ArrayList<String>> crowdfunds = databaseProcessor.getArrayListFromQuery("SELECT * FROM crowdfunds");
        for(int i =0; i < crowdfunds.size(); i++){

            int id = Integer.parseInt(crowdfunds.get(i).get(0));
            String name = crowdfunds.get(i).get(1);
            int totalPrice = Integer.parseInt(crowdfunds.get(i).get(2));
            int account = Integer.parseInt(crowdfunds.get(i).get(3));
            int founderID = Integer.parseInt(crowdfunds.get(i).get(4));
            if(account >= totalPrice){
                resolveCrowdfund(id, name, founderID);

            }

            Crowdfund crowdfund = new Crowdfund(id, name, totalPrice, account, founderID);
            addCrowdfund(crowdfund);
            }
        }

                
    public void exportCrowdfund(){

        CollectionIterator<Crowdfund> crowdfundIterator = crowdfundCollection.getIterator();
    }

    private void resolveCrowdfund(int id, String name, int founderID){

        databaseProcessor.executeUpdateAgainstDatabase("DELETE FROM crowdfunds WHERE id=" +
                String.valueOf(id) +
                ";");

        System.out.println("\n\nCrowdfund for: " +
                name +
                "has been successfully finished! Very nice !!\n\n\n");

        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO student_artifacts " +
                "(artifact_id, student_id, status) VALUES (" + String.valueOf(id) + ", " +
                String.valueOf(founderID) + ", 'not used')");

        System.out.println("\nArtifact has been added to founders stash! Very good !!\n");
    }

    public ItemCollection<Crowdfund> getCrowdfunds(){
        importCrowdfunds();
        return crowdfundCollection;
    }

    private void addCrowdfund(Crowdfund crowdfund){
        crowdfundCollection.add(crowdfund);
    }

    public void addCrowdfundToDatabase(Crowdfund crowdfund){
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO crowdfunds (name, total_price, account, founder_id) VALUES ( " + "'" +
                                                        crowdfund.getCrowdfundName() + "', " +
                                                        crowdfund.getCrowdfundTotalPrice() + ", " +
                                                        crowdfund.getCrowdfundAccount() + ", " +
                                                        crowdfund.getCrowdfundFounderID() +
                                                        ")");
    }

    public void updateCrowdfundAccount(int crowdfundID, int amountToAdd){
        databaseProcessor.executeUpdateAgainstDatabase("UPDATE crowdfunds SET account = account + " + 
                                                        String.valueOf(amountToAdd) + 
                                                        " WHERE id = " + 
                                                        String.valueOf(crowdfundID) + 
                                                        ";" );
    }
}

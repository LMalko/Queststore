import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class CrowdfundDao{

    private static ItemCollection<Crowdfund> crowdfundCollection = new ItemCollection<>("Crowdfunds");

    public void importCrowdfunds(){
        String fileName = "csv/CrowdfundDao.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int totalPrice = Integer.parseInt(parts[2]);
                int account = Integer.parseInt(parts[3]);
                String contributorEmail = parts[4];
                Crowdfund crowdfund = new Crowdfund(name, totalPrice, account, contributorEmail);
                addCrowdfund(crowdfund);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exportCrowdfund(){

        CollectionIterator<Crowdfund> crowdfundIterator = crowdfundCollection.getIterator();

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/CrowdfundDao.csv"));
            StringBuilder sb = new StringBuilder();

            while (crowdfundIterator.hasNext()) {

                Crowdfund crowdfund = crowdfundIterator.next();
                sb.append(crowdfund.getCrowdfundId());
                sb.append(",");
                sb.append(crowdfund.getCrowdfundName());
                sb.append(",");
                sb.append(crowdfund.getCrowdfundTotalPrice());
                sb.append(",");
                sb.append(crowdfund.getCrowdfundAccount());
                sb.append(",");
                sb.append(crowdfund.getCrowdfundContributorEmail());
                sb.append("\n");
            }

            br.write(sb.toString());
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public ItemCollection<Crowdfund> getCrowdfunds(){
        importCrowdfunds();
        return crowdfundCollection;
    }

    public void addCrowdfund(Crowdfund crowdfund){
        crowdfundCollection.add(crowdfund);
    }
}

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class CrowdfundDao{

    public void importCrowdfund(){
        String fileName = "CrowdfundDao.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int totalPrice = Integer.parseInt(parts[2]);
                int account = Integer.parseInt(parts[3]);
                Student contributors = parts[4];
                Crowdfund crowdfund = new Crowdfund(id, name, totalPrice, account, contributors);
                crowdfund.addCrowdfund(artifact);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exportCrowdfund(ArrayList<Crowdfund> crowdfundData){

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("CrowdfundDao.csv"));
            StringBuilder sb = new StringBuilder();

            for (Artifacts element : crowdfundData) {
                sb.append(element.getCrowdfundId());
                sb.append(",");
                sb.append(element.getCrowdfundName());
                sb.append(",");
                sb.append(element.getCrowdfundTotalPrice());
                sb.append(",");
                sb.append(element.getCrowdfundAccount());
                sb.append(",");
                sb.append(element.getCrowdfundContributors());
                sb.append("\n");
            }

            br.write(sb.toString());
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

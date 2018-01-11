import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class QuestDao{

    public void importQuests(){
        String fileName = "QuestsDao.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int award = Integer.parseInt(parts[2]);
                String status = parts[3];
                Quests quest = new Quests(id, name, award, status);
                quest.addQuest(quest);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exportQuests(ItemCollection<Quest> questsData){

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("QuestsDao.csv"));
            StringBuilder sb = new StringBuilder();

            for (Quests element : questsData) {
                sb.append(element.getQuestId());
                sb.append(",");
                sb.append(element.getQuestName());
                sb.append(",");
                sb.append(element.getQuestAward());
                sb.append(",");
                sb.append(element.getQuestStatus());
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

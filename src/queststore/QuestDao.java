import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class QuestDao{

    private static ItemCollection<Quest> questsCollection = new ItemCollection<>("Quests");

    public void importQuests(){
        String fileName = "csv/QuestsDao.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split(",");
                String name = parts[0];
                int award = Integer.parseInt(parts[1]);
                String status = parts[2];
                Quest quest = new Quest(name, award, status);
                addQuest(quest);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exportQuests(){

        CollectionIterator<Quest> questsIterator = questsCollection.getIterator();

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/QuestsDao.csv"));
            StringBuilder sb = new StringBuilder();

            while(questsIterator.hasNext()){
                Quest quest = questsIterator.next();
                sb.append(quest.getQuestId());
                sb.append(",");
                sb.append(quest.getQuestName());
                sb.append(",");
                sb.append(quest.getQuestAward());
                sb.append(",");
                sb.append(quest.getQuestStatus());
                sb.append("\n");
            }

            br.write(sb.toString());
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public ItemCollection<Quest> getQuests(){
        return questsCollection;
    }

    public void addQuest(Quest quest){
        questsCollection.add(quest);
    }
}

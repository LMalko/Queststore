import java.io.FileReader;
import java.io.BufferedReader;


public class QuestDao{

    public void importQuests(){
        String fileName = "csv/QuestsDao.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(file_name));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split("\\,");
                String id = parts[0].trim();
                String name = parts[1].trim();
                String award = parts[2].trim();
                Quests quest = new Quests(id, name, award);
                quest.addQuest(quest);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

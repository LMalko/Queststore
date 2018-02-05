import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class QuestDao{

    private static ItemCollection<Quest> questsCollection = new ItemCollection<>("Quests");
    private DBStatementProcessor databaseConnection = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");

    public void importQuests(){
        databaseConnection.connectToDatabase();

        ArrayList<ArrayList<String>> quests = databaseConnection.getArrayListFromQuery("SELECT * FROM quests");

        for(int i=0; i< quests.size(); i++){

            int id = Integer.parseInt(quests.get(i).get(0));
            String name = quests.get(i).get(1);
            int reward = Integer.parseInt(quests.get(i).get(2));
            String category = quests.get(i).get(3);

            Quest quest = new Quest(id, name, reward, category);
            addQuest(quest);

        }
    }

    public void exportQuests(){
        databaseConnection.connectToDatabase();

        CollectionIterator<Quest> questsIterator = questsCollection.getIterator();

        while(questsIterator.hasNext()){
            Quest quest = questsIterator.next();
            databaseConnection.executeUpdateAgainstDatabase("INSERT INTO quests VALUES " +
                                                                String.valueOf(quest.getQuestId()) +
                                                                quest.getQuestName() +
                                                                String.valueOf(quest.getQuestReward()) +
                                                                quest.getQuestCategoryName());
        }
    }

    public Quest getQuestById(int id){
        CollectionIterator<Quest> questIterator = questsCollection.getIterator();

        while (questIterator.hasNext()){
            Quest quest = questIterator.next();

            if(quest.getQuestId() == id) {
                return quest;
            }
        }
        return null;
    }

    public ItemCollection<Quest> getQuests(){
        return questsCollection;
    }

    public void addQuest(Quest quest){
        questsCollection.add(quest);
    }

    public void addQuestToDatabase(Quest quest){
      databaseConnection.executeUpdateAgainstDatabase("INSERT INTO quests (name, reward, category) VALUES ( " + "'" +
                                                          quest.getQuestName() + "', '" +
                                                          String.valueOf(quest.getQuestReward()) + "', '" +
                                                          quest.getQuestCategoryName() + "')");
    }

    public void editQuestOnDatabase(Quest quest){
      databaseConnection.executeUpdateAgainstDatabase("UPDATE quests SET name='" + quest.getQuestName() +
                                                                   "', reward='" + Integer.valueOf(quest.getQuestReward()) +
                                                                   "', category='" + quest.getQuestCategoryName() +
                                                                   "' WHERE id='" + Integer.valueOf(quest.getQuestId()) +
                                                                   "'");
    }
}

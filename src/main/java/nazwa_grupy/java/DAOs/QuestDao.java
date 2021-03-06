package nazwa_grupy.java.DAOs;

import nazwa_grupy.java.Iterator_DBProcessor.CollectionIterator;
import nazwa_grupy.java.Iterator_DBProcessor.DBStatementProcessor;
import nazwa_grupy.java.Models.ItemCollection;
import nazwa_grupy.java.Models.Quest;

import java.util.ArrayList;

public class QuestDao {

    private static ItemCollection<Quest> questsCollection = new ItemCollection<>("Quests");
    private DBStatementProcessor databaseConnection = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");
    private final static int idIndex = 0;
    private final static int nameIndex = 1;
    private final static int rewardIndex = 2;
    private final static int categoryIndex = 3;

    public void importQuests() {
        questsCollection.clear();
        databaseConnection.connectToDatabase();

        ArrayList<ArrayList<String>> quests = databaseConnection.getArrayListFromQuery("SELECT * FROM quests");

        for(int i=0; i< quests.size(); i++) {

            int id = Integer.parseInt(quests.get(i).get(idIndex));
            String name = quests.get(i).get(nameIndex);
            int reward = Integer.parseInt(quests.get(i).get(rewardIndex));
            String category = quests.get(i).get(categoryIndex);

            Quest quest = new Quest(id, name, reward, category);
            addQuest(quest);
        }
    }

    public Quest getQuestById(int id) {
        CollectionIterator<Quest> questIterator = questsCollection.getIterator();

        while (questIterator.hasNext()){
            Quest quest = questIterator.next();

            if(quest.getQuestId() == id) {
                return quest;
            }
        }
        return null;
    }

    public ItemCollection<Quest> getQuests() {
        importQuests();
        return questsCollection;
    }

    public void addQuest(Quest quest){
        questsCollection.add(quest);
    }

    public void addQuestToDatabase(Quest quest) {
      databaseConnection.executeUpdateAgainstDatabase("INSERT INTO quests (name, reward, category) VALUES ( " + "'" +
                                                          quest.getQuestName() + "', '" +
                                                          String.valueOf(quest.getQuestReward()) + "', '" +
                                                          quest.getQuestCategoryName() + "')");
    }

    public void editQuestOnDatabase(Quest quest) {
      databaseConnection.executeUpdateAgainstDatabase("UPDATE quests SET name='" + quest.getQuestName() +
                                                                   "', reward='" + Integer.valueOf(quest.getQuestReward()) +
                                                                   "', category='" + quest.getQuestCategoryName() +
                                                                   "' WHERE id='" + Integer.valueOf(quest.getQuestId()) +
                                                                   "'");
    }

    public void chooseStudentQuest(int studentID) {
        databaseConnection.executeQueryAgainstDatabase("SELECT student_quests.quests_id AS ID, " +
                                                       "student_quests.student_id AS Student, " +
                                                       "quests.name AS 'quest name', " +
                                                       "student_quests.status FROM student_quests " +
                                                       "JOIN quests ON student_quests.quests_id=quests.id " +
                                                       "WHERE student_quests.student_id = " + String.valueOf(studentID) +
                                                       " AND student_quests.status = 'not done';");
    }

    public void displayStudentQuest(int studentID) {
        System.out.println("\n\nQuests:\n\n\n");
        databaseConnection.executeQueryAgainstDatabase("SELECT quests.name AS 'quest name', student_quests.status " +
                                                       "FROM student_quests JOIN quests ON student_quests.quests_id=quests.id " +
                                                       "WHERE student_quests.student_id = " + String.valueOf(studentID) + ";");
        System.out.println("\n\n");
    }

    public void setQuestStatusAsDone(int questID) {
        databaseConnection.executeUpdateAgainstDatabase("UPDATE student_quests SET status = 'done' WHERE quests_id =" + questID);
    }

    public void addQuestToStudent(int questID, int studentID) {
        databaseConnection.executeUpdateAgainstDatabase("INSERT INTO student_quests (quests_id, student_id, status) VALUES ('" +
                                                        String.valueOf(questID) + "','" +
                                                        String.valueOf(studentID) +
                                                        "', 'not done')");
    }

    public boolean userDontHaveQuest(int choice, int studentId) {
        int questId = databaseConnection.getIntegerDataFromQuery("SELECT quests_id FROM student_quests " +
                                                                 "WHERE quests_id=" + choice +
                                                                 " AND student_id=" + studentId , "quests_id");
        if(questId == choice){
            return false;
        } else {
            return true;
        }
    }
}

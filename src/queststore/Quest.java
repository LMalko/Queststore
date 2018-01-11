import java.util.ArrayList;


public class Quest{
    private String name;
    private int id;
    private int award;
    private String status;
    private static ItemCollection<Quest> questsCollection = new ItemCollection<>("Quests");

    public Quest(int id, String name, int award, String status){
        this.name = name;
        this.id = id;
        this.award = award;
        this.status = status;
    }

    public void setQuestName(String name){
        this.name = name;
    }

    public String getQuestName(){
        return this.name;
    }

    public int getQuestId(){
        return this.id;
    }

    public void setQuestId(int id){
        this.id = id;
    }

    public int getQuestAward(){
        return this.award;
    }

    public String getQuestStatus(){
        return this.status;
    }

    public void setQuestStatus(String status){
        this.status = status;
    }

    public void setQuestAward(int award){
        this.award = award;
    }

    public ItemCollection<Quest> getQuests(){
        return questsCollection;
    }

    public void addQuest(Quest quest){
        questsCollection.add(quest);
    }
}

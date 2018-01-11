import java.util.ArrayList;


public class Quest{
    private String name;
    private int award;
    private static int id;
    private int questID;
    private String status;

    public Quest(String name, int award, String status){
        this.name = name;
        this.questID = id++;
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
        return this.questID;
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

}

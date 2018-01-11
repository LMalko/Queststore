import java.util.ArrayList;

class Group{
    private String groupName;

    public Group(String name){
        this.groupName = name;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String newName){
        this.groupName = newName;
    }
}

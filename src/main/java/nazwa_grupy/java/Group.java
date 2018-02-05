import java.util.ArrayList;

class Group{
    private String groupName;
    private int groupId;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(int groupId, String name){
        this.groupId = groupId;
        this.groupName = name;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public int getGroupId() {return this.groupId; }

    public void setGroupName(String newName){
        this.groupName = newName;
    }
}

import java.util.ArrayList;

class Group{
    private String groupName;
    private static ArrayList<Group> allGroupsCollection;

    public Group(String name){
        this.groupName = name;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String newName){
        this.groupName = newName;
    }

    public void addGroupToGroupCollection(Group group){
        allGroupsCollection.add(group);
    }

    public  static Group getGroupByName(String name){
        for (Group group : allGroupsCollection){
            if (group.getGroupName().equals(name)){
                return group;
            }
        }
        return null;
    }

    public static ArrayList<Group> getAllGroups(){
        return allGroupsCollection;
    }
}

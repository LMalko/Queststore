import java.util.ArrayList;

public class GroupDao{

    private static ItemCollection<Group> groupsCollection = new ItemCollection<>("Group");
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");

    public void importGroups(){
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> group = databaseProcessor.getArrayListFromQuery("SELECT * FROM groups");
        for(int i =0; i < group.size(); i++){
            int groupId = Integer.parseInt(group.get(i).get(0));
            String groupName = group.get(i).get(1);
            Group newGroup = new Group(groupId, groupName);
            addGroup(newGroup);
        }
    }

    public void addGroupToDatabase(Group group){
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO groups (name) VALUES ( " + "'" +
                group.getGroupName() +
                "');");
    }

    public ItemCollection<Group> getGroups(){
        return groupsCollection;
    }

    public void addGroup(Group group){
        groupsCollection.add(group);
    }

    public Group getGroupByName(String name){
        groupsCollection.clear();
        importGroups();
        CollectionIterator<Group> groupIterator = groupsCollection.getIterator();
        while(groupIterator.hasNext()){
            Group group = groupIterator.next();
            if (group.getGroupName().equals(name)){
                return group;
            }
        }
        return null;
    }
}

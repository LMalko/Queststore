import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.sql.Connection;


public class GroupDao{

    private static ItemCollection<Group> groupsCollection = new ItemCollection<>("Group");
    private JDBConnection databaseConnection = new JDBConnection("jdbc:sqlite:db/questStore.db");

    public void importGroups(){
        databaseConnection.connectToDatabase();

        ArrayList<ArrayList<String>> group = databaseConnection.getArrayListFromQuery("SELECT * FROM groups");
        for(int i =0; i < group.size(); i++){
            int groupId = Integer.parseInt(group.get(i).get(0));
            String groupName = group.get(i).get(1);
            Group newGroup = new Group(groupId, groupName);
            addGroup(newGroup);
        }
    }

    public void addGroupToDatabase(Group group){
        databaseConnection.executeUpdateAgainstDatabase("INSERT INTO groups (name) VALUES ( " + "'" +
                group.getGroupName() +
                "')");
    }

    public ItemCollection<Group> getGroups(){
        return groupsCollection;
    }

    public void addGroup(Group group){
        groupsCollection.add(group);
    }

    public Group getGroupByName(String name){

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

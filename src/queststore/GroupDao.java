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
        String row;
        for(int i =0; i < group.size(); i++){

            String groupName = group.get(i).get(0);
            Group newGroup = new Group(groupName);
            addGroup(newGroup);
        }
    }

    public void exportGroups(){

        CollectionIterator<Group> groupIterator = groupsCollection.getIterator();

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/groups.csv"));
            StringBuilder sb = new StringBuilder();

            while(groupIterator.hasNext()){
                Group group = groupIterator.next();
                sb.append(group.getGroupName());
                sb.append("\n");
            }

            br.write(sb.toString());
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void closeReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ItemCollection<Group> getGroups(){
        //importGroups();
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

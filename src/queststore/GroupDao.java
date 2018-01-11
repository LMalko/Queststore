import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class GroupDao{

    private static ItemCollection<Group> groupsCollection = new ItemCollection<>("Group");
    private BufferedReader br = null;

    public void importGroups(){
        String fileName = "csv/groups.csv";

        try{
            br = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = br.readLine()) != null){
                String[] parts = row.split("\n");
                String name = parts[0];
                Group group = new Group(name);
                addGroup(group);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        } finally {
            closeReader(br);
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

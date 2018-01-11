import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;

class GroupDao{

    public static void importGroups(){
        String fileName = "csv/groups.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split("\n");
                String groupName = parts[0];
                Group group = new Group(groupName);
                Group.addGroupToGroupCollection(group);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void exportGroups(ArrayList<Group> allGroupsCollection){

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/groups.csv"));
            StringBuilder sb = new StringBuilder();

            for (Group group : allGroupsCollection) {
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
}

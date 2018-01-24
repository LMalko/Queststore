import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class ExperienceLevelsDao{


    private JDBConnection databaseConnection = new JDBConnection("jdbc:sqlite:db/questStore.db");

    public void importExperienceLevels(){
        databaseConnection.connectToDatabase();

        ArrayList<ArrayList<String>> experience = databaseConnection.getArrayListFromQuery("SELECT * FROM experience_levels");
        String row;
        for(int i =0; i < experience.size(); i++){

            int money_required = Integer.parseInt(experience.get(i).get(0));
            String name = experience.get(i).get(1);

            ExperienceLevel experienceLevel = new ExperienceLevel(money_required, name);
            experienceLevel.addExperienceLevel(experienceLevel);
        }

    }

    public void exportExperienceLevels(ItemCollection<ExperienceLevel> experienceLevelData){

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/experienceLevels.csv"));
            StringBuilder sb = new StringBuilder();
            CollectionIterator<ExperienceLevel> levelIterator = experienceLevelData.getIterator();
            while(levelIterator.hasNext()) {
                ExperienceLevel level = levelIterator.next();
                sb.append(level.getLevel());
                sb.append(",");
                sb.append(level.getLevelName());
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

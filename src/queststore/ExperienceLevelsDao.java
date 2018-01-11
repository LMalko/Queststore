import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class ExperienceLevelsDao{

    public void importExperienceLevels(){
        String fileName = "csv/experienceLevels.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split(",");
                int level = Integer.parseInt(parts[0]);
                String levelName = parts[1];
                ExperienceLevel experienceLevel = new ExperienceLevel(level, levelName);
                experienceLevel.addExperienceLevel(experienceLevel);
            }
        }
        catch (IOException e){
            e.printStackTrace();
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

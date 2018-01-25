import java.util.ArrayList;

public class ExperienceLevelsDao{


    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");

    public void importExperienceLevels(){
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> experience = databaseProcessor.getArrayListFromQuery("SELECT * FROM experience_levels");
        String row;
        for(int i =0; i < experience.size(); i++){

            int money_required = Integer.parseInt(experience.get(i).get(0));
            String name = experience.get(i).get(1);

            ExperienceLevel experienceLevel = new ExperienceLevel(money_required, name);
            experienceLevel.addExperienceLevel(experienceLevel);
        }
    }

    public void addExperienceLevelToDatabase(ExperienceLevel experienceLevel){
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO experience_levels (name, money_required) VALUES (" + "'" +
                experienceLevel.getLevelName() + "' ,'" +
                experienceLevel.getLevelMoneyRequired() +
                "');");
    }

}

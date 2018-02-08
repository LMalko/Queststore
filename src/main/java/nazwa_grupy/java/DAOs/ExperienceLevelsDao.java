package nazwa_grupy.java.DAOs;

import nazwa_grupy.java.Iterator_DBProcessor.DBStatementProcessor;
import nazwa_grupy.java.Models.ExperienceLevel;

import java.util.ArrayList;

public class ExperienceLevelsDao {

    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");
    private final static int moneyRequiredIndex = 0;
    private final static int experienceLevelNameIndex = 1;

    public void importExperienceLevels() {
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> experience = databaseProcessor.getArrayListFromQuery("SELECT * FROM experience_levels");
        for(int i =0; i < experience.size(); i++) {

            int moneyRequired = Integer.parseInt(experience.get(i).get(moneyRequiredIndex));
            String name = experience.get(i).get(experienceLevelNameIndex);

            ExperienceLevel experienceLevel = new ExperienceLevel(moneyRequired, name);
            experienceLevel.addExperienceLevel(experienceLevel);
        }
    }

    public void addExperienceLevelToDatabase(ExperienceLevel experienceLevel) {
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO experience_levels (name, money_required) VALUES (" + "'" +
                experienceLevel.getLevelName() + "' ,'" +
                experienceLevel.getLevelMoneyRequired() +
                "');");
    }
}

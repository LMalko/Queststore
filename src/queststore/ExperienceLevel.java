public class ExperienceLevel{
    int level;
    String levelName;
    private static ItemCollection<ExperienceLevel> experienceLevelCollection = new ItemCollection<>("ExperienceLevel");


    public ExperienceLevel(int level, String levelName){
        this.level = level;
        this.levelName = levelName;
    }

    public int getLevel(){
        return this.level;
    }

    public String getLevelName(){
        return this.levelName;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setLevelName(String levelName){
        this.levelName = levelName;
    }

    public ItemCollection<ExperienceLevel> getExperienceLevels(){
        ExperienceLevelDao experienceLevelDaoDao = new ExperienceLevelDao();
        experienceLevelDaoDao.importExperienceLevel();
        return experienceLevelCollection;
    }

    public void addExperienceLevel(ExperienceLevel experienceLevel){
        experienceLevelCollection.add(experienceLevel);
    }
}

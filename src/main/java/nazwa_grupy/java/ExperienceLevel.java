public class ExperienceLevel{
    int money_required;
    String levelName;
    private static ItemCollection<ExperienceLevel> experienceLevelCollection = new ItemCollection<>("ExperienceLevel");


    public ExperienceLevel(int money_required, String levelName){
        this.money_required = money_required;
        this.levelName = levelName;
    }

    public int getLevelMoneyRequired(){
        return this.money_required;
    }

    public String getLevelName(){
        return this.levelName;
    }

    public void setLevel(int money_required){
        this.money_required = money_required;
    }

    public void setLevelName(String levelName){
        this.levelName = levelName;
    }

    public ItemCollection<ExperienceLevel> getExperienceLevels(){
        return experienceLevelCollection;
    }

    public void addExperienceLevel(ExperienceLevel experienceLevel){
        experienceLevelCollection.add(experienceLevel);
    }
}

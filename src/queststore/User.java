//change to abstract when implementing Admin, Mentor and Student classes
abstract class User{
    protected String name;
    protected String surname;
    protected int id;
    protected String login;
    protected String password;
    protected String status;
    protected int groupIndex;
    protected String groupName;
    protected int wallet;
    protected String experienceLevel;
    private static int counter;
    //public ItemCollection userArtifacts;

    public User(String name, String surname, String password, String status){
        this.name = name;
        this.surname = surname;
        this.login = name.toLowerCase() + surname.toLowerCase() + "@cc.com";
        this.password = password;
        this.status = status;
        this.id = counter;
        this.groupIndex = 0;
        this.wallet = 0;
        this.experienceLevel = " ";
        counter += 1;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public int getId(){
        return this.id;
    }

    public String getLogin(){
        return this.login;
    }

    public String getPassword(){
        return this.password;
    }

    public String getStatus(){
        return this.status;
    }

    public int getUserWallet(){
        return this.wallet;
    }

    public String getUserExperienceLevel(){
        return this.experienceLevel;
    }

    public String getUserGroupName(){
        return this.groupName;
    }

    public int getUserGroupIndex() { return this.groupIndex; }
}

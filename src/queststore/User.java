
abstract class User{
    protected String name;
    protected String surname;
    protected int id;
    protected String login;
    protected String password;
    protected String status;
    protected int groupId;
    protected String groupName;
    protected int wallet;
    protected String experienceLevel;
    //public ItemCollection userArtifacts;

    public User(String name, String surname, String password, String status){
        this.name = name;
        this.surname = surname;
        this.login = name.toLowerCase() + surname.toLowerCase() + "@cc.com";
        this.password = password;
        this.status = status;
        this.groupId = 0;
        this.wallet = 0;
        this.experienceLevel = " ";
    }

    public User(int id, String name, String surname, String password, String status, int groupId){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = name.toLowerCase() + surname.toLowerCase() + "@cc.com";
        this.password = password;
        this.status = status;
        this.groupId = groupId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
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

    public int getUserGroupId() { return this.groupId; }
}

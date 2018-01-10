//change to abstract when implementing Admin, Mentor and Student classes
abstract class User{
    protected String name;
    protected String surname;
    protected int id;
    protected String login;
    protected String password;
    protected String status;
    private static int counter;
    //public ItemCollection userArtifacts;

    public User(String login, String password, String status){
        this.login = login;
        this.password = password;
        this.status = status;
        this.id = counter;
        counter += 1;
    }

    public User(String name, String surname, String login, String password, String status){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public void setName(String name){
        this.name = name;
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
}

package queststore;

public class Mentor extends User{

    private String group;

    public Mentor(String name, String login, String password, String status, String group){
        super(name, login, password, status);
        this.group = group;
    }

    public String getMentorGroup(){
        return group;
    }
}

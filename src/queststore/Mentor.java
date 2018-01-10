public class Mentor extends User{

    private String group;

    public Mentor(String login, String password, String status){
        super(login, password, status);
    }

    public Mentor(String name, String surname, String login, String password, String status, String group){
        super(name, surname, login, password, status);
        this.group = group;
    }

    public String getMentorGroup(){
        return group;
    }
}

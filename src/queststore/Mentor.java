public class Mentor extends User{

    private String group;

    public Mentor(String name, String surname, String password){
        super(name, surname, password, "mentor");
        this.group = "";
    }

    public String getMentorGroup(){
        return group;
    }
}

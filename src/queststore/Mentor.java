public class Mentor extends User{

    private Group group;

    public Mentor(String name, String surname, String password){
        super(name, surname, password, "mentor");
        this.group = null;
    }

    public Group getMentorGroup(){
        return group;
    }

    public void setMentorGroup(Group group){
        this.group = group;
    }
}

public class Mentor extends User{

    private Group group;

    public Mentor(String name, String surname, String password){
        super(name, surname, password, "mentor");
        this.group = null;
    }

    public String getMentorGroupName(){
        return group.getGroupName();
    }

    public void setMentorGroup(Group group){
        this.group = group;
        this.groupName = group.getGroupName();
    }
}

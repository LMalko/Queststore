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

    public void setMentorName(String name){
        this.name = name;
    }

    public void setMentorSurname(String surname){
        this.surname = surname;
    }

    public void setMentorPassword(String password){
        this.password = password;
    }

    public void setMentorLogin(String name, String surname){
        this.login = name.toLowerCase() + surname.toLowerCase() + "@cc.com";
    }
}

public class Mentor extends User{

    private Group group;

    public Mentor(String name, String surname, String password){
        super(name, surname, password, "mentor");
    }

    public Mentor(String name, String surname, String password, int groupIndex){
        super(name,surname,password,"mentor");
        this.groupIndex = groupIndex;
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

    @Override
    public String toString(){
        return  String.format("ID: %d, NAME: %s, SURNAME: %s, EMAIL: %s, GROUP: %s",
                              this.id,
                              this.name,
                              this.surname,
                              this.login,
                              this.groupName);
    }
}

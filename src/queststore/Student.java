public class Student extends User{

    private int wallet;
    private int experience;
    private Group group;

    public Student(String name, String surname, String password){
        super(name, surname, password, "student");
        this.wallet = 0;
        this.experience = 0;
        this.group = null;
    }

    public Integer getStudentWallet(){
        return wallet;
    }

    public Integer getStudentExperiance(){
        return experience;
    }

    public void setStudentGroup(Group group){
        this.group = group;

    public Group getStudentGroup(){
        return this.group;

    }
}

public class Student extends User{

    private int wallet;
    private int experience;
    private Group group;
    private ItemCollection<Integer> myArtifacts;

    public Student(String name, String surname, String password){
        super(name, surname, password, "student");
        // this.experience = 0;
        // this.group = null;
    }

    public Student(int id, String name, String surname, String password, Group group, int wallet) {
        super(id,name,surname,password,"student", group);
        this.wallet = wallet;
    }

    public Integer getStudentWallet(){
        return this.wallet;
    }

    public Integer getStudentExperiance(){
        return experience;
    }

    public void setStudentGroup(Group group){
        this.group = group;
    }

    public String getStudentGroupName(){
        return this.group.getGroupName();

    }

    public void reduceWallet(int amount){
        this.wallet = this.wallet - amount;
    }
}

public class Student extends User{

    private int wallet;
    private int experience;
    private String group;

    public Student(String name, String surname, String password){
        super(name, surname, password, "student");
        this.wallet = 0;
        this.experience = 0;
        this.group = "";
    }

    public Integer getStudentWallet(){
        return wallet;
    }

    public Integer getStudentExperiance(){
        return experience;
    }

    public String getStudentGroup(){
        return this.group;
    }
}

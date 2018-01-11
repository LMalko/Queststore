public class Student extends User{

    private int wallet;
<<<<<<< HEAD
    private int experiance;
    private String group;

    public Student(String login, String password, String status, String group){
        super(login, password, status);
        this.group = group;
    }

    public Student(String name, String login, String surname,
                   String password, String status, String group, int wallet, int experiance){
            super(name, surname, login, password, status);
            this.wallet = wallet;
            this.experiance = experiance;
            this.group = group;
=======
    private int experience;
    private String group;

    public Student(String name, String surname, String password){
        super(name, surname, password, "student");
        this.wallet = 0;
        this.experience = 0;
        this.group = "";
>>>>>>> 74137e4cddb3b1e3311950956ce07a5eec1cf17a
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

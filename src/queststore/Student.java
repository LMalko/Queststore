public class Student extends User{

    private int wallet;
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
    }

    public Integer getStudentWallet(){
        return wallet;
    }

    public Integer getStudentExperiance(){
        return experiance;
    }

    public String getStudentGroup(){
        return this.group;
    }
}

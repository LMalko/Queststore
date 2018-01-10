package queststore;

public class Student extends User{

    private int wallet;
    private int experiance;

    public Student(String name, String login,
         String password, String status, String group, int wallet, int experiance){
            super(name, login, password, status);
            this.wallet = wallet;
            this.experiance = experiance;
    }

    public Integer getStudentWallet(){
        return wallet;
    }

    public Integer getStudentExperiance(){
        return experiance;
    }
}

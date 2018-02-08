package nazwa_grupy.java.Models;

public class Student extends User {

    private int wallet;
    private int totalIncome;
    private String experience;

    public Student(String name, String surname, String password) {
        super(name, surname, password, "student");
    }

    public Student(int id, String name, String surname, String password, Group group, int wallet, int totalIncome, String experience) {
        super(id,name,surname,password,"student", group);
        this.wallet = wallet;
        this.totalIncome = totalIncome;
        this.experience = experience;
    }

    public int getStudentWallet(){
        return this.wallet;
    }

    public int getStudentTotalIncome(){
        return totalIncome;
    }

    public void setStudentGroup(Group group){
        this.group = group;
    }

    public String getStudentExperienceLevel(){
        return this.experience;
    }

    public void reduceWallet(int amount){
        this.wallet = this.wallet - amount;
    }
}

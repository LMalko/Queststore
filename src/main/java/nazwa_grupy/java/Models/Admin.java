package nazwa_grupy.java.Models;

public class Admin extends User {

    public Admin(String name, String surname, String password){
        super(name, surname, password, "admin");
    }
}

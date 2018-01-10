public class Admin extends User{

    public Admin(String login, String password, String status){
        super(login, password, status);
    }

    public Admin(String name, String surname, String login, String password, String status){
        super(name, surname, login, password, status);

    }
}

import java.util.ArrayList;

class LoginController{
    private LoginView view = new LoginView();
    private UsersDao dao = new UsersDao();
    private static ArrayList<User> usersCollection = new ArrayList<User>();

    public void login(){
        view.clearScreen();
        getUsersFromDao();
        String userLogin = view.getLogin();
        String userPassword = view.getPassword();
        String userStatus = getUserStatus(userLogin, userPassword);
        if (checkIfUserExists(userLogin) && checkUserPassword(userLogin, userPassword)){
            runProperUserPanel(userLogin, userPassword, userStatus);
        }
    }

    private void getUsersFromDao(){
        usersCollection = dao.getUsersCollection();
    }

    private boolean checkIfUserExists(String login){
        for (User user : usersCollection){
            if (login.equals(user.getLogin())){
                return true;
            }
        }
        view.displayText("Given user does not exists!");
        return false;
    }

    private boolean checkUserPassword(String login, String password){
        for (User user : usersCollection){
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                return true;
            }
        }
        view.displayText("Wrong password!");
        return false;
    }

    private String getUserStatus(String login, String password){
        for (User user : usersCollection){
            if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                return user.getStatus();
            }
        }
        return null;
    }

    private void runProperUserPanel(String userLogin, String userPassword, String userStatus){
        if(userStatus.equals("admin")){
            User user = new Admin(userLogin, userPassword, userStatus);
            AdminController controller = new AdminController();
            controller.startAdminPanel();
        }
        else if(userStatus.equals("mentor")){
            User user = new Mentor(userLogin, userPassword, userStatus);
            MentorController controller = new MentorController();
            controller.startMentorPanel();
        }
        else if(userStatus.equals("student")){
            User user = new Student(userLogin, userPassword, userStatus);
            StudentController controller = new StudentController();
            controller.startStudentPanel();
        }
    }
}

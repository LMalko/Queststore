package nazwa_grupy.java.Controllers;

import nazwa_grupy.java.DAOs.UsersDao;
import nazwa_grupy.java.Models.Admin;
import nazwa_grupy.java.Models.Mentor;
import nazwa_grupy.java.Models.Student;
import nazwa_grupy.java.Models.User;
import nazwa_grupy.java.Views.LoginView;

import java.util.ArrayList;

public class LoginController{
    private LoginView view = new LoginView();
    private UsersDao dao = new UsersDao();
    private ArrayList<User> usersCollection = dao.getUsersCollection();
    public void login(){
        boolean isLoginSession = true;

        while (isLoginSession){
            view.clearScreen();
            String userLogin = view.getLogin();
            String userPassword = view.getPassword();
            String userStatus = getUserStatus(userLogin, userPassword);
            if (checkIfUserExists(userLogin) && checkUserPassword(userLogin, userPassword)){
                runProperUserPanel(userLogin, userPassword, userStatus);
            }
        }
    }

    private boolean checkIfUserExists(String login){
        try{
            for (User user : usersCollection){
                if (login.equals(user.getLogin())){
                    return true;
                }
            }
            view.displayText("Given user does not exists!");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return false;
    }

    private boolean checkUserPassword(String login, String password){
        try{
            for (User user : usersCollection){
                if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                    return true;
                }
            }
            view.displayText("Wrong password!");
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
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

    private User getUserAccount(String userLogin, String userPassword){
        for (User user : usersCollection) {
            if(userLogin.equals(user.getLogin()) && userPassword.equals(user.getPassword())){
                return user;
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
            Student user = (Student)getUserAccount(userLogin, userPassword);
            StudentController controller = new StudentController();
            controller.startStudentPanel(user);
        }
    }
}

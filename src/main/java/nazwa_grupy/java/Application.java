package nazwa_grupy.java;

import nazwa_grupy.java.Controllers.LoginController;

public class Application{

    public void startApp(){
        LoginController loginProcedure = new LoginController();
        loginProcedure.login();
    }
}

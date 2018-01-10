class LoginController{
    private LoginView view = new LoginView();
    private UsersDao dao = new UsersDao();
    private static ArrayList<User> usersCollection = new ArrayList<User>;

    public void login(){
        getUsersFromDao();
        String userLogin = view.getLogin();
        String userPassword = view.getPassword();

        if (checkIfUserExists(userLogin) && checkUserPassword(userLogin, userPassword){
            User user = new User(userLogin, userPassword);
            user.runPanel();
        }
    }

    private void getUsersFromDao(){
        usersCollection = dao.getUsersData();
    }

    private boolean checkIfUserExists(String login){
        for (User user : usersCollection){
            if (userLogin.equals(user.getLogin())){
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
}

class AdminController{

    UsersDao dao = new UsersDao();
    UserView view = new UserView();

    public void startAdminPanel(){
        boolean isRuntime = true;

        while(isRuntime){
            view.displayUserMenu("txt/adminMenu.txt");
            handleAdminPanelOptions();
        }
    }

    public void handleAdminPanelOptions(){
        String choice = view.getUserInput("Choose your option: ");
        if (choice.equals("0")){
            System.exit(0);
        }
        else if (choice.equals("1")){
            createNewMentor();
        }
        else if (choice.equals("2")){

        }
        else if (choice.equals("3")){

        }
        else if (choice.equals("4")){

        }
        else if (choice.equals("5")){

        }
        else if (choice.equals("6")){

        }
    }

    public void createNewMentor(){
        String mentorName = view.getUserInput("Enter mentor's name: ");
        String mentorSurname = view.getUserInput("Enter mentor's surname: ");
        String mentorPassword = view.getUserInput("Enter mentor's password: ");
        Mentor newMentor = new Mentor(mentorName, mentorSurname, mentorPassword);
        dao.addUserToUsersCollection(newMentor);
        dao.saveUsersToFile();
    }
}

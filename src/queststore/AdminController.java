class AdminController{

    UserView view = new UserView();

    public void startAdminPanel(){
        view.displayUserMenu("txt/adminMenu.txt");
    }
}

class StudentController{

    private UserView view = new UserView();
    private ItemCollection<Artifacts> artifactsCollection = Artifacts.getArtifactsCollection();
    private ItemCollection<Crowdfunds> crowdfundsCollection = Crowdfund.getCrowdfundsCollection();

    public void startStudentPanel(){
        view.displayUserMenu("txt/studentMenu.txt");
    }

    public getAllCrowdfunds(){
        return crowdfundsCollection;
    }

    public getAllArtifacts(){
        return artifactsCollection;
    }
}

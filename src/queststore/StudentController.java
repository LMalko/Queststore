class StudentController{

    private UserView view = new UserView();
    private ItemCollection<Artifacts> artifactsCollection = Artifacts.getArtifactsCollection();
    private ItemCollection<Crowdfunds> crowdfundsCollection = Crowdfund.getCrowdfundsCollection();

    public void startStudentPanel(){
        view.displayUserMenu("txt/studentMenu.txt");
    }

    public ItemCollection<Crowdfunds> getAllCrowdfunds(){
        return crowdfundsCollection;
    }

    public ItemCollection<Artifacts> getAllArtifacts(){
        return artifactsCollection;
    }

    private void createCrowdfund(Integer id){

    }

    private void buyArtifact(Integer id){

    }

    private void joinCrowdfund(Integer id){
        
    }

}

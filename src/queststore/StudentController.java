class StudentController{

    private UserView view = new UserView();
    private ItemCollection<Artifact> artifactsCollection = Artifacts.getArtifacts();
    private ItemCollection<Crowdfund> crowdfundsCollection = Crowdfund.getCrowdfundsCollection();

    CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();
    CollectionIterator<Crowdfund> crowdfundIterator = crowdfundsCollection.getIterator();

    public void startStudentPanel(){
        view.displayUserMenu("txt/studentMenu.txt");
    }

    public ItemCollection<Crowdfund> getAllCrowdfunds(){
        return crowdfundsCollection;
    }

    public ItemCollection<Artifact> getAllArtifacts(){
        return artifactsCollection;
    }

    private void createCrowdfund(){
        while(strIterator.hasNext()){
            System.out.println(strIterator.next());

        }
    }

    private void buyArtifact(Integer id){

    }

    private void joinCrowdfund(Integer id){
    }

}

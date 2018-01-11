class StudentController{

    private UserView view = new UserView();
    private ItemCollection<Artifact> artifactsCollection = Artifact.getArtifacts();
    private ItemCollection<Crowdfund> crowdfundsCollection = Crowdfund.getCrowdfunds();

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
        while(artifactIterator.hasNext()){
            System.out.println(artifactsCollection.next());
        }

    }

    private void buyArtifact(Integer id){

    }

    private void joinCrowdfund(Integer id){
        while(crowdfundIterator.hasNext()){
            System.out.println(crowdfundIterator.next());
        }
    }
}

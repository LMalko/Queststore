class StudentController{

    private UserView view = new UserView();
    private ItemCollection<Artifact> artifactsCollection = Artifact.getArtifacts();
    private ItemCollection<Crowdfund> crowdfundsCollection = Crowdfund.getCrowdfunds();

    CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();
    CollectionIterator<Crowdfund> crowdfundIterator = crowdfundsCollection.getIterator();

    public void startStudentPanel(){
        view.displayUserMenu("txt/studentMenu.txt");
        handleStudentPanelOptions();
    }

    private void handleStudentPanelOptions(){
        String choice = view.getUserInput("Choose your option: ");
        if (choice.equals("0")){
            System.exit(0);
        }
        else if (choice.equals("1")){
            return this.wallet;
        }
        else if (choice.equals("2")){
            buyArtifact(id);
        }
        else if (choice.equals("3")){
            createCrowdfund()
        }
        else if (choice.equals("4")){
            getAllCrowdfunds();
        }
        else if (choice.equals("5")){
            joinCrowdfund(Integer id);
        }
    }

    public ItemCollection<Crowdfund> getAllCrowdfunds(){
        return crowdfundsCollection;
    }

    public ItemCollection<Artifact> getAllArtifacts(){
        return artifactsCollection;
    }

    private void createCrowdfund(){
        while(artifactIterator.hasNext()){
            System.out.println(artifactIterator.next());
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

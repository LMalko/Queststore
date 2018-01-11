class StudentController{

    private UserView view = new UserView();
    private ItemCollection<Artifact> artifactsCollection = Artifact.getArtifacts();
    private ItemCollection<Crowdfund> crowdfundsCollection = Crowdfund.getCrowdfunds();

    CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();
    CollectionIterator<Crowdfund> crowdfundIterator = crowdfundsCollection.getIterator();

    private Student student;

    public void startStudentPanel(Student student){
        this.student = student;
        view.displayUserMenu("txt/studentMenu.txt");
        handleStudentPanelOptions();
    }

    private void handleStudentPanelOptions(){
        String choice = view.getUserInput("Choose your option: ");
        if (choice.equals("0")){
            System.exit(0);
        }
        else if (choice.equals("1")){
            System.out.println(student.getStudentWallet());
        }
        else if (choice.equals("2")){
            //buyArtifact();
        }
        else if (choice.equals("3")){
            createCrowdfund();
        }
        else if (choice.equals("4")){
            getAllCrowdfunds();
        }
        else if (choice.equals("5")){
            //joinCrowdfund(id);
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

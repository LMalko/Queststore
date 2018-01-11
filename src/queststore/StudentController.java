class StudentController{

    private UserView view = new UserView();
    private ArtifactsDao artifactsDao = new ArtifactsDao();
    private CrowdfundDao crowdfundsDao = new CrowdfundDao();
    private ItemCollection<Artifact> artifactsCollection = artifactsDao.getArtifacts();
    private ItemCollection<Crowdfund> crowdfundsCollection = crowdfundsDao.getCrowdfunds();

    private CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();
    private CollectionIterator<Crowdfund> crowdfundIterator = crowdfundsCollection.getIterator();

    private Student student;

    public void startStudentPanel(Student student){
        boolean isRuntime = true;
        this.student = student;
        
        while(isRuntime){
            view.displayUserMenu("txt/studentMenu.txt");
            handleStudentPanelOptions();
            String choice = view.getUserInput("Press anything to continue");

        }
    }

    private void handleStudentPanelOptions(){
        String choice = view.getUserInput("Choose your option: ");
        if (choice.equals("0")){
            System.exit(0);
        }else if (choice.equals("1")){
            System.out.println("\n\nWallet is:");
            System.out.println(student.getStudentWallet());
        }else if (choice.equals("2")){
            buyArtifact();
        }else if (choice.equals("3")){
            createCrowdfund();
        }else if (choice.equals("4")){
            returnAllCrowdfunds();
        }else if (choice.equals("5")){
            joinCrowdfund();
        }else{
            System.out.println("No such choice");
        }
        //Restart iterators.
        this.artifactIterator = artifactsCollection.getIterator();
        this.crowdfundIterator = crowdfundsCollection.getIterator();
    }

    private void returnAllCrowdfunds(){
        System.out.println("Crowdfunds:");
        while(crowdfundIterator.hasNext()){
            System.out.println(crowdfundIterator.next());
        }
    }

    private void returnAllArtifacts(){
        System.out.println("Artifacts:");
        while(artifactIterator.hasNext()){
            System.out.println(artifactIterator.next());
        }
    }

    private void createCrowdfund(){
        returnAllCrowdfunds();

    }

    private void buyArtifact(){
        returnAllArtifacts();
    }

    private void joinCrowdfund(){
        returnAllCrowdfunds();
    }
}
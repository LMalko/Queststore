class StudentController{

    private UserView view = new UserView();
    private ItemCollection<Artifact> artifactsCollection = Artifact.getArtifacts();
    private ItemCollection<Crowdfund> crowdfundsCollection = Crowdfund.getCrowdfunds();

    CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();
    CollectionIterator<Crowdfund> crowdfundIterator = crowdfundsCollection.getIterator();

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
            this.buyArtifact();
        }else if (choice.equals("3")){
            this.createCrowdfund();
        }else if (choice.equals("4")){
            this.getAllCrowdfunds();
        }else if (choice.equals("5")){
            this.joinCrowdfund();
        }else{
            System.out.println("No such choice");
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

    private void buyArtifact(){
        while(artifactIterator.hasNext()){
            System.out.println(artifactIterator.next());
        }

    }

    private void joinCrowdfund(){
        while(crowdfundIterator.hasNext()){
            System.out.println(crowdfundIterator.next());
        }
    }
}

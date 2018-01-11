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
            view.clearScreen();
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
    }

    private void returnAllCrowdfunds(){
        this.artifactIterator = artifactsCollection.getIterator();
        System.out.println("Crowdfunds:");
        while(crowdfundIterator.hasNext()){
            System.out.println(crowdfundIterator.next());
        }
        this.crowdfundIterator = crowdfundsCollection.getIterator();
    }

    private void returnAllArtifacts(){
        this.artifactIterator = artifactsCollection.getIterator();
        System.out.println("Artifacts:");
        while(artifactIterator.hasNext()){
            System.out.println(artifactIterator.next());
        }
        this.artifactIterator = artifactsCollection.getIterator();
    }

    private void createCrowdfund(){
        returnAllArtifacts();

        int artifactID;
        String contributorEmail;
        boolean ifExists = false;

        while(true){
        try{
            artifactID = Integer.parseInt(view.getUserInput("Enter artifact ID: "));
            break;
        }catch(NumberFormatException e){
            view.clearScreen();
            System.out.println("Wrong format.\n\n");
            returnAllArtifacts();
            }
        }

        while(artifactIterator.hasNext()){
            Artifact nextArtifact = artifactIterator.next();
            if(nextArtifact.getArtifactId() == artifactID){
                Artifact artifactToCrowdfund = nextArtifact;
                ifExists = true;
                contributorEmail = student.getLogin();
                Crowdfund crowdfund = new Crowdfund(artifactToCrowdfund.getArtifactName(),
                                                    artifactToCrowdfund.getArtifactPrice(),
                                                    artifactToCrowdfund.getArtifactPrice(), 
                                                    contributorEmail);
                this.crowdfundsDao.addCrowdfund(crowdfund);
                this.crowdfundsDao.exportCrowdfund();
                break;
                }
            }

        if(!ifExists){
            view.clearScreen();
            System.out.println("No such ID\n\n");
            this.artifactIterator = artifactsCollection.getIterator();
            createCrowdfund();
        }

    }

    private void buyArtifact(){
        returnAllArtifacts();
    }

    private void joinCrowdfund(){
        returnAllCrowdfunds();
    }

}
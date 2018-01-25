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
        artifactsDao.importArtifacts();
        crowdfundsDao.importCrowdfunds();
        
        while(isRuntime){
            view.displayUserMenu("txt/studentMenu.txt");
            handleStudentPanelOptions();
            String choice = view.getUserInput("Press anything to continue");

        }
    }

    private void handleStudentPanelOptions(){
        refreshArtifactsAndCrowdfundsDB();
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
        }else if (choice.equals("6")){
            System.out.println("Not available");
        }else{
            System.out.println("No such choice");
        }
    }

    private void refreshArtifactsAndCrowdfundsDB(){
        artifactsDao = new ArtifactsDao();
        artifactsDao.importArtifacts();
        artifactsCollection = artifactsDao.getArtifacts();
        crowdfundsDao = new CrowdfundDao();
        crowdfundsDao.importCrowdfunds();
        crowdfundsCollection = crowdfundsDao.getCrowdfunds();


    }

    private void returnAllCrowdfunds(){
        this.crowdfundIterator = crowdfundsCollection.getIterator();
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
                int founderID = student.getId();

                


                Crowdfund crowdfund = new Crowdfund(artifactToCrowdfund.getArtifactName(),
                                                    artifactToCrowdfund.getArtifactPrice(),
                                                    artifactToCrowdfund.getArtifactPrice(), 
                                                    founderID );
                                                    
                
                crowdfundsDao.addCrowdfundToDatabase(crowdfund);
                break;
                }
            }

        if(!ifExists){
            view.clearScreen();
            System.out.println("No such ID\n\n");
            createCrowdfund();
        }

    }

    private void buyArtifact(){
        returnAllArtifacts();
        System.out.println("\n\n\nStore is currently closed due to renovation - ZMIENIAMY SIĘ DLA CIEBIE !\n\n");
    }

    private void joinCrowdfund(){
        returnAllCrowdfunds();

        int crowdfundID;
        int contribution;
        boolean ifExists = false;

        while(true){
        try{
            crowdfundID = Integer.parseInt(view.getUserInput("Enter crowdfund ID: "));
            break;
        }catch(NumberFormatException e){
            view.clearScreen();
            System.out.println("Wrong format.\n\n");
            returnAllCrowdfunds();
            }
        }

        while(crowdfundIterator.hasNext()){
            Crowdfund nextCrowdfund = crowdfundIterator.next();
            if(nextCrowdfund.getCrowdfundId() == crowdfundID){
                Crowdfund crowdfundToContribute = nextCrowdfund;
                ifExists = true;

                while(true){
                try{
                    contribution = Integer.parseInt(view.getUserInput("How much you want to contribute? "));
                    if(contribution > student.getStudentWallet()){
                        System.out.println("You are to poor to contribute that much, amigo \n\n\n");
                        continue;
                    }
                    student.reduceWallet(contribution);
                    crowdfundToContribute.reduceCurrentPrice(contribution);
                    
                    break;

                }catch(NumberFormatException e){
                    view.clearScreen();
                    System.out.println("Wrong format.\n\n");
                    returnAllCrowdfunds();
                    }
                }

                break;
                }
            }

        if(!ifExists){
            view.clearScreen();
            System.out.println("No such ID\n\n");
            joinCrowdfund();
        }

    }

}
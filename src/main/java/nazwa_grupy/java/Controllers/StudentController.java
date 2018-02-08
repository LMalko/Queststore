package nazwa_grupy.java.Controllers;

import nazwa_grupy.java.DAOs.ArtifactsDao;
import nazwa_grupy.java.DAOs.CrowdfundDao;
import nazwa_grupy.java.DAOs.QuestDao;
import nazwa_grupy.java.DAOs.UsersDao;
import nazwa_grupy.java.Iterator_DBProcessor.CollectionIterator;
import nazwa_grupy.java.Models.*;
import nazwa_grupy.java.Views.UserView;

public class StudentController{

    private UserView view = new UserView();
    private UsersDao userDao = new UsersDao();
    private ArtifactsDao artifactsDao = new ArtifactsDao();
    private CrowdfundDao crowdfundsDao = new CrowdfundDao();
    private QuestDao questDao = new QuestDao();
    private ItemCollection<Artifact> artifactsCollection = artifactsDao.getArtifacts();
    private ItemCollection<Crowdfund> crowdfundsCollection = crowdfundsDao.getCrowdfunds();
    private ItemCollection<Quest> questsCollection = questDao.getQuests();

    private CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();
    private CollectionIterator<Crowdfund> crowdfundIterator = crowdfundsCollection.getIterator();
    private CollectionIterator<Quest> questIterator = questsCollection.getIterator();

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
        refreshDB();
        String choice = view.getUserInput("Choose your option: ");
        switch(choice){
            case "0":   view.clearScreen();
                        System.exit(0);
                        break;
            case "1":   view.clearScreen();
                        System.out.println("\n\nWallet is:");
                        System.out.println(this.student.getStudentWallet() + "\n\nVery nice!\n\n");
                        break;
            case "2":   buyArtifact();
                        break;
            case "3":   createCrowdfund();
                        break;
            case "4":   returnAllCrowdfunds();
                        break;
            case "5":   joinCrowdfund();
                        break;
            case "6":   showStudentArtifacts();
                        break;
            case "7":   view.clearScreen();
                        System.out.println("\n\nExperience status is:");
                        System.out.println(this.student.getStudentExperienceLevel() +
                                           "\n\nVery nice!\n\n");
                        break;
            case "8":   view.clearScreen();
                        enrollOnQuest();
                        break;
            case "9":   view.clearScreen();
                        questDao.displayStudentQuest(this.student.getId());
                        break;
            default:    System.out.println("No such choice");
                        break;
        }
    }

    private void enrollOnQuest(){
        returnAllQuests();
        while(true) {
            String choice = view.getUserInput("Choose your option: ");

            while(questIterator.hasNext()){
                Quest nextQuest = questIterator.next();

                if(choice.equals(String.valueOf(nextQuest.getQuestId()))) {
                    Quest correctQuest = nextQuest;
                    questDao.addQuestToStudent(correctQuest.getQuestId() , this.student.getId());
                }
            } break;
        }
    }

    private void refreshDB(){
        artifactsDao = new ArtifactsDao();
        artifactsDao.importArtifacts();
        artifactsCollection = artifactsDao.getArtifacts();
        crowdfundsDao = new CrowdfundDao();
        crowdfundsDao.importCrowdfunds();
        crowdfundsCollection = crowdfundsDao.getCrowdfunds();
        questDao = new QuestDao();
        questDao.importQuests();
        questsCollection = questDao.getQuests();

    }

    private void showStudentArtifacts(){
        artifactsDao.returnSpecifiedStudentArtifacts(this.student.getId());
    }

    private void returnAllCrowdfunds(){
        this.crowdfundIterator = crowdfundsCollection.getIterator();
        System.out.println("Crowdfunds:");
        while(crowdfundIterator.hasNext()){
            System.out.println(crowdfundIterator.next());
        }
        this.crowdfundIterator = crowdfundsCollection.getIterator();
    }

    private void returnAllQuests(){
        this.questIterator = questsCollection.getIterator();
        System.out.println("Quests:");
        while(questIterator.hasNext()){
            System.out.println(questIterator.next());
        }
        this.questIterator = questsCollection.getIterator();
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
                                                    0,
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
        int walletBalance = this.student.getStudentWallet();
        returnAllArtifacts();
        boolean doesArtifactExist = false;

        while(true) {
            String choice = view.getUserInput("Choose your option: ");

            while(artifactIterator.hasNext()){
                Artifact nextArtifact = artifactIterator.next();
                int artifactPrice = nextArtifact.getArtifactPrice();

                if(choice.equals(String.valueOf(nextArtifact.getArtifactId()))) {
                    doesArtifactExist = true;
                    System.out.println("\n\nThis artifact bought! Good Job!\n\n");
                    if(walletBalance >= artifactPrice) {
                        Artifact correctArtifact = nextArtifact;
                        artifactsDao.addArtifactToStudent(correctArtifact, this.student.getId());
                        this.student.reduceWallet(correctArtifact.getArtifactPrice());
                        userDao.updateStudentWalletInDatabase(this.student);
                    } else {
                        view.displayText("Not enough funds! Not very nice...");
                    }
                }
            } break;
        }if(!doesArtifactExist){
            System.out.println("\n\nNo such artifact ¯\\_(ツ)_/¯ \n\n");
        }
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
                    userDao.updateStudentWalletInDatabase(this.student);
                    crowdfundToContribute.reduceCurrentPrice(contribution);
                    crowdfundsDao.updateCrowdfundAccount(crowdfundToContribute.getCrowdfundId(), contribution);

                    
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
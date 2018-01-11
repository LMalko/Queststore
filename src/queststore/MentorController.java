import java.util.ArrayList;

class MentorController{

    private UserView view = new UserView();
    private UsersDao dao = new UsersDao();
    private QuestDao questDao = new QuestDao();
    private ArtifactsDao artifactsDao = new ArtifactsDao();
    private CategoryDao categoryDao = new CategoryDao();
    private GroupDao groupDao = new GroupDao();

    public void startMentorPanel(){
        boolean isRunning = true;
        groupDao.importGroups();
        questDao.importQuests();

        while(isRunning){
            view.displayUserMenu("txt/mentorMenu.txt");
            handleMentorPanelOptions();
        }
    }

    public void handleMentorPanelOptions(){
        String choice = view.getUserInput("Choose your option: ");
        if (choice.equals("0")){
            System.exit(0);
        }
        else if (choice.equals("1")){
            createStudent();
        }
        else if (choice.equals("2")){
            studentAssignToGroup();
        }
        else if (choice.equals("3")){
            addNewQuest();
        }
        else if (choice.equals("4")){
            addQuestCategory();
        }
        else if (choice.equals("5")){
            editQuest();
        }
        else if (choice.equals("6")){
            addArtifact();
        }
        else if (choice.equals("7")){
            //have to implement artifact picker
            //editArtifact(artifact);
        }
        else if (choice.equals("8")){
            addArtifactCategory();
        }
        else if (choice.equals("9")){
            markStudentQuest();
        }
        else if (choice.equals("10")){
            markStudentArtifact();
        }
        else if (choice.equals("11")){
            displayStudentWallet();
        }
    }

    public void createStudent(){
        String studentName = view.getUserInput("Enter student name: ");
        String studentSurname = view.getUserInput("Enter student surname: ");
        String studentPassword = view.getUserInput("Enter student password: ");
        Student newStudent = new Student(studentName, studentSurname, studentPassword);
        dao.addUserToUsersCollection(newStudent);
        dao.saveUsersToFile();
    }

    public void studentAssignToGroup(){

    }

    public void addNewQuest(){
        String questName = view.getUserInput("Enter quest name: ");
        int questAward = Integer.parseInt(view.getUserInput("Enter award for completing quest: "));
        String category = view.getUserInput("Enter category of quest: ");
        Quest newQuest = new Quest(questName, questAward, "not done", category);
        questDao.addQuest(newQuest);
        questDao.exportQuests();
    
    }

    public void addQuestCategory(){
        String categoryName = view.getUserInput("Enter new category name: ");
        Category category = new Category(categoryName);
        categoryDao.addCategory(category);
        categoryDao.exportCategory();
        
    }   

    public void editQuest(){
        getAllQuests();
    }

    public void getAllQuests(){
        ItemCollection<Quest> questCollection = questDao.getQuests();
        CollectionIterator<Quest> questIterator = questCollection.getIterator();

        while(questIterator.hasNext()){
            Quest currentQuest = questIterator.next();

            String questID = Integer.toString(currentQuest.getQuestId());
            String name = currentQuest.getQuestName();
            String award = Integer.toString(currentQuest.getQuestAward());
            String status = currentQuest.getQuestStatus();
            String category = currentQuest.getQuestCategoryName();
            view.displayText("ID: "+questID +" "+name+" for:"+award+
                " from category:"+category+" /currently:"+status);
        }
        questIterator = questCollection.getIterator();

    }

    public void addArtifact(){
        int artifactId = Integer.parseInt(view.getUserInput("Enter artifact id: "));
        String artifactName = view.getUserInput("Enter artifact name: ");
        int artifactPrice = Integer.parseInt(view.getUserInput("Enter artifact price: "));
        String artifactCategory = view.getUserInput("Enter artifact category: ");
        Artifact newArtifact = new Artifact(artifactId, artifactName, artifactPrice, artifactCategory);
        artifactsDao.addArtifact(newArtifact);
        artifactsDao.exportArtifacts();
    }

    public void editArtifact(Artifact artifact){

    }


    public void addArtifactCategory(){

    }

    public void markStudentQuest(){

    }

    public void markStudentArtifact(){

    }

    public void displayStudentWallet(){

    }
}

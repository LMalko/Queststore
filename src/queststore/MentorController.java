import java.util.ArrayList;

class MentorController{

    private static ItemCollection<Artifact> artifactsCollection = new ItemCollection<>("Artifacts");
    private static ItemCollection<Category> categoryCollection = new ItemCollection<>("Categories");


    private UserView view = new UserView();
    private UsersDao dao = new UsersDao();
    private QuestDao questDao = new QuestDao();
    //private Quest quest = new Quest();
    private ArtifactsDao artifactsDao = new ArtifactsDao();
    private CategoryDao categoryDao = new CategoryDao();
    private GroupDao groupDao = new GroupDao();

    public void startMentorPanel(){
        boolean isRunning = true;

        groupDao.importGroups();
        questDao.importQuests();
        artifactsDao.importArtifacts();
        categoryDao.importCategories();


        while(isRunning){
            view.displayUserMenu("txt/mentorMenu.txt");
            handleMentorPanelOptions();
        }
    }

    public void handleMentorPanelOptions(){
        try{
            String choice = view.getUserInput("Choose your option: ");
            if (choice.equals("0")){
                dao.disconnectDatabase();
                System.exit(0);

            }
            else if (choice.equals("1")){
                createStudent();
            }
            else if (choice.equals("2")){
                assignStudentToGroup();
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
                editArtifact();
                //have to implement artifact picker
                //editArtifact(artifact);
            }
            else if (choice.equals("8")){
                addNewCategory();
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
            else{
                view.displayText("No such option exists!");
               Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public void createStudent(){
        String studentName = view.getUserInput("Enter student name: ");
        String studentSurname = view.getUserInput("Enter student surname: ");
        String studentPassword = view.getUserInput("Enter student password: ");
        Student newStudent = new Student(studentName, studentSurname, studentPassword);
        dao.addUserToDatabase(newStudent);
        dao.addStudentWalletToDatabase(newStudent);
        // dodawanie portfela!!!
    }

    public void assignStudentToGroup(){
        try{
            getAllStudents();
            int studentId = Integer.parseInt(view.getUserInput("Choose student by ID"));
            Student student = dao.getStudentById(studentId);
            view.clearScreen();  // clear before displaying group names
            view.displayText("Choose group from those listed below:");
            getAllGroupsNames();
            String groupName = view.getUserInput("Choose group name:");
            Group newGroup = groupDao.getGroupByName(groupName);
            student.setStudentGroup(newGroup);
            dao.updateUserGroupInDatabase(student);
        } catch (NullPointerException e){
            promptMessageAndStopThread("No such student or group exists!");
        } catch (NumberFormatException e){
            promptMessageAndStopThread("No such student or group exists!");
        }

    }

    private void getAllStudents(){
        view.clearScreen();
        ArrayList<User> studentsCollection = dao.getAllUsersByStatus("student");
        for(User student : studentsCollection){
            int studentId = student.getId();
            String studentName = student.getName();
            String studentSurname = student.getSurname();
            String studentGroup = student.getUserGroupName();
            view.displayText("ID: "+studentId +" "+studentName+" "+studentSurname+" "+studentGroup);
        }
    }

    public void getAllGroupsNames(){
        ItemCollection<Group> allGroups = groupDao.getGroups();
        CollectionIterator<Group> groupIterator = allGroups.getIterator();
        while(groupIterator.hasNext()){
            Group group = groupIterator.next();
            view.displayText(group.getGroupName());
        }
    }


    public void addNewQuest(){
        String questName = view.getUserInput("Enter quest name: ");
        int questReward = Integer.parseInt(view.getUserInput("Enter reward for completing quest: "));
        String category = view.getUserInput("Enter category of quest: ");
        Quest newQuest = new Quest(questName, questReward, category);
        questDao.addQuest(newQuest);
        questDao.addQuestToDatabase(newQuest);

    }

    public void addQuestCategory(){
        String categoryName = view.getUserInput("Enter new category name: ");
        Category category = new Category(categoryName);
        categoryDao.addCategory(category);
        categoryDao.addCategoryToDatabase(category);

    }

    public void editQuest(){
        try{
            view.clearScreen();
            getAllQuests();
            int questId = Integer.parseInt(view.getUserInput("Enter ID of quest you want to edit: "));
            if (checkIfGivenIdQuestExists(questId)){
                Quest quest = questDao.getQuestById(questId);
                quest.setQuestName(view.getUserInput("Enter new quest name: "));
                quest.setQuestReward(Integer.parseInt(view.getUserInput("Enter new quest award: ")));
                quest.setQuestCategory(view.getUserInput("Enter new category name: "));
                questDao.editQuestOnDatabase(quest);
                System.out.println("Operation was succesfull");
            }
            else {
                view.displayText("No quest with given ID exists!");
                Thread.sleep(1000);
            }
        }
        catch (NullPointerException e){
            promptMessageAndStopThread("Quest does not exists!");
        }
        catch (NumberFormatException e){
            promptMessageAndStopThread("Only numbers!!!");
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private boolean checkIfGivenIdQuestExists(int id){
        ItemCollection<Quest> questsCollection = questDao.getQuests();
        CollectionIterator<Quest> questIterator = questsCollection.getIterator();

        while (questIterator.hasNext()){
            Quest quest = questIterator.next();
            if(quest.getQuestId() == id){
                return true;
            }
        }
        return false;
    }

    public void getAllQuests(){
        ItemCollection<Quest> questCollection = questDao.getQuests();
        CollectionIterator<Quest> questIterator = questCollection.getIterator();

        while(questIterator.hasNext()){
            Quest currentQuest = questIterator.next();

            String questID = Integer.toString(currentQuest.getQuestId());
            String name = currentQuest.getQuestName();
            String award = Integer.toString(currentQuest.getQuestReward());
            String category = currentQuest.getQuestCategoryName();
            view.displayText("ID: "+questID +" "+name+" for:"+award+
                " from category:"+category);
        }


    }

    public void addArtifact(){
        //wywala błąd jak dodamy artefakt o tej samej nazwie.
        try{
            String artifactName = view.getUserInput("Enter artifact name: ");
            int artifactPrice = Integer.parseInt(view.getUserInput("Enter artifact price: "));
            String artifactCategoryName = addArtifactCategory();
            Artifact newArtifact = new Artifact(artifactName, artifactPrice, artifactCategoryName);
            artifactsDao.addArtifactToDatabase(newArtifact);
        }
        catch (NumberFormatException e){
            promptMessageAndStopThread("price should be number!");
        }
        catch (NullPointerException e){
            promptMessageAndStopThread("Wrong category name!!!");
        }
    }

    public void editArtifact(){
        try{
            getAllArtifacts();
            int id = Integer.parseInt(view.getUserInput("Enter artifact id: "));
            Artifact artifactToEdit = getArtifactById(id);
            if (artifactToEdit != null){
                System.out.println(artifactToEdit);
                artifactToEdit.setName(view.getUserInput("Enter new artifact name: "));
                artifactToEdit.setPrice(Integer.parseInt(view.getUserInput("Enter new artifact price: ")));
                artifactToEdit.setCategory(addArtifactCategory());
                artifactsDao.updateArtifactDataInDatabase(artifactToEdit);
            }
            else{
                promptMessageAndStopThread("Can not find artifact!");
            }
        } catch (NumberFormatException e){
            promptMessageAndStopThread("Only number!!!");
        }
        catch (NullPointerException e){
            promptMessageAndStopThread("Wrong category name!!!");
        }
    }

    public Artifact getArtifactById(int id){
        ItemCollection<Artifact> artifactsCollection = artifactsDao.getArtifacts();
        CollectionIterator<Artifact> artifactsIterator = artifactsCollection.getIterator();

        while(artifactsIterator.hasNext()) {
            Artifact artifact = artifactsIterator.next();
            if(artifact.getArtifactId() == id) {
                return artifact;
            }
        }
        return null;
    }

    public void getAllArtifacts(){

        ItemCollection<Artifact> artifactsCollection = artifactsDao.getArtifacts();
        CollectionIterator<Artifact> artifactsIterator = artifactsCollection.getIterator();

        while (artifactsIterator.hasNext()){
            Artifact artifact = artifactsIterator.next();
            int id = artifact.getArtifactId();
            String name = artifact.getArtifactName();
            int price = artifact.getArtifactPrice();
            String category = artifact.getArtifactCategory();
            view.displayText(id + " " + name + " " +price + " " +category);
        }
    }


    public String addArtifactCategory(){
        getAllCategories();
        String correctCategoryName = null;
        view.displayText("Choose category from list:");
        String categoryName = view.getUserInput("Choose category by name: ");
        Category category = categoryDao.getCategoryByName(categoryName);
        if (category.getCategoryName().equals(categoryName)){
            correctCategoryName = categoryName;
        }
        else{
            view.displayText("Wrong");
        }
        return correctCategoryName;
    }

    public void addNewCategory(){
        String categoryName = view.getUserInput("Enter new category name: ");
        Category category = new Category(categoryName);
        categoryDao.addCategoryToDatabase(category);
        categoryDao.addCategory(category);
    }

    private void getAllCategories(){
        ItemCollection<Category> categoryCollection = categoryDao.getCategories();
        CollectionIterator<Category> categoryIterator = categoryCollection.getIterator();
        while (categoryIterator.hasNext()){
            Category category = categoryIterator.next();
            String name = category.getCategoryName();
            view.displayText(name);
        }

    }

    public void markStudentQuest(){
        //zaznaczyc ze quest studenta zrobiony i dodac kasen na jego konto
    }

    public void markStudentArtifact(){

    }

    public void displayStudentWallet(){
        try{
            getAllStudents();
            int studentId = Integer.parseInt(view.getUserInput("Choose student by ID"));
            Student student = dao.getStudentById(studentId);
            String wallet = String.valueOf(student.getStudentWallet());
            view.displayText(wallet);
        }
        catch (NullPointerException e){
            promptMessageAndStopThread("No student with given ID exist");
        }
        catch (NumberFormatException e){
            promptMessageAndStopThread("Only numbers in ID");
        }

    }

    private void promptMessageAndStopThread(String message){
        try{
            view.displayText(message);
            Thread.sleep(1000);
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
}

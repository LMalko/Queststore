package nazwa_grupy.java.Controllers;

import nazwa_grupy.java.DAOs.*;
import nazwa_grupy.java.Iterator_DBProcessor.CollectionIterator;
import nazwa_grupy.java.Models.*;
import nazwa_grupy.java.Views.UserView;

import java.util.ArrayList;

public class MentorController {

    private UserView view = new UserView();
    private UsersDao dao = new UsersDao();
    private QuestDao questDao = new QuestDao();
    private ArtifactsDao artifactsDao = new ArtifactsDao();
    private CategoryDao categoryDao = new CategoryDao();
    private GroupDao groupDao = new GroupDao();

    public void startMentorPanel() {
        boolean isRunning = true;

        groupDao.importGroups();
        questDao.importQuests();
        artifactsDao.importArtifacts();
        categoryDao.importCategories();

        while(isRunning) {
            view.displayUserMenu("txt/mentorMenu.txt");
            handleMentorPanelOptions();
            view.getUserInput("ENTER to continue");
        }
    }

    private void handleMentorPanelOptions() {
        try {
            String choice = view.getUserInput("Choose your option: ");
            switch (choice) {
                case "0":
                    dao.disconnectDatabase();
                    System.exit(0);
                case "1":
                    createStudent();
                    break;
                case "2":
                    assignStudentToGroup();
                    break;
                case "3":
                    addNewQuest();
                    break;
                case "4":
                    addQuestCategory();
                    break;
                case "5":
                    editQuest();
                    break;
                case "6":
                    addArtifact();
                    break;
                case "7":
                    editArtifact();
                    break;
                case "8":
                    addNewCategory();
                    break;
                case "9":
                    markStudentQuest();
                    break;
                case "10":
                    markStudentArtifact();
                    break;
                case "11":
                    displayStudentWallet();
                    break;
                default:
                    view.displayText("No such option exists!");
                    Thread.sleep(1000);
                    break;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void createStudent(){
        String studentName = view.getUserInput("Enter student name: ");
        String studentSurname = view.getUserInput("Enter student surname: ");
        String studentPassword = view.getUserInput("Enter student password: ");
        Student newStudent = new Student(studentName, studentSurname, studentPassword);
        dao.addUserToDatabase(newStudent);
        dao.addStudentWalletToDatabase(newStudent);
    }

    private void assignStudentToGroup(){
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
        } catch (NullPointerException | NumberFormatException e){
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

    private void getAllGroupsNames(){
        ItemCollection<Group> allGroups = groupDao.getGroups();
        CollectionIterator<Group> groupIterator = allGroups.getIterator();
        while(groupIterator.hasNext()){
            Group group = groupIterator.next();
            view.displayText(group.getGroupName());
        }
    }

    private void addNewQuest(){
        try {
            String questName = view.getUserInput("Enter quest name: ");
            int questReward = Integer.parseInt(view.getUserInput("Enter reward for completing quest: "));
            String category = setCategoryName();

            if (category != null) {
                Quest newQuest = new Quest(questName, questReward, category);
                questDao.addQuest(newQuest);
                questDao.addQuestToDatabase(newQuest);
            }
        } catch (NumberFormatException e) {
            view.displayText("Quest reward should be an integer number.");
        }
    }

    private void addQuestCategory(){
        String categoryName = view.getUserInput("Enter new category name: ");
        Category category = new Category(categoryName);
        categoryDao.addCategory(category);
        categoryDao.addCategoryToDatabase(category);

    }

    private void editQuest(){
        try{
            view.clearScreen();
            getAllQuests();
            int questId = Integer.parseInt(view.getUserInput("Enter ID of quest you want to edit: "));
            if (checkIfGivenIdQuestExists(questId)){
                Quest quest = questDao.getQuestById(questId);
                quest.setQuestName(view.getUserInput("Enter new quest name: "));
                quest.setQuestReward(Integer.parseInt(view.getUserInput("Enter new quest award: ")));
                String questCategory = setCategoryName();
                if (questCategory != null) {
                    quest.setQuestCategory(questCategory);
                    questDao.editQuestOnDatabase(quest);
                    view.displayText("Operation was successful!");
                }
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

    private void getAllQuests(){
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

    private void addArtifact() {
        try {
            String artifactName = view.getUserInput("Enter artifact name: ");
            int artifactPrice = Integer.parseInt(view.getUserInput("Enter artifact price: "));
            String artifactCategoryName = setCategoryName();
            if (artifactCategoryName != null) {
                Artifact newArtifact = new Artifact(artifactName, artifactPrice, artifactCategoryName);
                artifactsDao.addArtifactToDatabase(newArtifact);
            }
        }
        catch (NumberFormatException e) {
            promptMessageAndStopThread("Price should be a number!");
        }
        catch (NullPointerException e) {
            promptMessageAndStopThread("Wrong category name!");
        }
    }

    private void editArtifact() {
        try {
            getAllArtifacts();
            int id = Integer.parseInt(view.getUserInput("Enter artifact id: "));
            Artifact artifactToEdit = getArtifactById(id);
            if (artifactToEdit != null){
                view.displayObject(artifactToEdit);
                artifactToEdit.setName(view.getUserInput("Enter new artifact name: "));
                artifactToEdit.setPrice(Integer.parseInt(view.getUserInput("Enter new artifact price: ")));
                String artifactCategory = setCategoryName();
                if (artifactCategory != null) {
                    artifactToEdit.setCategory(artifactCategory);
                    artifactsDao.updateArtifactDataInDatabase(artifactToEdit);
                }
            }
            else{
                promptMessageAndStopThread("Can not find artifact!");
            }
        } catch (NumberFormatException e){
            promptMessageAndStopThread("Id and price should be numbers!");
        }
        catch (NullPointerException e){
            promptMessageAndStopThread("Wrong category name!");
        }
    }

    private Artifact getArtifactById(int id){
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

    private void getAllArtifacts(){

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

    private String setCategoryName(){
        String correctCategoryName = null;
        view.displayText("Choose category from list:");
        getAllCategories();

        try {
            String categoryName = view.getUserInput("Choose category by name: ");
            Category category = categoryDao.getCategoryByName(categoryName);
            if (category.getCategoryName().equals(categoryName)){
                correctCategoryName = categoryName;
            }
            else{
                view.displayText("Wrong");
            }

        } catch (NullPointerException e) {
            view.displayText("Wrong category name.");
        }
        return correctCategoryName;
    }

    private void addNewCategory(){
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

    private void markStudentQuest(){

        try{
            getAllStudents();
            int studentID = Integer.parseInt(view.getUserInput("Choose student by ID"));
            if (dao.getStudentById(studentID) != null){

                questDao.chooseStudentQuest(studentID);
                int questID = Integer.parseInt(view.getUserInput("Choose quest by ID"));
                questDao.setQuestStatusAsDone(questID);

                Quest newQuest = questDao.getQuestById(questID);
                int questReward = newQuest.getQuestReward();
                dao.addCurrencyToWallet(questReward, studentID);

            }else{
                promptMessageAndStopThread("Wrong student ID!!!");
            }
        }catch(NumberFormatException e){
            promptMessageAndStopThread("Please choose quest ID!!!");
        }
    }

    private void markStudentArtifact(){
        try {
            getAllStudents();
            int studentId = Integer.parseInt(view.getUserInput("Choose student by ID"));
            artifactsDao.returnSpecifiedStudentUnusedArtifacts(studentId);
            int artifactId = Integer.parseInt(view.getUserInput("Choose artifact to mark by ID"));
            artifactsDao.markGivenArtifact(artifactId);

        } catch (NullPointerException e){
            promptMessageAndStopThread("No student with given ID exist");
        }
        catch (NumberFormatException e){
            promptMessageAndStopThread("Only numbers in ID");
        }
    }

    private void displayStudentWallet(){
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

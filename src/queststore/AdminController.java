import java.util.ArrayList;

class AdminController{

    private UsersDao dao = new UsersDao();
    private UserView view = new UserView();
    private GroupDao groupDao = new GroupDao();
    private ExperienceLevelsDao levelsDao = new ExperienceLevelsDao();

    public void startAdminPanel(){
        boolean isRuntime = true;
        groupDao.importGroups();
        levelsDao.importExperienceLevels();

        while(isRuntime){
            view.displayUserMenu("txt/adminMenu.txt");
            handleAdminPanelOptions();
        }
    }

    private void handleAdminPanelOptions(){
        try{
            String choice = view.getUserInput("Choose your option: ");
            if (choice.equals("0")){
                System.exit(0);
            }
            else if (choice.equals("1")){
                createNewMentor();
            }
            else if (choice.equals("2")){
                createNewGroup();
            }
            else if (choice.equals("3")){
                assignMentorToGroup();
            }
            else if (choice.equals("4")){
                editMentorData();
            }
            else if (choice.equals("5")){
                getSpecificMentorData();
            }
            else if (choice.equals("6")){
                createNewLevelOfExperience();
            }
            else{
                view.displayText("No such option exists!");
               Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void createNewMentor(){
        String mentorName = view.getUserInput("Enter mentor's name: ");
        String mentorSurname = view.getUserInput("Enter mentor's surname: ");
        String mentorPassword = view.getUserInput("Enter mentor's password: ");
        Mentor newMentor = new Mentor(mentorName, mentorSurname, mentorPassword);
        dao.addUserToUsersCollection(newMentor);
        dao.saveUsersToFile();
    }

    private void editMentorData(){
        try{
            getAllMentors();
            int mentorId = Integer.parseInt(view.getUserInput("Choose mentor by ID"));
            if(checkIfGivenIdMentorExists(mentorId)){
                Mentor mentor = dao.getMentorById(mentorId);
                String newName = view.getUserInput("Enter mentor's new name: ");
                String newSurname = view.getUserInput("Enter mentor's new surname: ");
                String newPassword = view.getUserInput("Enter mentor's new password: ");
                mentor.setMentorName(newName);
                mentor.setMentorSurname(newSurname);
                mentor.setMentorPassword(newPassword);
                mentor.setMentorLogin(newName, newSurname);
                dao.saveUsersToFile();
            }
            else{
                view.displayText("No mentor with given ID exists!");
                Thread.sleep(1000);
            }
        } catch (NumberFormatException e){
            try{
                view.displayText("No mentor with given ID exists!");
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void getSpecificMentorData(){
        try{
            getAllMentors();
            int mentorId = Integer.parseInt(view.getUserInput("Choose mentor by ID"));
            if(checkIfGivenIdMentorExists(mentorId)){
                Mentor mentor = dao.getMentorById(mentorId);
                view.displayText(mentor.toString());
                view.getUserInput("Press any key to continue");
            } else{
                view.displayText("No mentor with given ID exists!");
                Thread.sleep(1000);
            }
        } catch (NumberFormatException e){
            try{
                view.displayText("No mentor with given ID exists!");
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private boolean checkIfGivenIdMentorExists(int id){
        ArrayList<User> mentorsCollection = dao.getAllUsersByStatus("mentor");
        for(User mentor : mentorsCollection){
            int mentorId = mentor.getId();
            if (mentorId == id){
                return true;
            }
        }
        return false;
    }

    private void createNewGroup(){
        String groupName = view.getUserInput("Enter new group name: ");
        Group group = new Group(groupName);
        groupDao.addGroup(group);
        groupDao.exportGroups();
    }

    private void assignMentorToGroup(){
        try{
            getAllMentors();
            int mentorId = Integer.parseInt(view.getUserInput("Choose mentor by ID"));
            Mentor mentor = dao.getMentorById(mentorId);
            view.clearScreen();  // clear before displaying group names
            view.displayText("Choose group from those listed below:");
            getAllGroupsNames();
            String groupName = view.getUserInput("Choose group name:");
            Group newGroup = groupDao.getGroupByName(groupName);
            mentor.setMentorGroup(newGroup);
            dao.saveUsersToFile();
        } catch (NullPointerException e){
            try{
                view.displayText("No such mentor or group exists!");
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        } catch (NumberFormatException e){
            try{
                view.displayText("No such mentor or group exists!");
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
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

    public void createNewLevelOfExperience(){
        try{
            view.clearScreen();
            String levelName = view.getUserInput("Set level name: ");
            int level = Integer.parseInt(view.getUserInput("Set xp needed to reach level: "));
            ExperienceLevel newLevel = new ExperienceLevel(level, levelName);
            newLevel.addExperienceLevel(newLevel);
            ItemCollection<ExperienceLevel> experienceLevels = newLevel.getExperienceLevels();
            levelsDao.exportExperienceLevels(experienceLevels);
        } catch (NumberFormatException e){
            try{
                view.displayText("Experience needed should be a number!");
                Thread.sleep(1000);
            } catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
    }

    private void getAllMentors(){
        view.clearScreen();
        ArrayList<User> mentorsCollection = dao.getAllUsersByStatus("mentor");
        for(User mentor : mentorsCollection){
            int mentorId = mentor.getId();
            String mentorName = mentor.getName();
            String mentorSurname = mentor.getSurname();
            view.displayText("ID: "+mentorId +" "+mentorName+" "+mentorSurname);
        }
    }
}

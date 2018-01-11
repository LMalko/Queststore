import java.util.ArrayList;

class AdminController{

    UsersDao dao = new UsersDao();
    UserView view = new UserView();

    public void startAdminPanel(){
        boolean isRuntime = true;

        while(isRuntime){
            view.displayUserMenu("txt/adminMenu.txt");
            handleAdminPanelOptions();
        }
    }

    private void handleAdminPanelOptions(){
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

        }
        else if (choice.equals("5")){

        }
        else if (choice.equals("6")){

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

    private void createNewGroup(){
        String groupName = view.getUserInput("Enter new group name: ");
        Group group = new Group(groupName);
        group.addGroupToGroupCollection(group);
    }

    private void assignMentorToGroup(){
        getAllMentors();
        int mentorId = Integer.parseInt(view.getUserInput("Choose mentor by ID"));
        Mentor mentor = dao.getMentorById(mentorId);
        view.displayText("Choose group from listed below");
        getAllGroupsNames();
        String groupName = view.getUserInput("Choose group name");
        Group group = Group.getGroupByName(groupName);
        mentor.setMentorGroup(group);
    }

    private void getAllGroupsNames(){
        ArrayList<Group> allGroups = Group.getAllGroups();
        CollectionIterator<Group> iterator = new CollectionIterator(allGroups);
        while(iterator.hasNext()){
            String groupName = iterator.next().getGroupName();
            view.displayText(groupName);
        }
    }

    private void getAllMentors(){
        ArrayList<User> mentorsCollection = dao.getAllUsersByStatus("mentor");

        for(User mentor : mentorsCollection){
            int mentorId = mentor.getId();
            String mentorName = mentor.getName();
            String mentorSurname = mentor.getSurname();
            view.displayText("ID: "+mentorId +" "+mentorName+" "+mentorSurname);
        }
    }
}

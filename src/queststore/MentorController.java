package queststore;

class MentorController{

    private UserView view = new UserView();
    private UsersDao dao = new UsersDao();

    public void startMentorPanel(){
        view.displayUserMenu("txt/mentorMenu.txt");
    }

    public void createStudent(){
        String studentName = view.getUserInput("Enter student name: ");
        String studentSurname = view.getUserInput("Enter student surname: ");
        String studentPassword = view.getUserInput("Enter student password: ");
        Student newStudent = new Student(studentName, studentSurname, studentPassword);
        dao.addUserToUsersCollection(newStudent);
        dao.saveUsersToFile();
    }

    public void addQuest(){

    }

    public void getQuestByCategory(String category){

    }

    public void addArtifact(){

    }

    public void editArtifact(Artifact artifact){

    }

    public void editQuest(Quest quest){

    }

    public void getArtifactByCategory(String category){

    }

    public void editStudent(Student student){

    }

    public void getStudentWallets(){

    }
}

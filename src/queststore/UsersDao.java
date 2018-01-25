import java.util.ArrayList;

class UsersDao {

    private static ArrayList<User> usersCollection = new ArrayList<User>();
    private JDBConnection databaseConnection = new JDBConnection("jdbc:sqlite:db/questStore.db");

    public void importUsersData() {
        databaseConnection.connectToDatabase();
        ArrayList<ArrayList<String>> users = databaseConnection.getArrayListFromQuery("SELECT * FROM users");
        for(int i =0; i < users.size(); i++){
            ArrayList<String> personData = users.get(i);
            User person = createUserObject(personData);
            usersCollection.add(person);
            }
        }

    public ArrayList<User> getUsersCollection() {
        importUsersData();
        return usersCollection;
    }

    private User createUserObject(ArrayList<String> personData) {
        int id = Integer.parseInt(personData.get(0));
        String name = personData.get(1);
        String surname = personData.get(2);
        String login = personData.get(3);
        String password = personData.get(4);
        String status = personData.get(5);
        int groupId = 0;
        //int walletID = Integer.parseInt(personData.get(7)); BRAK W BAZIE DANYCH

        if (personData.get(6) != null){
            groupId = Integer.parseInt(personData.get(6));
        }
        if (personData.get(7) != null) {
            String experienceLevel = personData.get(7);
        } else {
            String experienceLevel = "";
        }

        String groupName = getUserGroupNameByGroupId(groupId);
        Group group = new Group(groupId, groupName);
        User person = null;
        if(status.equals("admin")){
            person = new Admin(name, surname, password);
        }
        else if(status.equals("mentor")){
            person = new Mentor(id, name, surname, password, group);
        }
        else if(status.equals("student")){
            person = new Student(name, surname, password);
        }
        return person;
    }

    public void addUserToDatabase(User user){
        databaseConnection.connectToDatabase();
        String name = user.getName();
        String surname = user.getSurname();
        String login = user.getLogin();
        String password = user.getPassword();
        String status = user.getStatus();
        int groupId = user.getUserGroupId();
        String experienceLevel = user.getUserExperienceLevel();

        String query = "INSERT INTO users (name, surname, login, password," +
                        "status, group_id, experience) VALUES ( " + "'" +
                        name + "', '" +
                        surname + "', '" +
                        login + "', '" +
                        password + "', '" +
                        status + "', " +
                        String.valueOf(groupId) + ", '" +
                        experienceLevel +
                        "');";

        usersCollection.add(user);
        databaseConnection.executeUpdateAgainstDatabase(query);

    }

    public String getUserGroupNameByGroupId(int groupID){
        String query = "SELECT * FROM groups WHERE id = '" + groupID +"';";
        System.out.println(query);
        String groupName = databaseConnection.getStringDataFromQuery(query, "name");
        System.out.println(groupName);
        return groupName;
    }

    public void addUserToUsersCollection(User user){
        usersCollection.add(user);
    }

    public void updateUserGroupInDatabase(User user){
        String query = "UPDATE users SET group_id = " + "'" + user.getUserGroupId() + "' " +
                        "WHERE id = " + user.getId() + ";";
        System.out.println(query);
        databaseConnection.executeUpdateAgainstDatabase(query);
    }

    public ArrayList<User> getAllUsersByStatus(String userStatus){
        usersCollection.clear();
        importUsersData();
        ArrayList<User> usersWithGivenStatus = new ArrayList<User>();
        for (User user : usersCollection){
            if (user.getStatus().equals(userStatus)){
                usersWithGivenStatus.add(user);
            }
        }
        return usersWithGivenStatus;
    }

    public Mentor getMentorById(int id){
        for (User user : usersCollection){
            if(user.getId() == id && user.getStatus().equals("mentor")){
                return (Mentor)user;
            }
        }
        return null;
    }

    public Student getStudentById(int id){
        for (User user : usersCollection){
            if(user.getId() == id && user.getStatus().equals("student")){
                return (Student)user;
            }
        }
        return null;
    }

    public void disconnectDatabase(){
        databaseConnection.closeDatabase();
    }
}

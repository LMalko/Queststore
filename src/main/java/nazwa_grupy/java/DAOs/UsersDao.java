package nazwa_grupy.java.DAOs;

import nazwa_grupy.java.Iterator_DBProcessor.DBStatementProcessor;
import nazwa_grupy.java.Models.*;

import java.util.ArrayList;

public class UsersDao {

    private static ArrayList<User> usersCollection = new ArrayList<User>();
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");
    private static final int idIndex = 0;
    private static final int nameIndex = 1;
    private static final int surnameIndex = 2;
    private static final int passwordIndex = 4;
    private static final int statusIndex = 5;
    private static final int groupIdIndex = 6;
    private static final int experienceIndex = 7;


    private void importUsersData() {
        usersCollection.clear();
        databaseProcessor.connectToDatabase();
        ArrayList<ArrayList<String>> users = databaseProcessor.getArrayListFromQuery("SELECT * FROM users");
        for(int i =0; i < users.size(); i++) {
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
        int id = Integer.parseInt(personData.get(idIndex));
        String name = personData.get(nameIndex);
        String surname = personData.get(surnameIndex);
        String password = personData.get(passwordIndex);
        String status = personData.get(statusIndex);
        String experience = personData.get(experienceIndex);
        int groupId = 0;

        if (personData.get(groupIdIndex) != null){
            groupId = Integer.parseInt(personData.get(groupIdIndex));
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
            String query = "SELECT current_balance FROM wallet WHERE student_id = '" + id +"';";
            int wallet = databaseProcessor.getIntegerDataFromQuery(query, "current_balance");
            query = "SELECT total_income FROM wallet WHERE student_id = '" + id +"';";
            int totalIncome = databaseProcessor.getIntegerDataFromQuery(query, "total_income");
            person = new Student(id, name, surname, password, group, wallet, totalIncome, experience);
        }
        return person;
    }

    public void addUserToDatabase(User user){
        databaseProcessor.connectToDatabase();
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
        databaseProcessor.executeUpdateAgainstDatabase(query);

    }

    public void addStudentWalletToDatabase(Student student){
        int currentBalance = student.getStudentWallet();
        int totalIncome = student.getStudentTotalIncome();
        String query = "SELECT id FROM users WHERE login = '" + student.getLogin() + "';";
        int studentId = databaseProcessor.getIntegerDataFromQuery(query, "id");
        query = "INSERT INTO wallet (current_balance, total_income, student_id) VALUES( '" +
                currentBalance + "', '" +
                totalIncome + "', '" +
                studentId +
                "');";
        databaseProcessor.executeUpdateAgainstDatabase(query);


    }

    public String getUserGroupNameByGroupId(int groupID){
        String query = "SELECT * FROM groups WHERE id = '" + groupID +"';";
        String groupName = databaseProcessor.getStringDataFromQuery(query, "name");
        return groupName;
    }

    public void updateUserGroupInDatabase(User user){
        String query = "UPDATE users SET group_id = " + "'" + user.getUserGroupId() + "' " +
                        "WHERE id = " + user.getId() + ";";
        databaseProcessor.executeUpdateAgainstDatabase(query);
    }

    public void updateStudentWalletInDatabase(Student student){
        databaseProcessor.connectToDatabase();
        int currentBalance = student.getStudentWallet();
        int totalIncome = student.getStudentTotalIncome();
        int studentId = student.getId();

        String query = "UPDATE wallet SET current_balance = '" + currentBalance + "' ," +
                        "total_income = '" + totalIncome + "' " +
                        "WHERE student_id = '" + studentId + "';";
        databaseProcessor.executeUpdateAgainstDatabase(query);
    }

    public void updateUserDataInDatabase(User user) {
        String name = user.getName();
        String surname = user.getSurname();
        String login = user.getLogin();
        String password = user.getPassword();
        String status = user.getStatus();
        int groupId = user.getUserGroupId();
        String experience = user.getUserExperienceLevel();
        String query = "UPDATE users SET name = '" + name + "' ," +
                        "surname = '" + surname + "' ," +
                        "login = '" + login + "' ," +
                        "password = '" + password + "' ," +
                        "status = '" + status + "' ," +
                        "group_id = '" + groupId + "' ," +
                        "experience = '" + experience + "'" +
                        "WHERE id = " + user.getId() + ";";
        databaseProcessor.executeUpdateAgainstDatabase(query);
    }

    public ArrayList<User> getAllUsersByStatus(String userStatus){
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
        databaseProcessor.closeDatabase();
    }
}

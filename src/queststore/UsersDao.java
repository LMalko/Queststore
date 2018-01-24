import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class UsersDao {

    private static ArrayList<User> usersCollection = new ArrayList<User>();
    private JDBConnection databaseConnection = new JDBConnection("jdbc:sqlite:db/questStore.db");

    private void importUsersData() {
        databaseConnection.connectToDatabase();
        ArrayList<ArrayList<String>> users = databaseConnection.getArrayListFromQuery("SELECT * FROM users");
        for(int i =0; i < users.size(); i++){
            ArrayList<String> personData = users.get(i);
            User person = createUserObject(personData);
            System.out.println(person.getName());
            usersCollection.add(person);
            }
        }

    public ArrayList<User> getUsersCollection() {
        importUsersData();
        return usersCollection;
    }

    private User createUserObject(ArrayList<String> personData) {
        String name = personData.get(1);
        String surname = personData.get(2);
        String login = personData.get(3);
        String password = personData.get(4);
        String status = personData.get(5);
        int groupIndex = 0;
        //int walletID = Integer.parseInt(personData.get(7)); BRAK W BAZIE DANYCH

        if (personData.get(6) != null){
            groupIndex = Integer.parseInt(personData.get(6));
        }
        if (personData.get(7) != null) {
            String experienceLevel = personData.get(7);
        } else {
            String experienceLevel = "";
        }

        User person = null;
        if(status.equals("admin")){
            person = new Admin(name, surname, password);
        }
        else if(status.equals("mentor")){
            person = new Mentor(name, surname, password, groupIndex);
        }
        else if(status.equals("student")){
            person = new Student(name, surname, password);
        }
        return person;
    }


    public void saveUsersToFile(){
        String headerLine = "id,name,surname,login,password,status,group,wallet,experience";
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/usersData.csv"));
            StringBuilder sb = new StringBuilder();
            sb.append(headerLine);
            sb.append("\n");
            for (User user : usersCollection) {
                sb.append(user.getId());
                sb.append(",");
                sb.append(user.getName());
                sb.append(",");
                sb.append(user.getSurname());
                sb.append(",");
                sb.append(user.getLogin());
                sb.append(",");
                sb.append(user.getPassword());
                sb.append(",");
                sb.append(user.getStatus());
                sb.append(",");
                sb.append(user.getUserGroupName());
                sb.append(",");
                sb.append(user.getUserWallet());
                sb.append(",");
                sb.append(user.getUserExperience());
                sb.append("\n");
            }
            br.write(sb.toString());
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addUserToUsersCollection(User user){
        usersCollection.add(user);
    }

    public ArrayList<User> getAllUsersByStatus(String userStatus){
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
}

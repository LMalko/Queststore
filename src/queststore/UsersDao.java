import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class UsersDao {

    private String csvFile = "csv/usersData.csv";
    private BufferedReader br = null;
    private String line = "";
    private String separator = ",";
    private static ArrayList<User> usersCollection = new ArrayList<User>();

    private void importUsersData() {
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String headerLine = br.readLine();  // created to skip first line in csv file
            while ((line = br.readLine()) != null) {
                String[] personData = line.split(separator);
                User person = createUserObject(personData);
                usersCollection.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeReader(br);
        }
    }

    public ArrayList<User> getUsersCollection() {
        importUsersData();
        return usersCollection;
    }

    private User createUserObject(String[] personData) {
        String name = personData[1];
        String surname = personData[2];
        String login = personData[3];
        String password = personData[4];
        String status = personData[5];
        User person = null;

        if(status.equals("admin")){
            person = new Admin(name, surname, password);
        }
        else if(status.equals("mentor")){
            person = new Mentor(name, surname, password);
        }
        else if(status.equals("student")){
            person = new Student(name, surname, password);
        }
        return person;
    }

    private void closeReader(BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUsersToFile(){
        String headerLine = "id,name,surname,login,password,status";
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/usersData.csv"));
            StringBuilder sb = new StringBuilder();
            System.out.println(usersCollection.size());
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
        ArrayList<User> usersCollection = new ArrayList<User>();
        for (User user : usersCollection){
            if (user.getStatus().equals(userStatus)){
                usersCollection.add(user);
            }
        }
        return usersCollection;
    }

    public Mentor getMentorById(int id){
        for (User user : usersCollection){
            if(user.getId() == id && user.getStatus().equals("mentor")){
                return (Mentor)user;
            }
        }
        return null;
    }
}

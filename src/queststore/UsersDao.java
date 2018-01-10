import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class UsersDao {

    private String csvFile = "csv/usersData.csv";
    private BufferedReader br = null;
    private String line = "";
    private String separator = ",";
    private ArrayList<User> usersCollection = new ArrayList<User>();

    private void importUsersData() {
        try {
            br = new BufferedReader(new FileReader(csvFile));
            String headerLine = br.readLine();  // created to skip first line in csv file
            while ((line = br.readLine()) != null) {
                String[] personData = line.split(separator);
                User person = createNewUserObject(personData);
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

    private User createNewUserObject(String[] personData) {
        String login = personData[1];
        String password = personData[2];
        String status = personData[3];
        User person = new User(login, password, status);
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
}

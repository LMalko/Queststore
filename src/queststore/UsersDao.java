import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class UsersDao{

    private String csvFile = "csv/usersData.csv";
    private BufferedReader br = null;
    private String line = "";
    private String separator = ",";
    private ArrayList<Person> usersCollection = new ArrayList<Person>();

    public void getUsersData(){
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] personData = line.split(separator);
                Person person = createNewPersonObject(personData);
                userLogin.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Person createNewPersonObject(String[] personData){
        String login = personData[1];
        String password = personData[2];
        String status = personData[3];
        Person person = new Person(login, password, status);
        return person;
    }
}

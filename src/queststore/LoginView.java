import java.util.Scanner;

class LoginView{

    private Scanner reader = new Scanner(System.in);

    public void displayText(String text){
        System.out.prinln(text);
    }

    public String getPassword(){
        System.out.println("Enter password: ");
        String password = reader.nextln();
    }

    public String getLogin(){
        System.out.prinln("Enter login: ");
        String login = reader.nextln();
    }

}

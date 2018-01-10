import java.util.Scanner;

class LoginView{

    private Scanner reader = new Scanner(System.in);

    public void displayText(String text){
        System.out.println(text);
    }

    public String getPassword(){
        System.out.println("Enter password: ");
        String password = reader.nextLine();
        return password;
    }

    public String getLogin(){
        System.out.println("Enter login: ");
        String login = reader.nextLine();
        return login;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }  
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class UserView{
    private BufferedReader br = null;
    private Scanner reader = new Scanner(System.in);

    private String importUserMenu(String filename ) {
        String userMenu = "";
        try {
            br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
                userMenu = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
        }
    }
        return userMenu;
    }
    public void displayUserMenu(String filename){
        clearScreen();
        String userMenu = importUserMenu(filename);
            System.out.println(userMenu);
        }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String getUserInput(String text){
        System.out.println(text);
        String input = reader.nextLine();
        return input;
    }

    public void displayText(String text){
        System.out.println(text);
    }
}

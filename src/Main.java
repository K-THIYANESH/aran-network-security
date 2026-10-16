import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Load persisted events
        DataStore.loadEvents();

        Scanner sc = new Scanner(System.in);

        LoginService login = new LoginService();

        if(login.login(sc))
        {
            Menu menu = new Menu();
            menu.start(sc);
        }

        // Save events to file
        DataStore.saveEvents();

        sc.close();
    }
}
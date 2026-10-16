import java.util.Scanner;

public class LoginService {

    public boolean login(Scanner sc)
    {

        System.out.println("===== LOGIN =====");

        System.out.print("Username : ");
        String user = sc.nextLine().trim();

        System.out.print("Password : ");
        String pass = sc.nextLine().trim();

        // Input validation: Check for empty or blank fields
        if (user.isEmpty() || pass.isEmpty()) {
            System.out.println("Error: Username and Password fields cannot be empty.");
            return false;
        }

        if(user.equals("admin") && pass.equals("admin123"))
        {
            System.out.println("Login Successful");
            return true;
        }

        System.out.println("Invalid Login");

        return false;

    }

}
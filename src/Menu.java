import java.util.Scanner;

public class Menu {

    EventService eventService = new EventService();
    ThreatDetectionService threatService = new ThreatDetectionService();
    ReportService reportService = new ReportService();

    public void start(Scanner sc)
    {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RED = "\u001B[31m";

        int choice;

        do
        {
            System.out.println("\n" + ANSI_CYAN + "====== ARAN SECURITY CLI ======" + ANSI_RESET);
            System.out.println("1. Add Event");
            System.out.println("2. View Events");
            System.out.println("3. Analyze Threat");
            System.out.println("4. Generate Report");
            System.out.println("5. View Statistics");
            System.out.println("6. Exit");

            System.out.print(ANSI_GREEN + "Enter Choice : " + ANSI_RESET);
            if (!sc.hasNextInt()) {
                System.out.println(ANSI_RED + "Please enter a valid number." + ANSI_RESET);
                sc.nextLine();
                choice = 0;
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice)
            {
                case 1:
                    eventService.addEvent(sc);
                    break;

                case 2:
                    eventService.viewEvents();
                    break;

                case 3:
                    threatService.analyze();
                    break;

                case 4:
                    reportService.generate();
                    break;

                case 5:
                    threatService.showStatistics();
                    break;

                case 6:
                    System.out.println(ANSI_CYAN + "Thank you for using ARAN CLI. Goodbye!" + ANSI_RESET);
                    break;

                default:
                    System.out.println(ANSI_RED + "Invalid Choice" + ANSI_RESET);
            }

        }while(choice!=6);

    }
}
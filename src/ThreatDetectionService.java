import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ThreatDetectionService {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED_BOLD = "\u001B[1;31m";
    private static final String ANSI_YELLOW_BOLD = "\u001B[1;33m";
    private static final String ANSI_CYAN_BOLD = "\u001B[1;36m";
    private static final String ANSI_GREEN_BOLD = "\u001B[1;32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public void analyze()
    {

        System.out.println("\n" + ANSI_BLUE + "=== Threat Analysis ===" + ANSI_RESET);

        for(SecurityEvent e : DataStore.events)
        {

            String threat = "LOW";
            String color = ANSI_GREEN_BOLD;

            if(e.getEventType().equals("Malware")) {
                threat = "CRITICAL";
                color = ANSI_RED_BOLD;
            }

            else if(e.getEventType().equals("Login Failure")) {
                threat = "HIGH";
                color = ANSI_YELLOW_BOLD;
            }

            else if(e.getEventType().equals("USB Inserted")) {
                threat = "MEDIUM";
                color = ANSI_CYAN_BOLD;
            }

            System.out.println(e.getUsername() + " [" + e.getIpAddress() + "] -> " + color + threat + ANSI_RESET);

        }

        LoggerService.log("Threat Analysis Completed");

    }

    public void showStatistics() {
        System.out.println("\n" + ANSI_BLUE + "=== Security Metrics & Statistics ===" + ANSI_RESET);
        if (DataStore.events.isEmpty()) {
            System.out.println("No security events recorded yet.");
            return;
        }

        int total = DataStore.events.size();
        int critical = 0, high = 0, medium = 0, low = 0;
        Set<String> uniqueUsers = new HashSet<>();
        Set<String> uniqueIps = new HashSet<>();

        for (SecurityEvent e : DataStore.events) {
            uniqueUsers.add(e.getUsername());
            uniqueIps.add(e.getIpAddress());

            if (e.getEventType().equals("Malware")) critical++;
            else if (e.getEventType().equals("Login Failure")) high++;
            else if (e.getEventType().equals("USB Inserted")) medium++;
            else low++;
        }

        System.out.println("Total Logged Events   : " + total);
        System.out.println("Critical Threats (🔴) : " + ANSI_RED_BOLD + critical + ANSI_RESET);
        System.out.println("High Threats     (🟡) : " + ANSI_YELLOW_BOLD + high + ANSI_RESET);
        System.out.println("Medium Threats   (🔵) : " + ANSI_CYAN_BOLD + medium + ANSI_RESET);
        System.out.println("Low Threats      (🟢) : " + ANSI_GREEN_BOLD + low + ANSI_RESET);
        System.out.println("-------------------------------------");
        System.out.println("Unique Active Users   : " + uniqueUsers.size() + " " + uniqueUsers);
        System.out.println("Unique Source IPs     : " + uniqueIps.size() + " " + uniqueIps);
    }

}
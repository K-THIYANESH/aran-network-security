import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataStore {

    public static ArrayList<SecurityEvent> events = new ArrayList<>();
    private static final String DATA_FILE = "data/events.csv";

    public static void loadEvents() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            // Seed with three predefined events
            events.add(new SecurityEvent("admin", "Login Success", "192.168.1.1", java.time.LocalDateTime.now().minusHours(2)));
            events.add(new SecurityEvent("guest", "Login Failure", "192.168.1.50", java.time.LocalDateTime.now().minusHours(1).minusMinutes(30)));
            events.add(new SecurityEvent("system", "Malware", "10.0.0.4", java.time.LocalDateTime.now().minusMinutes(45)));
            saveEvents(); // Save them to create the file
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                SecurityEvent event = SecurityEvent.fromCsv(line);
                if (event != null) {
                    events.add(event);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading events: " + e.getMessage());
        }
    }

    public static void saveEvents() {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (FileWriter fw = new FileWriter(file)) {
            for (SecurityEvent event : events) {
                fw.write(event.toCsv() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving events: " + e.getMessage());
        }
    }
}
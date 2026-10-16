import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SecurityEvent {

    private String username;
    private String eventType;
    private String ipAddress;
    private LocalDateTime timestamp;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SecurityEvent(String username, String eventType, String ipAddress) {
        this.username = username;
        this.eventType = eventType;
        this.ipAddress = ipAddress;
        this.timestamp = LocalDateTime.now();
    }

    public SecurityEvent(String username, String eventType, String ipAddress, LocalDateTime timestamp) {
        this.username = username;
        this.eventType = eventType;
        this.ipAddress = ipAddress;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getEventType() {
        return eventType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFormattedTimestamp() {
        return timestamp.format(formatter);
    }

    public String toCsv() {
        return username + "," + eventType + "," + ipAddress + "," + timestamp.format(formatter);
    }

    public static SecurityEvent fromCsv(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length < 4) return null;
        LocalDateTime ts = LocalDateTime.parse(parts[3], formatter);
        return new SecurityEvent(parts[0], parts[1], parts[2], ts);
    }
}
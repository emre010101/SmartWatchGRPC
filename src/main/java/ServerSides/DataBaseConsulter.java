package ServerSides;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataBaseConsulter {
	
    private static File directory = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String FILE_NAME = directory.getAbsolutePath() + "//stepsDatabase.txt";

    public static int checkLastHour() {
    	System.out.println("--ServerSide: DataBaseConsulter.checkLastHour() invoked");
        int stepsInLastHour = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            // Get the current time and an hour before
            ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
            ZonedDateTime oneHourBefore = now.minusHours(1);

            while ((line = br.readLine()) != null) {
                try {
                    // Split the line by the delimiter
                    String[] parts = line.split("->");

                    // Parse the timestamp and step count
                    ZonedDateTime timestamp = ZonedDateTime.parse(parts[0], DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    int steps = Integer.parseInt(parts[1]);

                    // Check if the timestamp is within the last hour
                    if (timestamp.isAfter(oneHourBefore) && timestamp.isBefore(now)) {
                        stepsInLastHour += steps;
                    }
                } catch (DateTimeParseException | NumberFormatException e) {
                    // Log a warning and continue with the next line
                    System.err.printf("Warning: Skipping improperly formatted line '%s'\n", line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Test in server side : " + stepsInLastHour);
        return stepsInLastHour;
    }

}

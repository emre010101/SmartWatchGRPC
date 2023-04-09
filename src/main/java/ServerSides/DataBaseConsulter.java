package ServerSides;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sw.stepCounter.service1.WeekDays;

public class DataBaseConsulter {
	
    private static File directory = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String STEPS_DATABASE = directory.getAbsolutePath() + "//stepsDatabase.txt";
    private static File directory2 = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String REMINDER_DATABASE = directory.getAbsolutePath() + "//reminderDatabase.txt";
    
    public static int checkStepsFromStartTime(ZonedDateTime startTime) {
        System.out.println("--ServerSide: DataBaseConsulter.checkStepsFromStartTime() invoked");
        int stepsFromStartTime = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(STEPS_DATABASE))) {
            String line;

            // Get the current time
            ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());

            while ((line = br.readLine()) != null) {
                try {
                    // Split the line by the delimiter
                    String[] parts = line.split("->");

                    // Parse the timestamp and step count
                    ZonedDateTime timestamp = ZonedDateTime.parse(parts[0], DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                    int steps = Integer.parseInt(parts[1]);

                    // Check if the timestamp is within the specified time range
                    if (timestamp.isAfter(startTime) && timestamp.isBefore(now)) {
                        stepsFromStartTime += steps;
                    }
                } catch (DateTimeParseException | NumberFormatException e) {
                    // Log a warning and continue with the next line
                    System.err.printf("Warning: Skipping improperly formatted line '%s'\n", line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Test in server side : " + stepsFromStartTime);
        return stepsFromStartTime;
    }

	public static int checkAverageSteps(WeekDays period) {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
		switch (period) {
		
		case LAST_DAY:
			ZonedDateTime oneDayBefore = now.minusDays(24);
			return checkStepsFromStartTime(oneDayBefore);
			
		case LAST_5_DAYS:
			ZonedDateTime fiveDaysBefore = now.minusDays(5);
			return checkStepsFromStartTime(fiveDaysBefore)/5;
			
		case LAST_10_DAYS:
			ZonedDateTime tenDaysBefore = now.minusDays(10);
			return checkStepsFromStartTime(tenDaysBefore)/10;
			
		case LAST_30_DAYS:
			ZonedDateTime oneMonthBefore = now.minusDays(30);
			return checkStepsFromStartTime(oneMonthBefore)/30;
		}
		return 0;
	}

	public static String saveReminder(String taskName, String date_time, int type) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(REMINDER_DATABASE, true))) {
	        String currentDateTime = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	        writer.write(taskName + "<-->" + date_time + "<-->" + type + currentDateTime + "\n");
	    } catch (IOException e) {
	        System.err.println("Failed to save reminder to file: " + e.getMessage());
	        return "Not saved: " + taskName;
	    }
	    return "Saved: " + taskName;
	}
	


}

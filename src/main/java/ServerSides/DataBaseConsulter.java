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
import java.util.ArrayList;
import java.util.List;

import sw.Monitoring.service3.EmergencyContact;
import sw.Monitoring.service3.UserRecords;
import sw.stepCounter.service1.Periods;
import sw.stepCounter.service1.WeekDays;

public class DataBaseConsulter {
	
    private static File directory = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String STEPS_DATABASE = directory.getAbsolutePath() + "//stepsDatabase.txt";
    private static File directory2 = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String REMINDER_DATABASE = directory2.getAbsolutePath() + "//reminderDatabase.txt";
    private static File directory3 = new File("C:\\Code\\SmartWatchGRPC\\database");
    private static final String MONITORING_DATABASE = directory2.getAbsolutePath() + "//monitoringDatabase.txt";
    
    
    /*------------------------------------SERVICE 1 IMPLEMENTATIONS---------------------------------------*/
    
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

	@SuppressWarnings("incomplete-switch")
	public static int checkAverageSteps(Periods periods) {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
		switch (periods) {
		
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

	/*------------------------------------SERVICE 2 IMPLEMENTATIONS---------------------------------------*/
	
	public static String saveReminder(String taskName, String date_time, int type) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(REMINDER_DATABASE, true))) {
	        String currentDateTime = ZonedDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	        writer.write(taskName + "<->" + date_time + "<->" + type + "<->" + currentDateTime + "\n");
	    } catch (IOException e) {
	        System.err.println("Failed to save reminder to file: " + e.getMessage());
	        return "Not saved: " + taskName;
	    }
	    return "Saved: " + taskName;
	}

	/*Iterate over the unMarked reminders and if it's found marks them*/
	public static String markCompleted(String taskName) {
        List<String> unmarkedReminders = checkUnmarkedReminders();
        for (String line : unmarkedReminders) {
            String[] parts = line.split("<->");
            if (parts[0].equals(taskName)) {
                int lineNumber = Integer.parseInt(parts[parts.length - 1]);
                markLine(lineNumber);
                return "Task marked as completed: " + taskName;
            }
        }
        return "Task not found: " + taskName;
    }

	/*Passed linenumbers which are to be marked will be marked here in the dataBase*/
    public static void markLine(int lineNumber) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(REMINDER_DATABASE))) {
            String line;
            int currentLineNumber = 0;
            while ((line = reader.readLine()) != null) {
                if (currentLineNumber == lineNumber) {
                    line = line + "<->+";
                }
                lines.add(line);
                currentLineNumber++;
            }
        } catch (IOException e) {
            System.err.println("Failed to read reminders from file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REMINDER_DATABASE))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to write reminders to file: " + e.getMessage());
        }
    }

    /*It will iterate over the database and if find the reminder it will return it in a list with the line number found*/
    public static List<String> checkUnmarkedReminders() {
        List<String> unmarkedReminders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(REMINDER_DATABASE))) {
            String line;
            int lineNumber = -1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (!line.endsWith("<->+")) {
                    unmarkedReminders.add(line + "<->" + lineNumber);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read reminders from file: " + e.getMessage());
        }

        return unmarkedReminders;
    }
	//Testing the DataBaseConsulter Methods
	public static void main(String[] args) {
		/*List<String> control = checkUnmarkedReminders();
		System.out.println("Printing from the main the unmarkeds");
		for(String line : control) {
			System.out.println(line);
		}
		System.out.println("AFter");
		
		System.out.println(markCompleted("Booster for the foot"));*/
		//saveReminder("Emre", "test", 4);
		UserRecords rec = lookForUser(1);
		System.out.println(rec.getAddress());
	}
	
	/*------------------------------------SERVICE 3 IMPLEMENTATIONS---------------------------------------*/
	
	/*Save users send from client side to the database*/
	public static String saveUser(UserRecords request) {
		int patient_id = findLast();
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(MONITORING_DATABASE, true))) {
	        String userRecordLine = patient_id + "<->" + request.getName() + "<->" + request.getAge() + "<->" +
	                                request.getWeight() + "<->" + request.getHeight() + "<->" + request.getAddress();
	
	        for (EmergencyContact contact : request.getContactsList()) {
	            userRecordLine += "<->" + contact.getName() + "<->" + contact.getPhone();
	        }
	        writer.write(userRecordLine + "\n");
	        return patient_id + "<->" + request.getName() + "<->saved to database";
	    } catch (IOException e) {
	        System.err.println("Failed to save user record to file: " + e.getMessage());
	        return patient_id + "<->" + request.getName() + "<->it could not be saved";
	    }
	}
	
	/*Helper method:
	 * It will check the database what was the last id number has been used and it will return +1 to use in saveUser method*/
	private static int findLast() {
	    int lastId = 0;
	    try (BufferedReader reader = new BufferedReader(new FileReader(MONITORING_DATABASE))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split("<->");
	            if (parts.length >= 6) {
	                int currentId = Integer.parseInt(parts[0]);
	                if (currentId > lastId) {
	                    lastId = currentId;
	                }
	            } else {
	                System.out.println("Invalid database entry for patientId: " + parts[0] + ". Not enough fields in the entry.");
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Failed to read user records from file: " + e.getMessage());
	    }
	    return lastId + 1;
	}

	//@SuppressWarnings("unlikely-arg-type")
	public static UserRecords lookForUser(int id) {
	    //int patientId = request.getPatientId();
	    int patientId = id;
	    try (BufferedReader reader = new BufferedReader(new FileReader(MONITORING_DATABASE))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split("<->");
	            if (parts.length >= 6) {
		            if (Integer.parseInt(parts[0]) == patientId) {
		                UserRecords.Builder userBuilder = UserRecords.newBuilder()
		                        //.setPatientId(Integer.parseInt(parts[0]))
		                        .setName(parts[1])
		                        .setAge(Integer.parseInt(parts[2]))
		                        .setWeight(Double.parseDouble(parts[3]))
		                        .setHeight(Double.parseDouble(parts[4]))
		                        .setAddress(parts[5]);
	
		                for (int i = 6; i < parts.length; i += 2) {
		                    if (i + 1 < parts.length) {
		                        EmergencyContact contact = EmergencyContact.newBuilder()
		                                .setName(parts[i])
		                                .setPhone(parts[i + 1])
		                                .build();
		                        userBuilder.addContacts(contact);
		                    } else {
		                        System.err.println("Invalid database entry for patientId: " + patientId + ". Missing phone number for emergency contact.");
		                    }
		                }
		                return userBuilder.build();
		            }
	            }else {
	            	System.out.println("Invalid database entry for patientId: " + parts[0] + ". Not enough fields in the entry.");
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Failed to read user records from file: " + e.getMessage());
	    }

	    UserRecords notFoundUser = UserRecords.newBuilder()
	            .setName("User not found")
	            .build();

	    return notFoundUser;
	}

	public static UserRecords lookForUser(String patientName) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(MONITORING_DATABASE))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split("<->");
	            if (parts.length >= 6) {
	                if (parts[1].equals(patientName)) {
	                    UserRecords.Builder userBuilder = UserRecords.newBuilder()
	                            .setName(parts[1])
	                            .setAge(Integer.parseInt(parts[2]))
	                            .setWeight(Double.parseDouble(parts[3]))
	                            .setHeight(Double.parseDouble(parts[4]))
	                            .setAddress(parts[5]);

	                    for (int i = 6; i < parts.length; i += 2) {
	                        if (i + 1 < parts.length) {
	                            EmergencyContact contact = EmergencyContact.newBuilder()
	                                    .setName(parts[i])
	                                    .setPhone(parts[i + 1])
	                                    .build();
	                            userBuilder.addContacts(contact);
	                        } else {
	                            System.err.println("Invalid database entry for patientName: " + patientName + ". Missing phone number for emergency contact.");
	                        }
	                    }
	                    return userBuilder.build();
	                }
	            } else {
	                System.out.println("Invalid database entry for patientId: " + parts[0] + ". Not enough fields in the entry.");
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Failed to read user records from file: " + e.getMessage());
	    }

	    UserRecords notFoundUser = UserRecords.newBuilder()
	            .setName("User not found")
	            .build();

	    return notFoundUser;
	}



	


}

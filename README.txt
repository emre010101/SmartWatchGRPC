Smart Healthy Watch Project
The Smart Healthy Watch project is a comprehensive health platform that provides users with access to a range of health-related services through a smart watch. The platform consists of three main services: Step Counter, Reminder, and Monitoring.

Overview
Step Counter service: Allows users to monitor their daily steps and track their progress towards fitness goals. Users can check the number of steps taken from the start of the current hour to the current minute. Additionally, the service enables users to set step goals, and the server responds once the goal is reached.
Reminder service: Enables users to set reminders for appointments, pills, or other tasks, helping them stay organized and on track. The platform allows users to mark tasks as complete and retrieve a list of their scheduled tasks.
Monitoring service: Offers advanced health monitoring features, such as heart rate tracking and access to health records. Users can securely store their health records on the platform and access them at any time. Additionally, the service provides real-time heart rate tracking and alerts users if their heart rate falls outside of a safe range.
Services
Step Counter service
SendSteps: Sends multiple steps every second, changing on the current hour of the day (Client Streaming).
GetLastHourSteps: Requests the number of steps taken by the user in the last hour (Unary).
GetAverageHourlySteps: Requests the average number of steps taken by the user for each hour of the day (Client Streaming).
SetStepGoal: Sets a daily step goal and tracks progress towards that goal (Unary).
Reminder service
SetTaskReminder: Saves when pills need to be taken and other tasks and reminds the user (Unary).
checkReminder: Checks the reminders whether the time is up or not (Server Streaming).
MarkTaskComplete: Tracks completion of a task/event (Unary).
GetTaskList: Retrieves the list of tasks that have been set on the server (Server Streaming).
Monitoring service
SetUserRecords: Saves user credentials for accessing health records (Unary).
GetHealthRecords: Requests health records (Server Streaming).
MonitorHeartRate: Sends heart rate data from client and receives warnings from server (Bidirectional Streaming).
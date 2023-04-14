package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIClass {
	  private static JPanel mainMenuPanel;
	    private static JPanel subPagePanel;
	    private static JFrame frame;

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> createGUI());
	    }

	    private static void createGUI() {
	        frame = new JFrame("gRPC Client");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 200);

	        mainMenuPanel = new JPanel();
	        frame.getContentPane().add(mainMenuPanel);
	        placeComponents(mainMenuPanel);

	        frame.setVisible(true);
	    }

	    private static void placeComponents(JPanel panel) {
	        panel.setLayout(null);

	        JButton service1Button = createServiceButton("Service 1", 10, 20);
	        panel.add(service1Button);

	        JButton service2Button = createServiceButton("Service 2", 10, 60);
	        panel.add(service2Button);

	        JButton service3Button = createServiceButton("Service 3", 10, 100);
	        panel.add(service3Button);

	        service1Button.addActionListener(e -> openSubPage("Service 1 Subpage"));
	        service2Button.addActionListener(e -> openSubPage("Service 2 Subpage"));
	        service3Button.addActionListener(e -> openSubPage("Service 3 Subpage"));
	    }

	    private static JButton createServiceButton(String text, int x, int y) {
	        JButton button = new JButton(text);
	        button.setBounds(x, y, 150, 25);
	        return button;
	    }
	    
	    private static void openSubPage(String title) {
	        subPagePanel = new JPanel();
	        subPagePanel.setLayout(null);

	        int startY = 20;

	        // SendSteps
	        JButton sendStepsButton = new JButton("Send Steps");
	        sendStepsButton.setBounds(10, startY, 150, 25);
	        subPagePanel.add(sendStepsButton);
	        startY += 30;

	        // GetLastHourSteps
	        JButton getLastHourStepsButton = new JButton("Get Last Hour Steps");
	        getLastHourStepsButton.setBounds(10, startY, 150, 25);
	        subPagePanel.add(getLastHourStepsButton);
	        startY += 30;

	        // GetAverageHourlySteps
	        JComboBox<WeekDays> weekDaysComboBox = new JComboBox<>(WeekDays.values());
	        weekDaysComboBox.setBounds(10, startY, 150, 25);
	        subPagePanel.add(weekDaysComboBox);
	        startY += 30;

	        JButton getAverageHourlyStepsButton = new JButton("Get Average Hourly Steps");
	        getAverageHourlyStepsButton.setBounds(10, startY, 150, 25);
	        subPagePanel.add(getAverageHourlyStepsButton);
	        startY += 30;

	        // SetStepGoal
	        JButton setStepGoalButton = new JButton("Set Step Goal");
	        setStepGoalButton.setBounds(10, startY, 150, 25);
	        subPagePanel.add(setStepGoalButton);
	        startY += 40;

	        // Go Back button
	        JButton goBackButton = new JButton("Go Back");
	        goBackButton.setBounds(10, startY, 100, 25);
	        subPagePanel.add(goBackButton);
	        goBackButton.addActionListener(e -> goBackToMainMenu());
	        
	       /* JButton goBackButton = new JButton("Go Back");
	        goBackButton.setBounds(10, 80, 100, 25);
	        subPagePanel.add(goBackButton);*/

	        // Implement the actionPerformed methods for each button
	        sendStepsButton.addActionListener(e -> {
	            // Add your SendSteps RPC logic here
	        });

	        getLastHourStepsButton.addActionListener(e -> {
	            // Add your GetLastHourSteps RPC logic here
	        });

	        getAverageHourlyStepsButton.addActionListener(e -> {
	            // Add your GetAverageHourlySteps RPC logic here, using the selected value from the weekDaysComboBox
	            WeekDays selectedWeekDays = (WeekDays) weekDaysComboBox.getSelectedItem();
	        });

	        setStepGoalButton.addActionListener(e -> {
	            // Add your SetStepGoal RPC logic here
	        });

	        frame.getContentPane().remove(mainMenuPanel);
	        frame.getContentPane().add(subPagePanel);
	        frame.revalidate();
	        frame.repaint();
	    }

	    enum WeekDays {
	        LAST_DAY,
	        LAST_5_DAYS,
	        LAST_10_DAYS,
	        LAST_30_DAYS
	    }
	    private static void goBackToMainMenu() {
	        frame.getContentPane().remove(subPagePanel);
	        frame.getContentPane().add(mainMenuPanel);
	        frame.revalidate();
	        frame.repaint();
	    }
	}

	    /*private static void openSubiPage(String title) {
	        subPagePanel = new JPanel();
	        subPagePanel.setLayout(null);

	        JLabel label = new JLabel("This is the " + title);
	        label.setBounds(10, 20, 250, 25);
	        subPagePanel.add(label);

	        JButton goBackButton = new JButton("Go Back");
	        goBackButton.setBounds(10, 80, 100, 25);
	        subPagePanel.add(goBackButton);

	        goBackButton.addActionListener(e -> goBackToMainMenu());

	        frame.getContentPane().remove(mainMenuPanel);
	        frame.getContentPane().add(subPagePanel);
	        frame.revalidate();
	        frame.repaint();
	    }*/




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeConverter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Time Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        frame.add(panel);
        JLabel instructionLabel = new JLabel("Choose an option:");
        panel.add(instructionLabel);

        JButton dayOfWeekButton = new JButton("1. Day of the Week");
        JButton leapYearButton = new JButton("2. Leap Year");
        JButton timeConverterButton = new JButton("3. Time Converter");
// Make the label for input.
        JLabel inputLabel = new JLabel("Input:");
        JTextField inputField = new JTextField();
        panel.add(inputLabel);
        panel.add(inputField);

        JLabel outputLabel = new JLabel("Result: ");
        panel.add(outputLabel);

        dayOfWeekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                int day = 0;

                try {
                    day = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    outputLabel.setText("Error: Input must be a number.");
                    return;
                }

                if (day == 1) {
                    outputLabel.setText("Result: Sunday");
                } else if (day == 2) {
                    outputLabel.setText("Result: Monday");
                } else if (day == 3) {
                    outputLabel.setText("Result: Tuesday");
                } else if (day == 4) {
                    outputLabel.setText("Result: Wednesday");
                } else if (day == 5) {
                    outputLabel.setText("Result: Thursday");
                } else if (day == 6) {
                    outputLabel.setText("Result: Friday");
                } else if (day == 7) {
                    outputLabel.setText("Result: Saturday");
                } else {
                    outputLabel.setText("Error: Day must be between 1 and 7.");
                }
            }
        });

        leapYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                int year = 0;

                try {
                    year = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    outputLabel.setText("Error: Input must be a number.");
                    return;
                }

                if (input.length() != 4) {
                    outputLabel.setText("Error: Input must be a valid 4-digit year.");
                    return;
                }

                if (year % 4 == 0) {
                    if (year % 100 != 0) {
                        outputLabel.setText("Result: Leap Year");
                    } else {
                        if (year % 400 == 0) {
                            outputLabel.setText("Result: Leap Year");
                        } else {
                            outputLabel.setText("Result: Not a Leap Year");
                        }
                    }
                } else {
                    outputLabel.setText("Result: Not a Leap Year");
                }
            }
        });

        timeConverterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                String[] timeParts = input.split(":");
                
                if (timeParts.length != 2) {
                    outputLabel.setText("Error: Input format must be HH:MM");
                    return;
                }

                int hours = 0;
                int minutes = 0;

                try {
                    hours = Integer.parseInt(timeParts[0]);
                    minutes = Integer.parseInt(timeParts[1]);
                } catch (NumberFormatException ex) {
                    outputLabel.setText("Error: Invalid numbers.");
                    return;
                }

                String resultHours = "";
                if (hours >= 12) {
                    resultHours = (hours - 12) + "";
                } else {
                    resultHours = hours + "";
                }

                if (hours == 0) {
                    resultHours = "12";
                }

                if (minutes < 10) {
                    outputLabel.setText("Result: " + resultHours + ":0" + minutes + " pm");
                } else {
                    outputLabel.setText("Result: " + resultHours + ":" + minutes + " pm");
                }
            }
        });
        panel.add(dayOfWeekButton);
        panel.add(leapYearButton);
        panel.add(timeConverterButton);
        frame.setVisible(true);
    }
}

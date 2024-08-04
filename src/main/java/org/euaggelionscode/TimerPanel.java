package org.euaggelionscode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class TimerPanel extends JPanel {
    private JLabel timerLabel;
    private JTextField timerInputField;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private long timerStartTime;
    private long timerDuration;
    private Timer timer;

    public TimerPanel() {
        timerLabel = new JLabel("Timer: 00:00:00", SwingConstants.CENTER);
        timerInputField = new JTextField(10);
        timerInputField.setText("Enter seconds");
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        add(timerLabel);
        add(timerInputField);
        add(startButton);
        add(stopButton);
        add(resetButton);

        timer = new Timer();

        startButton.addActionListener(e -> startTimer());
        stopButton.addActionListener(e -> stopTimer());
        resetButton.addActionListener(e -> resetTimer());
    }

    private void startTimer() {
        try {
            String inputText = timerInputField.getText();
            if ("Enter seconds".equals(inputText) || inputText.isEmpty()) {
                showError("Please enter a valid number.");
                return;
            }
            timerDuration = Long.parseLong(inputText) * 1000;
            timerStartTime = System.currentTimeMillis();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    updateTimer();
                }
            }, 0, 1000);
        } catch (NumberFormatException ex) {
            showError("Please enter a valid number.");
        }
    }

    private void stopTimer() {
        timer.cancel();
    }

    private void resetTimer() {
        stopTimer();
        timerLabel.setText("Timer: 00:00:00");
    }

    private void updateTimer() {
        long elapsedMillis = System.currentTimeMillis() - timerStartTime;
        long remainingMillis = timerDuration - elapsedMillis;
        if (remainingMillis <= 0) {
            timerLabel.setText("Timer: 00:00:00");
            stopTimer();
        } else {
            long seconds = (remainingMillis / 1000) % 60;
            long minutes = (remainingMillis / (1000 * 60)) % 60;
            long hours = (remainingMillis / (1000 * 60 * 60)) % 24;
            timerLabel.setText(String.format("Timer: %02d:%02d:%02d", hours, minutes, seconds));
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
}

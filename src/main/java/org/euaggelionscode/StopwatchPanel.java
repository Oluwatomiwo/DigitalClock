package org.euaggelionscode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StopwatchPanel extends JPanel {
    private JLabel stopwatchLabel;
    private JButton startButton;
    private JButton stopButton;
    private JButton resetButton;
    private long stopwatchStartTime;
    private Timer timer;

    public StopwatchPanel() {
        stopwatchLabel = new JLabel("Stopwatch: 00:00:00", SwingConstants.CENTER);
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        add(stopwatchLabel);
        add(startButton);
        add(stopButton);
        add(resetButton);

        timer = new Timer();

        startButton.addActionListener(e -> startStopwatch());
        stopButton.addActionListener(e -> stopStopwatch());
        resetButton.addActionListener(e -> resetStopwatch());
    }

    private void startStopwatch() {
        stopwatchStartTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateStopwatch();
            }
        }, 0, 1000);
    }

    private void stopStopwatch() {
        timer.cancel();
        timer = new Timer();
    }

    private void resetStopwatch() {
        stopwatchLabel.setText("Stopwatch: 00:00:00");
        stopStopwatch();
    }

    private void updateStopwatch() {
        long elapsedMillis = System.currentTimeMillis() - stopwatchStartTime;
        long hours = (elapsedMillis / 3600000) % 24;
        long minutes = (elapsedMillis / 60000) % 60;
        long seconds = (elapsedMillis / 1000) % 60;
        stopwatchLabel.setText(String.format("Stopwatch: %02d:%02d:%02d", hours, minutes, seconds));
    }
}

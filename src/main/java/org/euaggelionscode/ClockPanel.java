package org.euaggelionscode;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ClockPanel extends JPanel {
    private JLabel timeLabel;
    private Timer timer;
    private TimeZoneProvider timeZoneProvider;

    public ClockPanel(TimeZoneProvider timeZoneProvider) {
        this.timeZoneProvider = timeZoneProvider;
        timeLabel = new JLabel("", SwingConstants.CENTER);
        add(timeLabel);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateClock();
            }
        }, 0, 1000);
    }

    void updateClock() {
        ZoneId zoneId = timeZoneProvider.getZoneId();
        LocalDateTime now = LocalDateTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText("Time: " + now.format(formatter));
    }
}

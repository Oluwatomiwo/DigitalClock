package org.euaggelionscode;

import javax.swing.*;
import java.awt.*;

public class DigitalClock {
    private JFrame frame;
    private ClockPanel clockPanel;
    private StopwatchPanel stopwatchPanel;
    private TimerPanel timerPanel;
    private JComboBox<String> timeZoneComboBox;

    public DigitalClock() {
        frame = new JFrame("Digital Clock");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        String[] timeZones = {"Africa/Lagos", "America/New_York", "Europe/London", "Asia/Tokyo"};
        timeZoneComboBox = new JComboBox<>(timeZones);
        TimeZoneProvider timeZoneProvider = new ComboBoxTimeZoneProvider(timeZoneComboBox);

        timeZoneComboBox.addActionListener(e -> clockPanel.updateClock());

        clockPanel = new ClockPanel(timeZoneProvider);
        stopwatchPanel = new StopwatchPanel();
        timerPanel = new TimerPanel();

        frame.add(clockPanel);
        frame.add(timeZoneComboBox);
        frame.add(stopwatchPanel);
        frame.add(timerPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClock::new);
    }
}

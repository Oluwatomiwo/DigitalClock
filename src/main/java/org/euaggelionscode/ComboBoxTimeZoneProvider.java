package org.euaggelionscode;

import javax.swing.*;
import java.time.ZoneId;

public class ComboBoxTimeZoneProvider implements TimeZoneProvider {
    private JComboBox<String> comboBox;

    public ComboBoxTimeZoneProvider(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    @Override
    public ZoneId getZoneId() {
        return ZoneId.of((String) comboBox.getSelectedItem());
    }
}


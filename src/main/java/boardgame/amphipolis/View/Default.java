package boardgame.amphipolis.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * This is a class that contains functions that sets a default text in JTextfields
 */

public class Default {
    public static void enableDefaultValue(final String defaultValue,final JTextField text ) {
        // Set current value
        text.setText(defaultValue);
        text.setForeground(Color.gray);

        // Add listener
        text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (text.getText().equals(defaultValue)) {
                    text.setForeground(Color.black);
                    text.setText("");
                }
                super.focusGained(e);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (text.getText().equals("")) {
                    text.setForeground(Color.gray);
                    text.setText(defaultValue);
                }
                super.focusLost(e);
            }
        });
    }
}

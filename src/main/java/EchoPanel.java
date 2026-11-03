import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** READ FIRST
 * Echo: a text field, a button and a label that repeats what was typed.
 *
 *   field text   after clicking "Echo", the label says
 *   ----------   ----------------------------------------
 *   "hi"         "You typed: hi"
 *   "hello world" "You typed: hello world"
 *   ""           "You typed nothing"
 *
 * Before the first click the label says "waiting...".
 *
 * field.getText() returns a String, no matter what was typed. Compare it to
 * "" with .equals, never with == (that is a String, and Strings are compared
 * with equals - the one place == is right for objects is e.getSource(),
 * which you will meet in TrafficLightPanel).
 */
public class EchoPanel extends JPanel implements ActionListener {

    // Declared for you; all null until the constructor runs new.
    private JTextField field;
    private JButton button;
    private JLabel label;

    /** COMPLETE THIS METHOD
     * Builds the field (new JTextField(12) - 12 columns wide), the button
     * ("Echo") and the label ("waiting..."); adds all three in that order;
     * wires the button to this listener.
     * Example: new EchoPanel().getLabel().getText() is "waiting..." and
     * getField().getText() is "".
     */
    public EchoPanel() {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Reads the field. If its text is "" (nothing typed) the label becomes
     * "You typed nothing"; otherwise it becomes "You typed: " followed by
     * the text.
     * Example: field "hi" -> label "You typed: hi"
     */
    public void actionPerformed(ActionEvent e) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Returns the text field, so the tests can type into it.
     */
    public JTextField getField() {
        // Insert your code below

        return null;
    }

    /** COMPLETE THIS METHOD
     * Returns the button, so the tests can click it.
     */
    public JButton getButton() {
        // Insert your code below

        return null;
    }

    /** COMPLETE THIS METHOD
     * Returns the label, so the tests can read it.
     */
    public JLabel getLabel() {
        // Insert your code below

        return null;
    }
}

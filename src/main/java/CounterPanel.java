import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** READ FIRST
 * The click counter from lecture 2.10: a button, a label, and a count that
 * goes up. This is the first program you write that WAITS - main builds the
 * window and ends, and the program keeps running until someone clicks.
 *
 * Three pieces, all required:
 *   1. the button          - new JButton("Click me")
 *   2. the listener        - this panel; "implements ActionListener" is a
 *                            promise that it has actionPerformed
 *   3. the wire            - button.addActionListener(this);
 *                            "button, when you are clicked, tell THIS object"
 * Leave out the wire and everything compiles, the button highlights, and
 * nothing ever happens. No error message. That is the #1 bug this week.
 *
 * Trace: click 1 -> count 0 -> 1, label "Clicks: 1"
 *        click 2 -> count 1 -> 2, label "Clicks: 2"
 * Between clicks the count is still sitting in the object - because it is a
 * FIELD. Declare it inside actionPerformed and it is reborn as 0 on every
 * click; the label says "Clicks: 1" forever.
 *
 * JPanel already uses FlowLayout, so add(button); add(label); is all the
 * layout you need.
 */
public class CounterPanel extends JPanel implements ActionListener {

    // Declared for you: the two components. Both are null until the
    // constructor runs new.
    private JButton button;
    private JLabel label;

    // Declare the click counter here - a FIELD, so it survives between clicks.


    /** COMPLETE THIS METHOD
     * Builds the button ("Click me") and the label ("Clicks: 0"), adds both
     * to this panel, and wires the button to this listener.
     * Example: new CounterPanel().getLabel().getText() is "Clicks: 0" and
     * getCount() is 0.
     * Hint: build, add, wire. Three lines for the button, two for the label.
     */
    public CounterPanel() {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Called by Swing once per click - never by you. Adds one to the count
     * and updates the label to "Clicks: " + count. setText redraws the label
     * for you.
     * Example: after the third click the label reads "Clicks: 3".
     */
    public void actionPerformed(ActionEvent e) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Returns how many clicks so far.
     */
    public int getCount() {
        // Insert your code below

        return 0;
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

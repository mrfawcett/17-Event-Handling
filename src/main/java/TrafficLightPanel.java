import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** READ FIRST
 * A traffic light with two buttons. The state is one String field that is
 * always exactly "RED", "GREEN" or "YELLOW".
 *
 *   button   from      to
 *   -------  --------  --------
 *   Next     "RED"     "GREEN"
 *   Next     "GREEN"   "YELLOW"
 *   Next     "YELLOW"  "RED"
 *   Reset    anything  "RED"
 *
 * It starts at "RED". The label always reads "Light: " + state.
 *
 * TWO BUTTONS, ONE LISTENER. Both buttons are wired to this panel, so
 * actionPerformed runs for either click and has to ask which button it was:
 *
 *     if (e.getSource() == nextButton) { ... }
 *     else if (e.getSource() == resetButton) { ... }
 *
 * e.getSource() returns the component that was clicked. Here == is CORRECT:
 * you are asking "is it literally that button object?" - an identity
 * question. Compare the STATE with .equals, as always: state.equals("RED").
 *
 * The paintComponent below is PROVIDED. It draws three lamps and lights the
 * one that matches getState(). It only runs when Swing repaints the panel -
 * so after you change the state, call repaint(), or the lamp on screen stays
 * stale while the label (which repaints itself) moves on. Data changed, screen
 * not: that is the "forgot repaint()" bug, and it leaves no error message.
 */
public class TrafficLightPanel extends JPanel implements ActionListener {

    // Declared for you; null until the constructor runs new.
    private JButton nextButton;
    private JButton resetButton;
    private JLabel label;

    // Declare the state String here - a FIELD. It starts as "RED".


    /** COMPLETE THIS METHOD
     * Sets the state to "RED"; builds the two buttons ("Next" and "Reset")
     * and the label ("Light: RED"); adds all three; wires BOTH buttons to
     * this listener.
     * Example: new TrafficLightPanel().getState() is "RED".
     * Hint: two addActionListener lines. Wire one and not the other and half
     * the tests fail with no error message anywhere.
     */
    public TrafficLightPanel() {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * If the source is nextButton, advances the state RED -> GREEN -> YELLOW
     * -> RED. If the source is resetButton, sets the state to "RED". Either
     * way, sets the label to "Light: " + state and calls repaint().
     * Example: fresh panel, Next, Next -> "YELLOW"; then Reset -> "RED".
     */
    public void actionPerformed(ActionEvent e) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Returns the current state: "RED", "GREEN" or "YELLOW".
     */
    public String getState() {
        // Insert your code below

        return "";
    }

    /** COMPLETE THIS METHOD
     * Returns the Next button, so the tests can click it.
     */
    public JButton getNextButton() {
        // Insert your code below

        return null;
    }

    /** COMPLETE THIS METHOD
     * Returns the Reset button, so the tests can click it.
     */
    public JButton getResetButton() {
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

    /** PROVIDED - do not change
     * Room for the buttons on top and three lamps below.
     */
    public Dimension getPreferredSize() {
        return new Dimension(220, 320);
    }

    /** PROVIDED - do not change
     * Draws the housing and three lamps; the lamp matching getState() is lit,
     * the other two are dark. This runs only when Swing repaints the panel -
     * which is why actionPerformed must call repaint().
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        String s = getState();

        g.setColor(Color.DARK_GRAY);                 // the housing
        g.fillRect(70, 60, 80, 220);

        if ("RED".equals(s)) {                       // top lamp
            g.setColor(Color.RED);
        } else {
            g.setColor(new Color(90, 30, 30));
        }
        g.fillOval(85, 75, 50, 50);

        if ("YELLOW".equals(s)) {                    // middle lamp
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(new Color(90, 90, 30));
        }
        g.fillOval(85, 145, 50, 50);

        if ("GREEN".equals(s)) {                     // bottom lamp
            g.setColor(Color.GREEN);
        } else {
            g.setColor(new Color(30, 90, 30));
        }
        g.fillOval(85, 215, 50, 50);
    }
}

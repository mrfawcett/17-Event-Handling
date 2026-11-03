import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/** READ FIRST
 * A panel whose background cycles through three colours, one step per click:
 *
 *   colorIndex   background
 *   ----------   ----------
 *       0        Color.RED      <- start here
 *       1        Color.GREEN
 *       2        Color.BLUE
 *       0        Color.RED      <- and around again
 *
 * The state is ONE int field, colorIndex. Each click moves it forward one
 * step, wrapping 2 -> 0, and then the background is set to match. The
 * lecture's version of this used a random colour; this one is predictable so
 * the autograder can check it.
 *
 * setBackground(Color) is a JPanel method (you inherit it). It repaints the
 * panel for you, the same way setText repaints a JLabel - so there is no
 * repaint() call needed here. Arrays are Unit 3: an if / else if / else on
 * colorIndex is all you need.
 */
public class ColorPanel extends JPanel implements ActionListener {

    // Declared for you; null until the constructor runs new.
    private JButton button;

    // Declare colorIndex here - a FIELD, so it survives between clicks.


    /** COMPLETE THIS METHOD
     * Builds the button ("Next color"), adds it to the panel, wires it to
     * this listener, sets colorIndex to 0 and the background to Color.RED.
     * Example: new ColorPanel().getBackground() is Color.RED and
     * getColorIndex() is 0.
     */
    public ColorPanel() {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Advances colorIndex one step (0 -> 1 -> 2 -> 0) and sets the background
     * to the matching colour: 0 RED, 1 GREEN, 2 BLUE.
     * Example: on a fresh panel, one click gives index 1 and a GREEN
     * background; three clicks bring it back to 0 and RED.
     * Hint: colorIndex = (colorIndex + 1) % 3;
     */
    public void actionPerformed(ActionEvent e) {
        // Insert your code below

    }

    /** COMPLETE THIS METHOD
     * Returns the current index, 0, 1 or 2.
     */
    public int getColorIndex() {
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
}

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * PROVIDED - you do not need to change this file.
 *
 * Puts all four panels in one window, two by two, so you can click on them.
 * GridLayout(2, 2, 10, 10) is a layout that gives each panel an equal cell
 * with a 10-pixel gutter; it is the only thing here that is not the 2.9
 * template.
 *
 * Notice what happens when you run it: main reaches its last line and ENDS,
 * and the window is still there, waiting. Swing is sitting in a loop watching
 * for clicks, and every click calls one of your actionPerformed methods.
 *
 * Guarded by GraphicsEnvironment.isHeadless() so it does nothing on a machine
 * without a screen. The autograder never runs Main anyway - it constructs your
 * panels directly, presses the buttons with doClick(), and reads the getters.
 */
public class Main {
    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("No screen here - run the tests instead: mvn test");
            return;
        }

        JFrame frame = new JFrame("Event Handling");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2, 10, 10));

        frame.add(new CounterPanel());
        frame.add(new ColorPanel());
        frame.add(new EchoPanel());
        frame.add(new TrafficLightPanel());

        frame.setSize(700, 720);
        frame.setVisible(true);          // last, always
    }
}

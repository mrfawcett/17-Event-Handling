import javax.swing.JButton;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CounterPanelTest {
    static { System.setProperty("java.awt.headless", "true"); }

    /** Presses the button n times, exactly as a user would (fires actionPerformed each time). */
    private static void click(JButton b, int n) {
        for (int i = 0; i < n; i++) b.doClick(0);
    }

    @DisplayName("CounterPanel: a new panel has count 0 and the label reads \"Clicks: 0\"")
    @Test
    void counter_Test01() {
        CounterPanel p = new CounterPanel();
        assertEquals(0, p.getCount(), "count should start at 0");
        assertNotNull(p.getLabel(), "getLabel() is null - construct the label in the constructor");
        assertEquals("Clicks: 0", p.getLabel().getText(), "the label should start as \"Clicks: 0\"");
    }

    @DisplayName("CounterPanel: the button exists and says \"Click me\"")
    @Test
    void counter_Test02() {
        CounterPanel p = new CounterPanel();
        assertNotNull(p.getButton(), "getButton() is null - construct the button in the constructor and return it");
        assertEquals("Click me", p.getButton().getText(), "the button's text is its constructor argument");
    }

    @DisplayName("CounterPanel: the button and the label are both ADDED to the panel")
    @Test
    void counter_Test03() {
        CounterPanel p = new CounterPanel();
        assertNotNull(p.getButton(), "getButton() is null");
        assertNotNull(p.getLabel(), "getLabel() is null");
        assertTrue(p.isAncestorOf(p.getButton()), "the button was built but never add()-ed to the panel");
        assertTrue(p.isAncestorOf(p.getLabel()), "the label was built but never add()-ed to the panel");
    }

    @DisplayName("CounterPanel: the button has an ActionListener - button.addActionListener(this)")
    @Test
    void counter_Test04() {
        CounterPanel p = new CounterPanel();
        assertNotNull(p.getButton(), "getButton() is null");
        assertTrue(p.getButton().getActionListeners().length >= 1,
                "nothing is listening to the button. A button with no listener is a picture of a button.");
    }

    @DisplayName("CounterPanel: one click -> count 1, label \"Clicks: 1\"")
    @Test
    void counter_Test05() {
        CounterPanel p = new CounterPanel();
        click(p.getButton(), 1);
        assertEquals(1, p.getCount(), "count after one click");
        assertEquals("Clicks: 1", p.getLabel().getText(), "label after one click - setText(\"Clicks: \" + count)");
    }

    @DisplayName("CounterPanel: two clicks -> \"Clicks: 2\" (a count declared INSIDE actionPerformed is stuck on 1)")
    @Test
    void counter_Test06() {
        CounterPanel p = new CounterPanel();
        click(p.getButton(), 2);
        assertEquals(2, p.getCount(), "count after two clicks - if this is 1, your counter is a local variable, not a field");
        assertEquals("Clicks: 2", p.getLabel().getText(), "label after two clicks");
    }

    @DisplayName("CounterPanel: ten clicks -> count 10, label \"Clicks: 10\"")
    @Test
    void counter_Test07() {
        CounterPanel p = new CounterPanel();
        click(p.getButton(), 10);
        assertEquals(10, p.getCount(), "count after ten clicks");
        assertEquals("Clicks: 10", p.getLabel().getText(), "label after ten clicks");
    }

    @DisplayName("CounterPanel: the label updates on EVERY click, not just the first")
    @Test
    void counter_Test08() {
        CounterPanel p = new CounterPanel();
        for (int i = 1; i <= 4; i++) {
            click(p.getButton(), 1);
            assertEquals("Clicks: " + i, p.getLabel().getText(), "label after click " + i);
            assertEquals(i, p.getCount(), "count after click " + i);
        }
    }

    @DisplayName("CounterPanel: two panels count separately (3 clicks on one, 1 on the other)")
    @Test
    void counter_Test09() {
        CounterPanel a = new CounterPanel();
        CounterPanel b = new CounterPanel();
        click(a.getButton(), 3);
        click(b.getButton(), 1);
        assertEquals(3, a.getCount(), "panel a was clicked 3 times");
        assertEquals(1, b.getCount(), "panel b was clicked once - is your count static?");
        assertEquals("Clicks: 3", a.getLabel().getText(), "panel a label");
        assertEquals("Clicks: 1", b.getLabel().getText(), "panel b label");
    }
}

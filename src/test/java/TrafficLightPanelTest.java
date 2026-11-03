import javax.swing.JButton;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrafficLightPanelTest {
    static { System.setProperty("java.awt.headless", "true"); }

    private static void click(JButton b, int n) {
        for (int i = 0; i < n; i++) b.doClick(0);
    }

    @DisplayName("TrafficLightPanel: starts at \"RED\" with the label \"Light: RED\"")
    @Test
    void light_Test01() {
        TrafficLightPanel p = new TrafficLightPanel();
        assertEquals("RED", p.getState(), "the state starts as \"RED\"");
        assertNotNull(p.getLabel(), "getLabel() is null - construct the label and return it");
        assertEquals("Light: RED", p.getLabel().getText(), "initial label");
    }

    @DisplayName("TrafficLightPanel: the buttons say \"Next\" and \"Reset\" and both are added to the panel, with the label")
    @Test
    void light_Test02() {
        TrafficLightPanel p = new TrafficLightPanel();
        assertNotNull(p.getNextButton(), "getNextButton() is null");
        assertNotNull(p.getResetButton(), "getResetButton() is null");
        assertEquals("Next", p.getNextButton().getText(), "Next button text");
        assertEquals("Reset", p.getResetButton().getText(), "Reset button text");
        assertNotSame(p.getNextButton(), p.getResetButton());
        assertTrue(p.isAncestorOf(p.getNextButton()), "the Next button was never add()-ed to the panel");
        assertTrue(p.isAncestorOf(p.getResetButton()), "the Reset button was never add()-ed to the panel");
        assertTrue(p.isAncestorOf(p.getLabel()), "the label was never add()-ed to the panel");
    }

    @DisplayName("TrafficLightPanel: BOTH buttons have a listener (wiring one and not the other is silent)")
    @Test
    void light_Test03() {
        TrafficLightPanel p = new TrafficLightPanel();
        assertTrue(p.getNextButton().getActionListeners().length >= 1,
                "the Next button has no listener - nextButton.addActionListener(this)");
        assertTrue(p.getResetButton().getActionListeners().length >= 1,
                "the Reset button has no listener - resetButton.addActionListener(this)");
    }

    @DisplayName("TrafficLightPanel: Next once -> \"GREEN\", label \"Light: GREEN\"")
    @Test
    void light_Test04() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getNextButton(), 1);
        assertEquals("GREEN", p.getState(), "RED -> GREEN");
        assertEquals("Light: GREEN", p.getLabel().getText(), "the label follows the state");
    }

    @DisplayName("TrafficLightPanel: Next twice -> \"YELLOW\"")
    @Test
    void light_Test05() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getNextButton(), 2);
        assertEquals("YELLOW", p.getState(), "RED -> GREEN -> YELLOW");
        assertEquals("Light: YELLOW", p.getLabel().getText(), "label");
    }

    @DisplayName("TrafficLightPanel: Next three times wraps back to \"RED\"")
    @Test
    void light_Test06() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getNextButton(), 3);
        assertEquals("RED", p.getState(), "YELLOW -> RED (the cycle closes)");
        assertEquals("Light: RED", p.getLabel().getText(), "label");
    }

    @DisplayName("TrafficLightPanel: the cycle keeps going - 4 clicks GREEN, 7 clicks GREEN, 8 clicks YELLOW")
    @Test
    void light_Test07() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getNextButton(), 4);
        assertEquals("GREEN", p.getState(), "after 4 clicks");
        click(p.getNextButton(), 3);
        assertEquals("GREEN", p.getState(), "after 7 clicks");
        click(p.getNextButton(), 1);
        assertEquals("YELLOW", p.getState(), "after 8 clicks");
    }

    @DisplayName("TrafficLightPanel: Reset from \"YELLOW\" -> \"RED\", label \"Light: RED\"")
    @Test
    void light_Test08() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getNextButton(), 2);
        assertEquals("YELLOW", p.getState(), "setup: two Next clicks");
        click(p.getResetButton(), 1);
        assertEquals("RED", p.getState(), "Reset goes straight to RED");
        assertEquals("Light: RED", p.getLabel().getText(), "label after Reset");
    }

    @DisplayName("TrafficLightPanel: Reset from \"GREEN\" -> \"RED\" (Reset is not another Next)")
    @Test
    void light_Test09() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getNextButton(), 1);
        assertEquals("GREEN", p.getState(), "setup: one Next click");
        click(p.getResetButton(), 1);
        assertEquals("RED", p.getState(), "Reset from GREEN must give RED, not YELLOW - check e.getSource() to tell the buttons apart");
    }

    @DisplayName("TrafficLightPanel: Reset while already \"RED\" stays \"RED\"; pressing it twice changes nothing")
    @Test
    void light_Test10() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getResetButton(), 2);
        assertEquals("RED", p.getState(), "Reset on RED is still RED");
        assertEquals("Light: RED", p.getLabel().getText(), "label");
    }

    @DisplayName("TrafficLightPanel: Next after Reset starts the cycle over -> \"GREEN\"")
    @Test
    void light_Test11() {
        TrafficLightPanel p = new TrafficLightPanel();
        click(p.getNextButton(), 2);
        click(p.getResetButton(), 1);
        click(p.getNextButton(), 1);
        assertEquals("GREEN", p.getState(), "RED (after Reset) -> GREEN");
        assertEquals("Light: GREEN", p.getLabel().getText(), "label");
    }

    @DisplayName("TrafficLightPanel: two panels keep separate states")
    @Test
    void light_Test12() {
        TrafficLightPanel a = new TrafficLightPanel();
        TrafficLightPanel b = new TrafficLightPanel();
        click(a.getNextButton(), 2);
        assertEquals("YELLOW", a.getState(), "panel a after two Next clicks");
        assertEquals("RED", b.getState(), "panel b was never clicked - is state static?");
    }
}

import java.awt.Color;
import javax.swing.JButton;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ColorPanelTest {
    static { System.setProperty("java.awt.headless", "true"); }

    private static void click(JButton b, int n) {
        for (int i = 0; i < n; i++) b.doClick(0);
    }

    @DisplayName("ColorPanel: starts RED with colorIndex 0")
    @Test
    void color_Test01() {
        ColorPanel p = new ColorPanel();
        assertEquals(Color.RED, p.getBackground(), "the constructor should call setBackground(Color.RED)");
        assertEquals(0, p.getColorIndex(), "colorIndex starts at 0");
    }

    @DisplayName("ColorPanel: the button exists, is added to the panel, and has a listener")
    @Test
    void color_Test02() {
        ColorPanel p = new ColorPanel();
        assertNotNull(p.getButton(), "getButton() is null - construct the button and return it");
        assertTrue(p.isAncestorOf(p.getButton()), "the button was built but never add()-ed to the panel");
        assertTrue(p.getButton().getActionListeners().length >= 1,
                "nothing is listening to the button - button.addActionListener(this)");
    }

    @DisplayName("ColorPanel: one click -> GREEN, colorIndex 1")
    @Test
    void color_Test03() {
        ColorPanel p = new ColorPanel();
        click(p.getButton(), 1);
        assertEquals(1, p.getColorIndex(), "index after one click");
        assertEquals(Color.GREEN, p.getBackground(), "background after one click");
    }

    @DisplayName("ColorPanel: two clicks -> BLUE, colorIndex 2")
    @Test
    void color_Test04() {
        ColorPanel p = new ColorPanel();
        click(p.getButton(), 2);
        assertEquals(2, p.getColorIndex(), "index after two clicks - stuck on 1 means colorIndex is a local variable");
        assertEquals(Color.BLUE, p.getBackground(), "background after two clicks");
    }

    @DisplayName("ColorPanel: three clicks wrap around -> RED, colorIndex 0")
    @Test
    void color_Test05() {
        ColorPanel p = new ColorPanel();
        click(p.getButton(), 3);
        assertEquals(0, p.getColorIndex(), "index after three clicks should wrap to 0: (colorIndex + 1) % 3");
        assertEquals(Color.RED, p.getBackground(), "background after three clicks");
    }

    @DisplayName("ColorPanel: seven clicks -> GREEN, colorIndex 1 (7 % 3 = 1)")
    @Test
    void color_Test06() {
        ColorPanel p = new ColorPanel();
        click(p.getButton(), 7);
        assertEquals(1, p.getColorIndex(), "index after seven clicks");
        assertEquals(Color.GREEN, p.getBackground(), "background after seven clicks");
    }

    @DisplayName("ColorPanel: the background and the index agree after every one of six clicks")
    @Test
    void color_Test07() {
        ColorPanel p = new ColorPanel();
        Color[] expected = {Color.GREEN, Color.BLUE, Color.RED, Color.GREEN, Color.BLUE, Color.RED};
        for (int i = 0; i < 6; i++) {
            click(p.getButton(), 1);
            assertEquals((i + 1) % 3, p.getColorIndex(), "index after click " + (i + 1));
            assertEquals(expected[i], p.getBackground(), "background after click " + (i + 1));
        }
    }

    @DisplayName("ColorPanel: two panels cycle independently")
    @Test
    void color_Test08() {
        ColorPanel a = new ColorPanel();
        ColorPanel b = new ColorPanel();
        click(a.getButton(), 2);
        assertEquals(2, a.getColorIndex(), "panel a after two clicks");
        assertEquals(0, b.getColorIndex(), "panel b was never clicked - is colorIndex static?");
        assertEquals(Color.BLUE, a.getBackground(), "panel a background");
        assertEquals(Color.RED, b.getBackground(), "panel b background");
    }
}

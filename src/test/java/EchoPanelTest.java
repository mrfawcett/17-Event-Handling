import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EchoPanelTest {
    static { System.setProperty("java.awt.headless", "true"); }

    @DisplayName("EchoPanel: starts with the label \"waiting...\" and an empty field")
    @Test
    void echo_Test01() {
        EchoPanel p = new EchoPanel();
        assertNotNull(p.getLabel(), "getLabel() is null - construct the label and return it");
        assertNotNull(p.getField(), "getField() is null - construct the field and return it");
        assertEquals("waiting...", p.getLabel().getText(), "initial label text");
        assertEquals("", p.getField().getText(), "a new JTextField is empty");
    }

    @DisplayName("EchoPanel: the button says \"Echo\"; field, button and label are all added to the panel; the button is wired")
    @Test
    void echo_Test02() {
        EchoPanel p = new EchoPanel();
        assertNotNull(p.getButton(), "getButton() is null");
        assertEquals("Echo", p.getButton().getText(), "button text");
        assertTrue(p.isAncestorOf(p.getField()), "the field was never add()-ed to the panel");
        assertTrue(p.isAncestorOf(p.getButton()), "the button was never add()-ed to the panel");
        assertTrue(p.isAncestorOf(p.getLabel()), "the label was never add()-ed to the panel");
        assertTrue(p.getButton().getActionListeners().length >= 1,
                "nothing is listening to the button - button.addActionListener(this)");
    }

    @DisplayName("EchoPanel: type \"hi\", click -> \"You typed: hi\"")
    @Test
    void echo_Test03() {
        EchoPanel p = new EchoPanel();
        p.getField().setText("hi");
        p.getButton().doClick(0);
        assertEquals("You typed: hi", p.getLabel().getText(), "label after echoing \"hi\" (note the space after the colon)");
    }

    @DisplayName("EchoPanel: empty field, click -> \"You typed nothing\"")
    @Test
    void echo_Test04() {
        EchoPanel p = new EchoPanel();
        p.getField().setText("");
        p.getButton().doClick(0);
        assertEquals("You typed nothing", p.getLabel().getText(), "an empty field gets the special message - compare with .equals(\"\")");
    }

    @DisplayName("EchoPanel: clicking a brand-new panel without typing -> \"You typed nothing\"")
    @Test
    void echo_Test05() {
        EchoPanel p = new EchoPanel();
        p.getButton().doClick(0);
        assertEquals("You typed nothing", p.getLabel().getText(), "nothing typed yet");
    }

    @DisplayName("EchoPanel: text with a space, \"hello world\" -> \"You typed: hello world\"")
    @Test
    void echo_Test06() {
        EchoPanel p = new EchoPanel();
        p.getField().setText("hello world");
        p.getButton().doClick(0);
        assertEquals("You typed: hello world", p.getLabel().getText(), "the whole text, spaces included");
    }

    @DisplayName("EchoPanel: digits are still text - \"42\" -> \"You typed: 42\"")
    @Test
    void echo_Test07() {
        EchoPanel p = new EchoPanel();
        p.getField().setText("42");
        p.getButton().doClick(0);
        assertEquals("You typed: 42", p.getLabel().getText(), "getText() returns a String; no parseInt here");
    }

    @DisplayName("EchoPanel: the field is read on EVERY click - \"a\" then \"b\" -> \"You typed: b\"")
    @Test
    void echo_Test08() {
        EchoPanel p = new EchoPanel();
        p.getField().setText("a");
        p.getButton().doClick(0);
        assertEquals("You typed: a", p.getLabel().getText(), "first echo");
        p.getField().setText("b");
        p.getButton().doClick(0);
        assertEquals("You typed: b", p.getLabel().getText(), "second echo must read the field again, not reuse the old text");
    }

    @DisplayName("EchoPanel: type, clear, click -> back to \"You typed nothing\"")
    @Test
    void echo_Test09() {
        EchoPanel p = new EchoPanel();
        p.getField().setText("something");
        p.getButton().doClick(0);
        assertEquals("You typed: something", p.getLabel().getText(), "first echo");
        p.getField().setText("");
        p.getButton().doClick(0);
        assertEquals("You typed nothing", p.getLabel().getText(), "after clearing the field the label should say nothing was typed");
    }
}

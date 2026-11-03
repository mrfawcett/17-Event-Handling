# Event Handling

**Unit 2 — Graphics, GUI & Applied Class Design** · Pairs with lecture 2.10 Event Handling

Every program you have written since August ran top to bottom and ended when
`main` ended. Today that stops being true. `main` builds a window and ends —
and the program is still running, sitting in a loop, waiting for a click.
When one comes, Swing calls *your* method. You are no longer writing the
story; you are writing what happens when somebody does something.

You will write four panels that react: a click counter, a colour cycler, an
echo box, and a traffic light with two buttons. Only two lines in the whole
assignment are new this week — `implements ActionListener` and
`addActionListener` — and the bugs are all about the same three things:
*wire the button, keep the state in a field, then redraw.*

## What you are given

| File | Status | Purpose |
|---|---|---|
| `src/main/java/CounterPanel.java` | **you complete this** | a button, a label, a count that goes up |
| `src/main/java/ColorPanel.java` | **you complete this** | a button that cycles the background RED → GREEN → BLUE |
| `src/main/java/EchoPanel.java` | **you complete this** | a text field, a button, a label that repeats the text |
| `src/main/java/TrafficLightPanel.java` | **you complete this** | two buttons, one listener, a state String; the lamp drawing is provided |
| `src/main/java/Main.java` | provided | puts all four panels in one window when there is a screen |
| `src/test/java/*Test.java` | provided | the autograder's tests — read them |
| `pom.xml`, `grading.json` | provided | build and grading setup — do not edit |

The component fields (`JButton`, `JLabel`, `JTextField`) are declared for you
and start as `null` — construct each one in the constructor. The **state**
fields (`count`, `colorIndex`, `state`) are *not* declared. You declare them,
and where you declare them is the lesson.

## What to write

| Class | Points | Behaviour |
|---|---|---|
| `CounterPanel` | 30 | `"Click me"` button, `"Clicks: 0"` label; each click adds one and sets the label to `"Clicks: N"` |
| `ColorPanel` | 20 | `"Next color"` button; background cycles RED (0) → GREEN (1) → BLUE (2) → RED (0) |
| `EchoPanel` | 20 | `JTextField(12)`, `"Echo"` button, `"waiting..."` label; click → `"You typed: <text>"`, empty → `"You typed nothing"` |
| `TrafficLightPanel` | 30 | `"Next"` cycles `"RED"` → `"GREEN"` → `"YELLOW"` → `"RED"`; `"Reset"` → `"RED"`; label `"Light: <state>"` |

Every panel has the same skeleton, which is the lecture's `Counter` with the
`JFrame` moved out to `Main`:

```java
public class CounterPanel extends JPanel implements ActionListener {
    private JButton button;                 // declared for you
    private JLabel label;                   // declared for you
    private int count;                      // YOU declare the state - as a FIELD

    public CounterPanel() {
        button = new JButton("Click me");   // 1. build
        label = new JLabel("Clicks: 0");
        add(button);                        // 2. add   (JPanel is already FlowLayout)
        add(label);
        button.addActionListener(this);     // 3. wire
    }

    public void actionPerformed(ActionEvent e) {   // Swing calls this. You never do.
        count++;
        label.setText("Clicks: " + count);
    }
    // getters: getCount(), getButton(), getLabel()
}
```

Build, add, wire. Three pieces, all required. `implements ActionListener` is
a promise that this class has `public void actionPerformed(ActionEvent e)` —
leave the method out and it will not compile; misspell it and it will not
compile either, which is a mercy. Leave out `addActionListener` and
*everything* compiles, the button highlights when you hover, and nothing ever
happens. No error message. That is the #1 bug of the next two weeks.

### `CounterPanel`

The count is a **field**. Declare it inside `actionPerformed` and it is
created fresh — as 0 — on every click, so `0 + 1` is 1 every single time and
the label says `"Clicks: 1"` forever. There is a test that clicks twice and
expects `"Clicks: 2"`. `label.setText(...)` redraws the label for you.

### `ColorPanel`

One `int` field, `colorIndex`, starting at 0 with the background `Color.RED`
(the constructor calls `setBackground(Color.RED)`). Each click:

```java
colorIndex = (colorIndex + 1) % 3;
if (colorIndex == 0) { setBackground(Color.RED); }
else if (colorIndex == 1) { setBackground(Color.GREEN); }
else { setBackground(Color.BLUE); }
```

`setBackground` is inherited from `JPanel` and repaints for you. Arrays are
Unit 3; the `if` chain is all you need.

### `EchoPanel`

Add the field, then the button, then the label. On click read
`field.getText()` — it is always a `String` — and set the label:

| field text | label after click |
|---|---|
| `"hi"` | `"You typed: hi"` |
| `"hello world"` | `"You typed: hello world"` |
| `"42"` | `"You typed: 42"` — still text, no `parseInt` |
| `""` | `"You typed nothing"` |

Compare to `""` with `.equals`, never `==`. Read the field on *every* click —
the tests type `"a"`, click, type `"b"`, click, and expect `"You typed: b"`.

### `TrafficLightPanel` — two buttons, one listener

Both buttons are wired to `this`, so the same `actionPerformed` runs for
either one and has to ask *which*:

```java
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == nextButton) {            // WHICH button? identity -> ==
        if (state.equals("RED")) { state = "GREEN"; }        // WHAT state? value -> equals
        else if (state.equals("GREEN")) { state = "YELLOW"; }
        else { state = "RED"; }
    } else if (e.getSource() == resetButton) {
        state = "RED";
    }
    label.setText("Light: " + state);
    repaint();                                    // the lamps live in paintComponent
}
```

`e.getSource()` returns the component that was clicked. This is the one place
all year where `==` on objects is right: you are asking "is it literally that
button?", an identity question. The state is a `String`, so it is compared
with `.equals`, as always.

The other way to do two buttons is two separate listener objects, one per
button, each with its own `actionPerformed` — a second class that
`implements ActionListener`. Both designs are correct and both are common in
real code. Here the panel is already the listener, so use `getSource()`; you
will meet the two-class version in January when interfaces get their own
lecture.

The provided `paintComponent` draws three lamps and lights the one matching
`getState()`. It runs only when Swing repaints the panel. Change the state
without calling `repaint()` and the *label* moves on (it repaints itself) while
the *lamp* stays stale — data changed, screen not. The autograder cannot see
your screen, so it cannot catch a missing `repaint()`; run `Main` and click.

## Examples

```
CounterPanel      new -> count 0, "Clicks: 0"
                  click x3 -> count 3, "Clicks: 3"
ColorPanel        new -> RED, index 0 ; click -> GREEN, 1 ; click -> BLUE, 2 ; click -> RED, 0
                  7 clicks from new -> GREEN, 1
EchoPanel         setText("hi"), click -> "You typed: hi"
                  setText(""),   click -> "You typed nothing"
TrafficLight      new -> "RED" ; Next -> "GREEN" ; Next -> "YELLOW" ; Next -> "RED"
                  Next, Next, Reset -> "RED" ; then Next -> "GREEN"
```

## Running the tests

`mvn test` runs everything; `mvn test -Dtest=CounterPanelTest` runs one rubric line.

| Test class | Rubric line | Points |
|---|---|---|
| `CounterPanelTest` | CounterPanel: button, label, count that survives clicks | 30 |
| `ColorPanelTest` | ColorPanel: background cycles RED, GREEN, BLUE | 20 |
| `EchoPanelTest` | EchoPanel: text field to label | 20 |
| `TrafficLightPanelTest` | TrafficLightPanel: two buttons, one listener | 30 |

The autograder awards a rubric line only when **every** test in that class
passes. The tests never open a window: they construct your panel, press your
button with `getButton().doClick()` exactly as a user would (for `EchoPanel`
they call `getField().setText("hi")` first), and then read your getters and
the label's text. They also check that each button actually *has* a listener
and that every component was `add`-ed to the panel — the two silent bugs.

## Suggested order

1. `CounterPanel`. Build, add, wire, then `actionPerformed`, then the three
   getters. Run `CounterPanelTest`. If "two clicks" fails, your count is a
   local variable.
2. `ColorPanel`. Same skeleton; the state is an index instead of a count.
3. `EchoPanel`. Same skeleton plus a `JTextField`. Watch the `.equals("")`.
4. `TrafficLightPanel`. Two `addActionListener` lines, `getSource()` in
   `actionPerformed`, `repaint()` at the end.
5. Run `Main`. Click everything. Notice that `main` ended long ago and the
   window is still there.

## Why is nothing happening?

Work down this list in order. Five of the six leave no error message at all.

- You forgot `addActionListener` — the button works, nothing is listening. Silent.
- You forgot `repaint()` — the data changed and the screen did not. Resize the
  window to confirm: resizing forces a redraw, and the new state appears.
- You declared the counter **inside** `actionPerformed` — it resets to 0 on
  every click. Stuck on 1.
- Your method is not exactly `public void actionPerformed(ActionEvent e)` —
  you wrote a method nobody calls. (With `implements ActionListener` this one
  at least fails to compile.)
- You wired the wrong button, or wired one and not the other.
- You changed a copy — `setBackground` on a different panel than the one in
  the frame, or a second `new JLabel` instead of the field.

## Rules of the road

- AP Java subset only, plus the Swing calls named above: `new JButton(String)`,
  `new JLabel(String)`, `new JTextField(int)`, `add`, `addActionListener`,
  `setText`, `getText`, `setBackground`, `getBackground`, `repaint`,
  `e.getSource()`. No lambdas, no anonymous classes, no arrays.
- Do not change method headers, the declared component fields, or the
  provided `paintComponent` and `getPreferredSize` in `TrafficLightPanel`.
- Nothing in your panels may open a window. Only `Main` does, and only inside
  `if (!GraphicsEnvironment.isHeadless())`. A panel that constructs a `JFrame`
  crashes the autograder and scores zero.
- Do not touch `src/test`, `pom.xml`, `grading.json`, or `.github`. The
  autograder checks that they are byte-identical to the template before it
  runs a single test; if they differ it stops and awards nothing, and the
  change shows up in the roster.

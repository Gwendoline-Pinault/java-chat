
import java.awt.Color;
import javax.swing.JButton;

public class MyButton extends JButton {
  private static final Color LIGHT_BLUE = new Color(80, 105, 220);

  public MyButton(String text) {
    super(text);
    setBackground(LIGHT_BLUE);
    setForeground(Color.WHITE);
  }
}

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame{
  private String clientName = null;

  public LoginFrame() {
    // Initialisation de la fenêtre de connexion
    setTitle("Connexion au chat");
    setSize(300, 130);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    BoxLayout box = new BoxLayout(panel, BoxLayout.Y_AXIS);
    panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Ajoute du padding dans le panel
    panel.setLayout(box);

    // ------- label -------
    JLabel nameLabel = new JLabel("Nom");
    nameLabel.setAlignmentX(CENTER_ALIGNMENT);
    panel.add(nameLabel);

    // ------- input -------
    JTextField nameField = new JTextField(15);
    nameField.setAlignmentY(CENTER_ALIGNMENT);
    panel.add(nameField);

    // -------- Bouton de connexion -----------
    MyButton connectButton = new MyButton("Se connecter");
    connectButton.setAlignmentX(CENTER_ALIGNMENT);
    // listener
    connectButton.addActionListener(e -> {
      // Met à jour le nom du client dans la variable de la classe
      this.clientName = nameField.getText();
      // Empêche la connexion si le nom est vide
      if (!this.clientName.isEmpty()) {
        dispose(); // Ferme la fenêtre de connexion
        new EchoClientGUI(this.clientName); // Lance le client
      } else {
        JOptionPane.showMessageDialog(this, "Veuillez entrer un nom.");
      }
    });
    panel.add(connectButton);

    // Ajout du panel dans le JFrame
    add(panel, BorderLayout.CENTER);
    setVisible(true);
  }
}

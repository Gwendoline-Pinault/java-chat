import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
  private String clientName = null;

  public LoginFrame() {
    // Initialisation de la fenêtre de connexion
    setTitle("Connexion Client");
    setSize(300, 100);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());

    JLabel nameLabel = new JLabel("Nom : ");
    JTextField nameField = new JTextField(15);
    JButton connectButton = new JButton("Se connecter");

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

    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(connectButton);

    add(panel);
    setVisible(true);
  }
}

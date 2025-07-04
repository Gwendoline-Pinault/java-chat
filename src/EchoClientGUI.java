import java.awt.*;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class EchoClientGUI extends JFrame {
  private String clientName = null;
  private JTextArea chatArea;
  private JTextField inputField;
  private PrintWriter out;
  private BufferedReader in;
  private Socket socket;

  public EchoClientGUI(String clientName) {
    this.clientName = clientName;
    setTitle("Chat - " + clientName);
    setSize(500, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    chatArea = new JTextArea();
    chatArea.setLineWrap(true);
    chatArea.setWrapStyleWord(true);
    chatArea.setEditable(false);
    inputField = new JTextField();
    MyButton logoutButton = new MyButton("Déconnexion");
    MyButton sendButton = new MyButton("Envoyer");
    JPanel headerZone = new JPanel();
    JPanel inputZone = new JPanel();
    BoxLayout box = new BoxLayout(inputZone, BoxLayout.X_AXIS);
    inputZone.setLayout(box);

    // header zone
    headerZone.add(logoutButton);
    add(headerZone, BorderLayout.NORTH);

    // chat zone
    add(new JScrollPane(chatArea), BorderLayout.CENTER);

    // inputZone
    inputZone.add(inputField);
    inputZone.add(sendButton);
    add(inputZone, BorderLayout.SOUTH);
 
    // listeners
    sendButton.addActionListener(e -> sendMessage());
    logoutButton.addActionListener(e -> {
      try {
        out.println("exit"); // Envoie le message de déconnexion au serveur
        socket.close(); // Ferme la connexion
        chatArea.append("Déconnexion.\n");
        dispose(); // Ferme la fenêtre
      } catch (IOException ex) {
        chatArea.append("Erreur de fermeture : " + ex.getMessage() + "\n");
      }
    });


    // connect client to server
    connectToServer();

    setVisible(true);
  }

  // Connexion au serveur
  private void connectToServer() {
    try {
      socket = new Socket("localhost", 12345); // Adresse et port
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);

      chatArea.append("Vous êtes connecté(e) au serveur.\nBienvenue " + clientName + " !\n");
      out.println(clientName + " a rejoint le chat."); // Envoie le nom du client au serveur
      chatArea.append("Pour écrire un message privé : tapez '@Nom ' avant votre message.\n");

      // Thread pour lire les messages entrants
      new Thread(() -> {
        String response;

        try {
          while ((response = in.readLine()) != null) {
            chatArea.append(response + "\n");
          }
        } catch (IOException ex) {
          chatArea.append("Déconnecté du serveur.\n");
        }
      }).start();

    } catch (IOException e) {
      chatArea.append("Erreur de connexion : " + e.getMessage() + "\n");
    }
  }

  // Envoi de message
  private void sendMessage() {
    String message = inputField.getText();
    if (message.isEmpty())
      return;

    LocalDateTime now = LocalDateTime.now(); // récupère la date et l'heure actuelles
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm"); // formate la date jour/mois/année, heures:minutes
    String currentTime = now.format(formatter);
    out.println(message); // Envoie au serveur
    chatArea.append("[" + currentTime + "] " + this.clientName + " : " + message + "\n");
    inputField.setText("");

    if (message.equalsIgnoreCase("exit")) {
      try {
        socket.close(); // Ferme la connexion
        chatArea.append("Déconnexion.\n");
      } catch (IOException e) {
        chatArea.append("Erreur de fermeture : " + e.getMessage() + "\n");
      }
    }
  }

  public static void main(String[] args) {
    // Lance la fenêtre de connexion
    SwingUtilities.invokeLater(() -> {
      new LoginFrame(); // Affiche la fenêtre de connexion
    });
  }
}

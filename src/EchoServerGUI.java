import java.awt.*;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class EchoServerGUI extends JFrame {
  private JTextArea logArea;
  private ServerSocket serverSocket;
  private ArrayList<Client> clients = new ArrayList<>(); 

  public EchoServerGUI() {
    setTitle("Serveur");
    setSize(400, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Zone de texte pour afficher les messages du client
    logArea = new JTextArea();
    logArea.setEditable(false);
    add(new JScrollPane(logArea), BorderLayout.CENTER);

    setVisible(true);
    startServer();
  }

  // Méthode pour démarrer le serveur
  private void startServer() {
    new Thread(() -> {
      try {
        serverSocket = new ServerSocket(12345); // Port d'écoute
        log("Serveur en écoute sur le port 12345...");

        while (true) {
          Socket clientSocket = serverSocket.accept(); // Accepte un client

          // Démarre un thread pour gérer ce client
          new Thread(() -> handleClient(clientSocket)).start();
        }

      } catch (IOException e) {
        log("Erreur serveur : " + e.getMessage());
      }
    }).start();
  }

  // Méthode de gestion des messages du client
  private void handleClient(Socket clientSocket) {
    String clientName = null;

    try (
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
      String message;
      String adresse = clientSocket.getInetAddress() + ":" + clientSocket.getPort(); 

      message = in.readLine();

      // Connexion d'un client : récupération de son nom et stockage dans la liste
      if (message != null & message.contains(" a rejoint le chat.")) {
        clientName = message.split(" a rejoint le chat.")[0]; // Récupère le nom du client
        log(clientName + " est connecté avec l'adresse " + adresse);
        clients.add(new Client(clientName, out)); // stocke le client dans la liste pour traitements
        for (Client client : clients) {
          if (!client.getName().equals(clientName)) { // Informe les clients connectés de la connexion d'un autre utilisateur
            client.getOut().println(clientName + " est en ligne.");
          }
        }
      }
      
      while ((message = in.readLine()) != null) {
        if (message.equalsIgnoreCase("exit")) {
          log(clientName + " a quitté la session.");
          break;
        }
        LocalDateTime now = LocalDateTime.now(); // récupère la date et l'heure actuelles
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm"); // formate la date jour/mois/année, heures:minutes
        String currentTime = now.format(formatter);
        log("[" + currentTime + "] " + clientName + " : " + message);

        if (message.startsWith("@")){
          String target = message.split(" ")[0]; // récupère le nom du destinataire
          String contentMessage = message.split(" ")[1]; // récupère le contenu du message
          target = target.substring(1); // Enlève le '@'
          boolean found = false;

          // Cherche le client correspondant pour envoyer le message privé
          for (Client client: clients) {
            if (client.getName().equals(target)) { // Vérifie si le client est dans la liste
              client.getOut().println("[" + currentTime + "] " + clientName + " : " + contentMessage); // Envoie le message sur le chat du destinataire
              found = true;
              break; // Sort de la boucle une fois le message envoyé
            }
          }
          if (!found) {
            out.println(target + " n'est pas connecté.");
            log(target + " n'est pas connecté.");
          }
        }
      }

      log(clientName + " est désormais hors ligne.");
      for (Client client : clients) {
        if (!client.getName().equals(clientName)) { // Informe les clients connectés de la connexion d'un autre utilisateur
          client.getOut().println(clientName + " est désormais hors ligne.");
        }
      }
      clientSocket.close();
    } catch (IOException e) {
      log("Erreur client : " + e.getMessage());
    }
  }

  // Affichage dans l'interface
  private void log(String message) {
    SwingUtilities.invokeLater(() -> logArea.append(message + "\n"));
  }

  public static void main(String[] args) {
    new EchoServerGUI(); // Lance le serveur
  }
}

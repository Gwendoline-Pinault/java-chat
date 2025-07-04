import java.io.PrintWriter;

public class Client {
  private String name;
  private PrintWriter out;

  public Client(String name, PrintWriter out) {
    this.name = name;
    this.out = out;
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retourne le PrintWriter associé au client pour envoyer des messages privés.
   * @return PrintWriter
   */
  public PrintWriter getOut() {
    return out;
  }
}

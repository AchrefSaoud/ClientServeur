import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            
            System.out.println("Attente de connexion...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connexion acceptée !");
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String message = in.readLine();
            System.out.println("Message reçu du client : " + message);
            out.println("Message reçu : " + message);
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

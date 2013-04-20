package chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Hagg
 */
public class ChatServer {

    private ArrayList<ClientThread> clientThreads = null;

    ChatServer(int port) {
        clientThreads = new ArrayList<>();

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                Socket socket = server.accept();

                log("Verbindung wurde von " + socket.getInetAddress() + " hergestellt");

                ClientThread client = new ClientThread(this, socket.getInputStream(), socket.getOutputStream());
                client.start();
                clientThreads.add(client);

            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void writeToAll(String name, String s) {
        for (int i = 0; i < clientThreads.size(); i++) {
            clientThreads.get(i).write(name, s);
        }
        log(name + ": " + s);
    }

    public String whoIsOnline() {
        String s = "Personen online: ";
        
        for (int i = 0; i < clientThreads.size(); i++) {
            s += clientThreads.get(i).getClientName() + ", ";
        }
        
        return s;
    }

    private void log(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        new ChatServer(1234);
    }
}

package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Hagg
 */
public class ClientThread extends Thread {

    private BufferedReader reader;
    private PrintWriter writer;
    private ChatServer server;
    private String name;

    public ClientThread(ChatServer server, InputStream input, OutputStream output) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.writer = new PrintWriter(output);
        this.server = server;
        try {
            this.name = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.append(server.whoIsOnline() + "\n").flush();
        server.writeToAll(name, "ist beigetreten");
    }

    @Override
    public void run() {
        try {
            String s = "";
            while ((s = reader.readLine()) != null) {
                server.writeToAll(name, s);
            }
            server.writeToAll(name, "hat den Chat verlassen");
        } catch (IOException ex) {
            server.writeToAll(name, "hat den Chat verlassen");
        }
    }

    public void write(String name, String s) {
        writer.append(name + ": " + s + "\n").flush();
    }

    public String getClientName() {
        return name;
    }
}

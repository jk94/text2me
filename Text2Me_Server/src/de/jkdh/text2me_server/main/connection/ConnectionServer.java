package de.jkdh.text2me_server.main.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionServer extends Thread {

    private ArrayList<ConnectionThread> ConnectionThreads = null;
    private ServerSocket server;
    private int port;

    public ConnectionServer(int port) {
        ConnectionThreads = new ArrayList<>();

        this.port = port;

        server = null;

    }

    @Override
    public void run() {

        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionServer.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                Socket socket = server.accept();

                log("Verbindung wurde von " + socket.getInetAddress()
                        + " hergestellt");

                ConnectionThread client = new ConnectionThread(this,
                        socket.getInputStream(), socket.getOutputStream());
                client.start();
                ConnectionThreads.add(client);

            } catch (IOException ex) {
                Logger.getLogger(ConnectionServer.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
    }

    public void close() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String s) {
        System.out.println("ConnectionServer: " + s);
    }
}

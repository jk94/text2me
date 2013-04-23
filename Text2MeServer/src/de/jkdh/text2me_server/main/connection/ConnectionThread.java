package de.jkdh.text2me_server.main.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionThread extends Thread {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket user;

    public ConnectionThread(Socket user) {
        try {
            this.reader = new BufferedReader(new InputStreamReader(
                    user.getInputStream()));
            this.writer = new PrintWriter(user.getOutputStream());
        } catch (IOException ex) {
        }
        this.user = user;

        writer.append("GET TEXT2MEPROTOCOL/1.0" + "\n").flush();
        writer.append("User: 00490123456789" + "\n").flush();
        writer.append("Password: 12345" + "\n").flush();
        writer.append("Content-Type: text/json" + "\n").flush();
        writer.append(" " + "\n").flush();
        writer.append("{" + "\n").flush();
        writer.append("\t\"Sender\": \"00490123456789\"" + "\n").flush();
        writer.append("}" + "\n").flush();
        writer.append("" + "\n").flush();
        writer.append("" + "\n").flush();
    }

    @Override
    public void run() {
        try {
            String s = "";
            while ((s = reader.readLine()) != null) {
                System.out.println(user.getInetAddress().getHostAddress() + ": " + s);
            }
        } catch (IOException ex) {
        }
    }

    public void write(String s) {
        writer.append(s + "\n").flush();
    }
}

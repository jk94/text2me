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

		//writer.append("Willkommen" + "\n").flush();
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

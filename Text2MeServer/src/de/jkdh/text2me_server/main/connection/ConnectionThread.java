package de.jkdh.text2me_server.main.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ConnectionThread extends Thread {

	private BufferedReader reader;
	private PrintWriter writer;
	private ConnectionServer server;

	public ConnectionThread(ConnectionServer server, InputStream input,
			OutputStream output) {
		this.reader = new BufferedReader(new InputStreamReader(input));
		this.writer = new PrintWriter(output);
		this.server = server;
		writer.append("Willkommen" + "\n").flush();
	}

	@Override
	public void run() {
		try {
			String s = "";
			while ((s = reader.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException ex) {
		}
	}

	public void write(String s) {
		writer.append(s + "\n").flush();
	}
}

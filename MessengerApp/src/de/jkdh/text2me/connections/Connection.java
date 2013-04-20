package de.jkdh.text2me.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import de.jkdh.common.ProtocolHelper;
import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.message.Message;

public class Connection extends AsyncTask<URL, Integer, Long> {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	private String server;
	private int port;

	public Connection(String server, int port) {
		this.server = server;
		this.port = port;
	}

	public void reconnect() {
		try {
			socket = new Socket(server, port);

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Long doInBackground(URL... params) {

		Request request = new Request(
				"SEND TEXT2MEPROTOCOL/1.0\nUser: user\nPassword: password\n\n{\n\"Sender\": \"user\"\n}");
		System.out.println(request.getType());
		System.out.println(request.getUser());
		System.out.println(request.getPassword());

		reconnect();

		return null;
	}

	public void sendMessage(Message message) {
		if (socket == null || socket.isClosed()) {
			reconnect();
		}
		out.append(ProtocolHelper.getHeader(ProtocolHelper.TYPE_SEND, "user",
				"password"));
		String[] name = { ProtocolHelper.ITEM_SENDER,
				ProtocolHelper.ITEM_RECEIVER, ProtocolHelper.ITEM_TEXT,
				ProtocolHelper.ITEM_TIME };
		String[] content = { "user", "receiver", message.getText(),
				String.valueOf(message.getTime().getTime()) };
		out.append(ProtocolHelper.getBody(name, content));
		out.flush();
	}

	public void checkContact(Contact contact) {
		if (socket.isClosed()) {
			reconnect();
		}
		String protocol = "";
		protocol = "/CONTACTEXIST MESSENGERAPPPROTOCOL/1.0\n";
		protocol = protocol + "";
		// {
		// "Sender": "00490123456789",
		// "Receiver": "00491243233",
		// "Text": "Hallo Jan",
		// "Time": "Unix Timestamp"
		// }"
		out.append(protocol + "\n");
		System.out.println(protocol);
		out.flush();
	}

	public class Request {
		private String type;
		private String user;
		private String password;
		private String[] name;
		private String[] content;

		public Request(String request) {
			String[] lines = request.split("\n");
			System.out.println(lines.length);
			if (lines.length >= 4) {
				if (lines[0].endsWith(ProtocolHelper.PROTOCOL_NAME + "/"
						+ ProtocolHelper.PROTOCOL_VERSION)) {
					type = lines[0].substring(0, lines[0].indexOf(" ")).trim();
					user = lines[1].substring(lines[1].indexOf(": ") + 2)
							.trim();
					password = lines[2].substring(lines[2].indexOf(": ") + 2)
							.trim();
					name = new String[lines.length - 3];
					content = new String[lines.length - 3];
					// JSON einlesen
					// for (int i = 3; i < lines.length; i++) {
					// String line = lines[i];
					// if(!line.trim().equals("")){
					//
					// }
					// }
				}
			}
		}

		public String getType() {
			return type;
		}

		public String getContent(String sName) {
			String s = "";

			for (int i = 0; i < content.length; i++) {
				if (name[i].equals(sName)) {
					s = content[i];
				}
			}

			return s;
		}

		public String getUser() {
			return user;
		}

		public String getPassword() {
			return password;
		}

	}

}

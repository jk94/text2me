package de.jkdh.text2me.connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import de.jkdh.common.ProtocolHelper;
import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.message.Message;

public class Connection extends AsyncTask<URL, Integer, Long> {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;

	private static WaitingList waitinglist;

	private String server;
	private int port;

	public Connection(String server, int port) {
		this.server = server;
		this.port = port;

		if (waitinglist == null) {
			waitinglist = new WaitingList();
		}
	}

	public void reconnect() {
		try {
			if (socket != null) {
				socket.close();
				in.close();
				out.close();
			}

			socket = null;
			in = null;
			out = null;

			socket = new Socket(server, port);

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Keine Verbindung zum Server beim reconnect");
		}
	}

	public void executeWaitinglist() {
		waitinglist.execute();
	}

	@Override
	protected Long doInBackground(URL... params) {
		//
		// Request request = new Request(
		// "SEND TEXT2MEPROTOCOL/1.0\nUser: user\nPassword: password\n\n{\n\"Sender\": \"user\"\n}");
		// System.out.println(request.getType());
		// System.out.println(request.getUser());
		// System.out.println(request.getPassword());

		reconnect();

		// while (socket == null) {
		//
		// }
		//
		// while (true) {
		// if (!socket.isClosed()) {
		// if (waitlist.size() > 0) {
		// if (waitlist.get(0).isFlushable()) {
		// out.append(waitlist.get(0).getText());
		// out.append("\n");
		// out.flush();
		// waitlist.remove(0);
		// }
		// }
		// } else {
		// reconnect();
		// }
		// System.out.println(waitlist.size());
		// }
		return null;
	}

	public void sendMessage(Message message) {
		Packet p = new Packet();
		p.append(ProtocolHelper.getHeader(ProtocolHelper.TYPE_SEND, "user",
				"password"));
		String[] name = { ProtocolHelper.ITEM_SENDER,
				ProtocolHelper.ITEM_RECEIVER, ProtocolHelper.ITEM_TEXT,
				ProtocolHelper.ITEM_TIME };
		String[] content = { "user", "receiver", message.getText(),
				String.valueOf(message.getTime().getTime()) };
		p.append(ProtocolHelper.getBody(name, content));
		p.flush();

		waitinglist.add(p);
		waitinglist.execute();
	}

	public void checkContact(Contact contact) {
		if (socket.isClosed()) {
			reconnect();
		}
		// String protocol = "";
		// protocol = "/CONTACTEXIST MESSENGERAPPPROTOCOL/1.0\n";
		// protocol = protocol + "";
		// {
		// "Sender": "00490123456789",
		// "Receiver": "00491243233",
		// "Text": "Hallo Jan",
		// "Time": "Unix Timestamp"
		// }"
		// out.append(protocol + "\n");
		// System.out.println(protocol);
		// out.flush();
	}

	public class WaitingList {

		private ArrayList<Connection.Packet> waitinglist;

		public WaitingList() {
			waitinglist = new ArrayList<Connection.Packet>();
		}

		public void add(Connection.Packet p) {
			waitinglist.add(p);
		}

		public void execute() {
			if (socket != null) {
				System.out.println("Waitinglist execute");
				for (int i = 0; i < waitinglist.size(); i++) {
					out.write(waitinglist.get(i).getText());
					out.append("\n");
					out.flush();
					System.out.println("Nachricht versendet");
				}
				waitinglist.clear();
			} else {
				reconnect();
			}
		}
	}

	public class Packet {
		private String text;
		private boolean flush;

		public Packet() {
			this.text = "";
			this.flush = false;
		}

		public void append(String text) {
			this.text += text;
		}

		public void flush() {
			this.flush = true;
		}

		public boolean isFlushable() {
			return flush;
		}

		public String getText() {
			return text;
		}
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

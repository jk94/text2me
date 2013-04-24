package de.jkdh.text2me.connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import de.jkdh.common.ProtocolHelper;
import de.jkdh.common.ProtocolPacket;
import de.jkdh.text2me.message.Message;

public class Connection {
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

		startReconnect();
	}

	public void startReconnect() {
		Reconnector r = new Reconnector();
		r.execute();
	}

	public void executeWaitinglist() {
		waitinglist.execute();
	}

	public void sendMessage(Message message) {
		ProtocolPacket p = new ProtocolPacket(ProtocolHelper.TYPE_SEND, "user",
				"password", ProtocolHelper.CONTENT_TYPE_JSON);

		String[] name = { ProtocolHelper.ITEM_SENDER,
				ProtocolHelper.ITEM_RECEIVER, ProtocolHelper.ITEM_TEXT,
				ProtocolHelper.ITEM_TIME };
		String[] content = { "user", "receiver", message.getText(),
				String.valueOf(message.getTime().getTime()) };
		p.setMessage(ProtocolHelper.getBody(name, content));

		waitinglist.add(p);
		waitinglist.execute();
	}

	public void checkContact(String number) {
		ProtocolPacket p = new ProtocolPacket(ProtocolHelper.TYPE_CHECKCONTACT,
				"user", "password", ProtocolHelper.CONTENT_TYPE_JSON);

		String[] name = { ProtocolHelper.ITEM_CONTACT };
		String[] content = { number };
		p.setMessage(ProtocolHelper.getBody(name, content));

		waitinglist.add(p);
		waitinglist.execute();
	}

	public class WaitingList {

		private ArrayList<ProtocolPacket> waitinglist;

		public WaitingList() {
			waitinglist = new ArrayList<ProtocolPacket>();
		}

		public void add(ProtocolPacket p) {
			waitinglist.add(p);
		}

		public void execute() {
			if (socket != null) {
				for (int i = 0; i < waitinglist.size(); i++) {
					out.write(waitinglist.get(i).getPacket());
					out.append("\n");
					out.flush();
				}
				waitinglist.clear();
			} else {
				startReconnect();
			}
		}
	}

	public class Reconnector extends AsyncTask<URL, Integer, Long> {

		@Override
		protected Long doInBackground(URL... params) {

			try {
				socket = null;
				socket = new Socket(server, port);

				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());

				if (socket.isConnected()) {
					new Reader().execute();
					waitinglist.execute();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	public class Reader extends AsyncTask<URL, Integer, Long> {

		@Override
		protected Long doInBackground(URL... params) {
			while (socket.isConnected()) {
				new ProtocolPacket(in);
				System.out.println("new ProtocolPacket");
			}
			return null;
		}
	}

}

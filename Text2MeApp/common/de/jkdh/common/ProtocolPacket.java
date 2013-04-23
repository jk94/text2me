package de.jkdh.common;

import java.io.BufferedReader;
import java.net.Socket;

public class ProtocolPacket {
	private int contenttype;
	private String user;
	private String password;
	private String message_type;
	private String message;
	private boolean valid = false;

	public ProtocolPacket(int type, String user, String password,
			String message_type) {
		this.contenttype = type;
		this.user = user;
		this.password = password;
		this.message_type = message_type;
		this.valid = true;
	}

	public ProtocolPacket(BufferedReader reader) {
		try {
			String s;

			while ((s = reader.readLine()).trim().equals("")) {
			}

			if (s.endsWith(ProtocolHelper.PROTOCOL_NAME + "/"
					+ ProtocolHelper.PROTOCOL_VERSION)) {

				contenttype = ProtocolHelper.getTypeIntByString(s.substring(0,
						s.indexOf(" ")));

				if (contenttype >= 0 && contenttype <= ProtocolHelper.CONTENT_TYPES.length) {

					while (!(s = reader.readLine()).trim().equals("")) {
						s = s.trim();
						if (s.startsWith(ProtocolHelper.HEADER_USER)) {
							user = s.substring(s.indexOf(": ") + 2);
						}
						if (s.startsWith(ProtocolHelper.HEADER_PASSWORD)) {
							password = s.substring(s.indexOf(": ") + 2);
						}
						if (s.startsWith(ProtocolHelper.HEADER_CONTENT_TYPE)) {
							message_type = s.substring(s.indexOf(": ") + 2);
						}
					}

					boolean b = false;
					message = "";

					while (!b
							&& !(b = (s = reader.readLine()).trim().equals(""))) {
						message += s;
					}
					valid = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getContentType() {
		return contenttype;
	}

	public void setContentType(int type) {
		this.contenttype = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isValid() {
		return valid;
	}

	public String getPacket() {
		String s = "";

		s += ProtocolHelper.getHeader(contenttype, user, password);
		s += message;
		s += "\n\n";

		return s;
	}

}

package de.jkdh.common;

public class ProtocolHelper {

	public static final String PROTOCOL_NAME = "TEXT2MEPROTOCOL";
	public static final String PROTOCOL_VERSION = "1.0";

	public static final int TYPE_SEND = 0;
	public static final int TYPE_GET = 1;
	public static final int TYPE_READ = 2;
	public static final int TYPE_DELIVER = 3;
	public static final int TYPE_OK = 4;
	public static final int TYPE_CHECKCONTACT = 5;

	public static final String[] TYPES = { "SEND", "GET", "READ", "DELIVER",
			"OK", "CHECKCONTACT" };

	public static final String HEADER_USER = "User";
	public static final String HEADER_PASSWORD = "Password";
	public static final String HEADER_CONTENT_TYPE = "Content-Type";

	public static final String ITEM_SENDER = "Sender";
	public static final String ITEM_RECEIVER = "Receiver";
	public static final String ITEM_TEXT = "Text";
	public static final String ITEM_TIME = "Time";
	public static final String ITEM_MESSAGEID = "MessageID";
	public static final String ITEM_CONTACT = "Contact";

	public static final String CONTENT_TYPE_NULL = "null";
	public static final String CONTENT_TYPE_JSON = "text/json";
	public static final String CONTENT_TYPE_JPEG = "image/jpeg";

	public static String getHeader(int type, String user, String password) {
		return TYPES[type] + " " + PROTOCOL_NAME + "/" + PROTOCOL_VERSION
				+ "\n" + HEADER_USER + ": " + user + "\n" + HEADER_PASSWORD
				+ ": " + password + "\n\n";
	}

	public static String getBody(String[] name, String[] content) {
		String s = "";

		if (name.length == content.length) {
			s += "{\n";
			for (int i = 0; i < name.length; i++) {
				if (i != 0) {
					s += ",\n";
				}
				s += "\t\"" + name[i] + "\": \"" + content[i] + "\"";
			}
			s += "\n}\n";
		}

		return s;
	}

	public static int getTypeIntByString(String type) {
		int j = -1;
		for (int i = 0; i < TYPES.length; i++) {
			if (type.equals(TYPES[i])) {
				j = i;
			}
		}
		return j;
	}
}

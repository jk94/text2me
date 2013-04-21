package de.jkdh.text2me.manager;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import de.jkdh.text2me.connections.Connection;
import de.jkdh.text2me.connections.DB_Connect;
import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.conversation.Conversation;
import de.jkdh.text2me.message.Message;

public class Control {

	private static Control theControl;
	private ArrayList<Conversation> conversations;
	private ArrayList<Contact> contacts;
	private Connection connection;
	private DB_Connect database;
	private ArrayList<UI> uis;
	private ContactManager CManager;

	public Control(Context context) {
		super();

		database = new DB_Connect(context);

		CManager = new ContactManager(this, context);
		Log.d("Control", "CM erstellt");
		CManager.getPhoneContactIDs();
		CManager.exeIT();
		Log.d("Control", "CM ReadContacts");

		uis = new ArrayList<UI>();

		contacts = database.getContacts();
		conversations = database.getConversations(this);

		connection = new Connection("text2me", 2729);
		connection.execute();

	}

	public ArrayList<Conversation> getConversations() {
		return conversations;
	}

	public Conversation getConversationByID(long id) {
		Conversation c = null;
		for (int i = 0; i < conversations.size(); i++) {
			if (conversations.get(i).getId() == id) {
				c = conversations.get(i);
			}
		}
		return c;
	}

	public Conversation addConversation(Conversation con, Contact contact) {
		this.conversations.add(con);
		con.setContact(contact);
		this.database.addConversation(con, contact);
		this.addConversationOnUIs(con);
		return con;
	}

	public void setConversation(ArrayList<Conversation> conv) {
		this.conversations = conv;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public Contact getContactByID(long id) {
		Contact c = null;
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getId() == id) {
				c = contacts.get(i);
			}
		}
		return c;
	}

	public Contact addContact(Contact contact) {
		this.contacts.add(contact);
		this.database.addContact(contact);
		this.addContactOnUIs(contact);
		return contact;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public Conversation getConversationByContact(Contact contact) {
		Conversation convers = null;

		for (int i = 0; i < conversations.size(); i++) {
			if (conversations.get(i).getContact().equals(contact)) {
				convers = conversations.get(i);
			}
		}
		return convers;
	}

	public Message addMessage(Message message, Conversation con) {
		con.addMessage(message);
		this.database.addMessage(message, con);
		connection.sendMessage(message);
		return message;
	}

	public DB_Connect getDatabaseConnection() {
		return this.database;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void registerUI(UI ui) {
		this.uis.add(ui);
	}

	public void unregisterUI(UI ui) {
		this.uis.remove(ui);
	}

	private void addConversationOnUIs(Conversation conversation) {
		for (int i = 0; i < uis.size(); i++) {
			uis.get(i).addConversation(conversation);
		}
	}

	public void addContactOnUIs(Contact contact) {
		for (int i = 0; i < uis.size(); i++) {
			uis.get(i).addContact(contact);
		}
	}

	public interface UI {
		public void addConversation(Conversation conversation);

		public void addContact(Contact contact);
	}

	public static Control getControl(Context context) {
		if (theControl == null) {
			theControl = new Control(context);
		}
		return theControl;
	}

	public static Control getControl() {
		// if (theControl == null) {
		// theControl = new Control(context);
		// }
		return theControl;
	}

	// Nur zu Testzwecken //////////////////////////////////

	public void addExampleConversations() {

		/*
		 * Message m1 = new Message(-1, "Hallo ;)", new Date().getTime(),
		 * false); Message m2 = new Message(-1, "Dies ist ein Test", new
		 * Date().getTime(), true); Message m3 = new Message(-1,
		 * "Noch eine Nachricht", new Date().getTime(), false); Message m4 = new
		 * Message(-1, "Test! Test! Test!", new Date().getTime(), false);
		 * Message m5 = new Message(-1, "Eins Zwei Drei Vier Fünf", new
		 * Date().getTime(), true); Message m6 = new Message(-1,
		 * "Das ist voll die tolle App", new Date().getTime(), false); Message
		 * m7 = new Message( -1,
		 * "Dies ist jetzt mal eine längere Nachricht, um zu schauen ob es damit irgendwelche Probleme gibt. also schreib ich hier jetzt irgendwelches sinnloses Zeug. Eins Zwei Drei Blub hihihi"
		 * , new Date().getTime(), true);
		 */

		Contact c1 = new Contact(-1, -1, "Jan", "1234568", "Mein Status",
				"mobil");
		Contact c2 = new Contact(-1, -1, "Peter", "1290387", "Ein Status",
				"mobil");
		Contact c3 = new Contact(-1, -1, "Günther", "1756290387", "In Status",
				"mobil");

		// Conversation con1 = new Conversation(-1, c1, new
		// ArrayList<Message>());
		// Conversation con2 = new Conversation(-1, c2, new
		// ArrayList<Message>());
		// Conversation con3 = new Conversation(-1, c3, new
		// ArrayList<Message>());

		addContact(c1);
		addContact(c2);
		addContact(c3);

		// addConversation(con1, c1);
		// addConversation(con2, c2);
		// addConversation(con3, c3);
		//
		// addMessage(m1, con1);
		// addMessage(m2, con1);
		// addMessage(m3, con1);
		// addMessage(m4, con2);
		// addMessage(m5, con2);
		// addMessage(m6, con2);
		// addMessage(m7, con3);
	}
}

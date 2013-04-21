package de.jkdh.text2me.conversation;

import java.util.ArrayList;

import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.message.Message;

public class Conversation {
	private Contact contact;
	private ArrayList<Message> messages;
	private int _id;

	public Conversation(int _id, Contact contact, ArrayList<Message> messages) {
		super();
		this.contact = contact;
		this.messages = messages;
		this._id = _id;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}

	public Message getLastMessage() {
		Message m = null;
		if (messages.size() > 0) {
			m = messages.get(messages.size() - 1);
		}
		return m;
	}

	public void addMessage(Message theMessage) {
		messages.add(theMessage);
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}
}

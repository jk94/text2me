package de.jkdh.text2me.connections;

import java.util.ArrayList;

import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.conversation.Conversation;
import de.jkdh.text2me.message.Message;

public class BackupManager {

	ArrayList<Contact> contacts;
	ArrayList<Conversation> conversations;
	ArrayList<Message> unreadMessages;

	public BackupManager(ArrayList<Contact> contacts,
			ArrayList<Conversation> conversations,
			ArrayList<Message> unreadMessages) {
		this.unreadMessages = unreadMessages;
		this.conversations = conversations;
		this.contacts = contacts;
	}

	public BackupManager() {
		this.unreadMessages = new ArrayList<Message>();
		this.conversations = new ArrayList<Conversation>();
		this.contacts = new ArrayList<Contact>();
	}

}

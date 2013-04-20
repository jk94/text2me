package de.jkdh.text2me.message;

import de.jkdh.text2me.conversation.Conversation;

public class UnreadMessage {
	private Conversation conversation;
	private Message message;

	public UnreadMessage(Message message, Conversation conversation) {
		this.message = message;
		this.conversation = conversation;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}

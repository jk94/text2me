package de.jkdh.text2me.contact;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import de.jkdh.text2me.R;
import de.jkdh.text2me.conversation.Conversation;
import de.jkdh.text2me.conversation.ConversationActivity;
import de.jkdh.text2me.div.DivMethods;
import de.jkdh.text2me.manager.Control;
import de.jkdh.text2me.message.Message;

public class ContactsActivity extends ListActivity {

	private Control theControl;
	private ArrayList<Contact> contacts;
	private ContactArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts_list);
		theControl = Control.getControl(this);

		contacts = theControl.getContacts();
		adapter = new ContactArrayAdapter(this, contacts);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		DivMethods.log("ContactsActivity", adapter.getItem(position).getName()
				+ " Neue Konversation");
		Contact contact = adapter.getItem(position);
		onContactSelected(contact);
	}

	public void onContactSelected(Contact contact) {

		Conversation con = theControl.getConversationByContact(contact);

		if (con == null) {
			// neue Konversation
			Conversation thenewCon = new Conversation(-1, null,
					new ArrayList<Message>());
			theControl.addConversation(thenewCon, contact);
			openConversationActivity(thenewCon.getId());
		} else {
			openConversationActivity(con.getId());
		}
		finish();
	}

	private void openConversationActivity(int conversationID) {
		Intent i = new Intent(this, ConversationActivity.class);
		i.putExtra(DivMethods.EXTRA_CONVERSATION_ID, conversationID);
		startActivity(i);
	}
}

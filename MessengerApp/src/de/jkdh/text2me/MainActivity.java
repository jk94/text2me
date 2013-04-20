package de.jkdh.text2me;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import de.jkdh.text2me.connections.DB_Connect;
import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.contact.ContactsActivity;
import de.jkdh.text2me.conversation.Conversation;
import de.jkdh.text2me.conversation.ConversationActivity;
import de.jkdh.text2me.conversation.ConversationArrayAdapter;
import de.jkdh.text2me.dashclock.DashClockMessageExtension;
import de.jkdh.text2me.div.DivMethods;
import de.jkdh.text2me.manager.Control;
import de.jkdh.text2me.message.Message;
import de.jkdh.text2me.message.UnreadMessage;
import de.jkdh.text2me.settings.SettingsActivity;

public class MainActivity extends ListActivity implements Control.UI {

	private ConversationArrayAdapter adapter;
	private static Control theControl;
	DB_Connect dbc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		theControl = Control.getControl(this);
		// theControl.setMainActivity(this);
		// theControl.registerUI(this);

		ArrayList<Conversation> cons = theControl.getConversations();

		adapter = new ConversationArrayAdapter(this, cons);

		adapter.notifyDataSetChanged();

		setListAdapter(adapter);
		dbc = new DB_Connect(this);
		// log(dbc.getDatabaseName());
	}

	@Override
	public void onDestroy() {
		theControl.unregisterUI(this);
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			log("OptionsMenu: Aktualisieren");
			break;
		case R.id.action_settings:
			log("OptionsMenu: Einstellungen");
			openSettingsActivity();
			break;
		case R.id.action_contacs:
			log("OptionsMenu: Kontakte");
			openContactActivity();
			break;
		case R.id.action_exampleconversation:
			log("OptionsMenu: Example Conversations");
			theControl.addExampleConversations();
			break;
		case R.id.action_examplenotification:
			log("OptionsMenu: Example Notification");
			exampleNotification();
			break;
		}
		return true;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		log(adapter.getItem(position).getContact().getName());
		Conversation conversation = adapter.getItem(position);
		onConversationSelected(conversation);
	}

	public void openContactActivity() {
		Intent i = new Intent(this, ContactsActivity.class);
		startActivity(i);
	}

	public void openSettingsActivity() {
		Intent i = new Intent(this, SettingsActivity.class);
		startActivity(i);
	}

	public void onConversationSelected(Conversation conversation) {
		Intent i = new Intent(this, ConversationActivity.class);
		i.putExtra(DivMethods.EXTRA_CONVERSATION_ID, conversation.getId());
		startActivity(i);
	}

	@Override
	public void addConversation(Conversation conversation) {
		adapter.add(conversation);
	}

	public void removeConversation(Conversation conversation) {
		adapter.remove(conversation);
	}

	@Override
	public void addContact(Contact contact) {

	}

	private void log(String s) {
		Log.d("MainActivity", s);
	}

	// ExampleNotification

	private void exampleNotification() {

		Notification n = new Notification(this);

		Message message = new Message(
				-1,
				"Hallo jh jg  kuf kf khgf jh h hgf jh fhjg jhg jhg jhg jhg jhg jhf jhg jhg ",
				12344, false);
		Message message2 = new Message(-1, "Wie geht es dir?", 12344, false);

		Conversation conversation = theControl.getConversations().get(0);
		Conversation conversation2 = theControl.getConversations().get(1);

		UnreadMessage unreadMessage = new UnreadMessage(message, conversation);
		UnreadMessage unreadMessage2 = new UnreadMessage(message2,
				conversation2);

		ArrayList<UnreadMessage> unreadMessages = new ArrayList<UnreadMessage>();
		unreadMessages.add(unreadMessage);
		unreadMessages.add(unreadMessage2);

		n.create(unreadMessages);

		DashClockMessageExtension.setUnreadMessages(unreadMessages);

	}

}

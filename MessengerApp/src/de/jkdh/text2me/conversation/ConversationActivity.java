package de.jkdh.text2me.conversation;

import java.util.Date;

import android.R.color;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import de.jkdh.text2me.R;
import de.jkdh.text2me.contact.ContactProfileActivity;
import de.jkdh.text2me.div.DivMethods;
import de.jkdh.text2me.manager.Control;
import de.jkdh.text2me.message.Message;
import de.jkdh.text2me.message.MessageArrayAdapter;

public class ConversationActivity extends ListActivity {

	private Control theControl;
	private Conversation conversation;
	private EditText txf_Message;
	private MessageArrayAdapter adapter;

	// private ListView listview;
	// private Button btn_Send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);
		txf_Message = (EditText) findViewById(R.id.senden_edittext);
		// listview = getListView();
		// btn_Send.setText("Send");
		theControl = Control.getControl(this);

		int id = getIntent().getIntExtra(DivMethods.EXTRA_CONVERSATION_ID, 0);

		conversation = theControl.getConversationByID(id);

		getListView().setDividerHeight(0);

		setTitle(conversation.getContact().getName());
		getActionBar().setSubtitle(R.string.profile_last_online);

		adapter = new MessageArrayAdapter(this, conversation.getMessages());

		adapter.notifyDataSetChanged();

		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.conversation_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.showProfil:
			log("OptionsMenu: Zeige Profil");
			Intent i = new Intent(this, ContactProfileActivity.class);
			i.putExtra(DivMethods.EXTRA_CONTACT_ID, conversation.getContact()
					.getId());
			i.putExtra(DivMethods.EXTRA_CONVERSATION_ID, conversation.getId());
			startActivity(i);
			break;
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			break;
		}
		return true;
	}

	public void onClickSendenButton(View v) {
		String eingabe = txf_Message.getText().toString();
		if (!eingabe.isEmpty() && !DivMethods.haveOnlySpace(eingabe)) {
			// DivMethods.removeAllEnframingSpaces(eingabe);
			txf_Message.setText("");
			addNewMessage(eingabe, new Date().getTime(), true);
		} else {
			if (eingabe.isEmpty()) {
				Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Only Space", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void addNewMessage(String text, long timestamp, boolean ownMsg) {
		Message msg = new Message(-1, text, timestamp, ownMsg);
		theControl.addMessage(msg, conversation);
		// ans Ende der Liste scrollen:
		getListView().setSelection(adapter.getCount() - 1);
		getListView().setBackgroundColor(color.holo_orange_light);

	}

	private void log(String s) {
		Log.d("MainActivity", s);
	}
}

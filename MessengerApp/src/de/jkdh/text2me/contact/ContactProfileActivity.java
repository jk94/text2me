package de.jkdh.text2me.contact;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import de.jkdh.text2me.R;
import de.jkdh.text2me.div.DivMethods;
import de.jkdh.text2me.manager.Control;

public class ContactProfileActivity extends Activity {

	private Contact contact;
	private Control theControl;
	private int conversation_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_profile);
		theControl = Control.getControl(this);
		int contactID = getIntent().getIntExtra(DivMethods.EXTRA_CONTACT_ID, 0);
		conversation_id = getIntent().getIntExtra(
				DivMethods.EXTRA_CONVERSATION_ID, 0);
		log("" + contactID);
		contact = theControl.getContactByID(contactID);

		setTitle(contact.getName());

		TextView twName = (TextView) findViewById(R.id.profile_name);
		TextView twOnline = (TextView) findViewById(R.id.profile_last_online);
		TextView twStatus = (TextView) findViewById(R.id.profile_status);
		TextView twNumber = (TextView) findViewById(R.id.profile_number);

		twName.setText(contact.getName());
		twNumber.setText(contact.getNumber());
		twStatus.setText(contact.getStatus() + "\n\n\n");
		twOnline.setText("Last Online:\nnot in use");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.getParentActivityIntent(this).putExtra(
					DivMethods.EXTRA_CONVERSATION_ID, conversation_id);
			NavUtils.navigateUpFromSameTask(this);
			break;
		}
		return true;
	}

	private void log(String s) {
		Log.d("MainActivity", s);
	}
}

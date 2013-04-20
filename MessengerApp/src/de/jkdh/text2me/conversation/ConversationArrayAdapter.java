package de.jkdh.text2me.conversation;

import java.util.ArrayList;

import de.jkdh.text2me.R;
import de.jkdh.text2me.message.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConversationArrayAdapter extends ArrayAdapter<Conversation> {

	private Context c;
	private ArrayList<Conversation> items;
	private final static int ID = R.layout.activity_main_list_item;

	public ConversationArrayAdapter(Context context,
			ArrayList<Conversation> objects) {
		super(context, ID, objects);
		c = context;
		items = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RelativeLayout v = (RelativeLayout) convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = (RelativeLayout) vi.inflate(ID, null);
		}

		TextView name = (TextView) v.findViewById(R.id.list_item_name);
		TextView text = (TextView) v.findViewById(R.id.list_item_status);
		TextView time = (TextView) v.findViewById(R.id.list_item_time);

		if (name != null) {
			name.setText(items.get(position).getContact().getName());
		}
		Message lastMessage = items.get(position).getLastMessage();
		if (text != null) {
			if (lastMessage != null) {
				text.setText(lastMessage.getText());
			} else {
				text.setText("");
			}
		}
		if (time != null) {
			if (lastMessage != null) {
				time.setText(lastMessage.getDateAsString(getContext()));
			} else {
				time.setText("");
			}
		}

		return v;
	}

}

package de.jkdh.text2me.message;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import de.jkdh.text2me.R;

public class MessageArrayAdapter extends ArrayAdapter<Message> {

	private final int ANZAHL_VIEWS = 2;
	private final int TYPE_VIEW_EMPFANGEN = 0;
	private final int TYPE_VIEW_GESENDET = 1;

	private Context c;
	private ArrayList<Message> items;
	private final static int ID = R.layout.activity_conversation_list_item_empfangen;

	public MessageArrayAdapter(Context context, ArrayList<Message> objects) {
		super(context, ID, objects);
		c = context;
		items = objects;
	}

	public void showLast() {
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Message m = items.get(position);

		View v = (View) convertView;

		int type = getItemViewType(position);
		int layout;

		if (type == TYPE_VIEW_EMPFANGEN) {
			layout = R.layout.activity_conversation_list_item_empfangen;
		} else {
			layout = R.layout.activity_conversation_list_item_gesendet;
		}

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = (View) vi.inflate(layout, null);
		}

		TextView text = (TextView) v.findViewById(R.id.list_item_text);
		TextView time = (TextView) v.findViewById(R.id.list_item_time);

		if (text != null) {
			text.setText(m.getText());
		}
		if (time != null) {
			time.setText(m.getTimeAsString(getContext()));
		}

		return v;
	}

	@Override
	public int getViewTypeCount() {
		return ANZAHL_VIEWS;
	}

	@Override
	public int getItemViewType(int position) {
		int i;

		if (items.get(position).isEigeneNachricht()) {
			i = TYPE_VIEW_GESENDET;
		} else {
			i = TYPE_VIEW_EMPFANGEN;
		}

		return i;
	}

	@Override
	public int getCount() {
		return items.size();
	}
}

package de.jkdh.text2me.contact;

import java.util.ArrayList;

import de.jkdh.text2me.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ContactArrayAdapter extends ArrayAdapter<Contact> {
	private Context c;
	private ArrayList<Contact> items;
	private final static int ID = R.layout.activity_contacts_list_item;

	public ContactArrayAdapter(Context context, ArrayList<Contact> objects) {
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
		TextView status = (TextView) v.findViewById(R.id.list_item_status);
		TextView numbertype = (TextView) v
				.findViewById(R.id.list_item_numbertype);

		if (name != null) {
			name.setText(items.get(position).getName());
		}
		if (status != null) {
			status.setText(items.get(position).getStatus());
		}
		if (numbertype != null) {
			numbertype.setText(items.get(position).getNumbertype());
		}
		return v;
	}

}

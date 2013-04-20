package de.jkdh.text2me.message;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import de.jkdh.text2me.R;

import android.content.Context;

public class Message {
	private String text;
	private Date time;
	private boolean eigeneNachricht;
	private int _id;

	public Message(int _id, String text, long timestamp, boolean eigeneNachricht) {
		super();
		this._id = _id;
		this.text = text;
		this.time = new Date((long) timestamp);
		this.eigeneNachricht = eigeneNachricht;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return time;
	}

	public String getTimeAsString(Context context) {
		String s = "";

		Calendar c = Calendar.getInstance();
		c.setTime(time);

		s = c.get(Calendar.HOUR_OF_DAY) + ":"
				+ formatMonthOrDay(c.get(Calendar.MINUTE));

		return s;
	}

	public String getDateAsString(Context context) {
		String s = "";

		Calendar c = Calendar.getInstance();
		c.setTime(time);
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());

		if (c.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR)) {
			// Der gleiche Tag
			s = c.get(Calendar.HOUR_OF_DAY) + ":"
					+ formatMonthOrDay(c.get(Calendar.MINUTE)) + " "
					+ context.getString(R.string.time_oclock);

		} else if (c.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR) - 1) {
			// Gestern
			s = context.getResources().getString(R.string.time_yesterday);

		} else if (c.get(Calendar.WEEK_OF_YEAR) == now
				.get(Calendar.WEEK_OF_YEAR)) {
			// Die gleiche Woche
			s = context.getResources().getStringArray(
					R.array.time_daysoftheweek)[c.get(Calendar.DAY_OF_WEEK) - 1];

		} else {
			s = formatMonthOrDay(c.get(Calendar.DAY_OF_MONTH)) + "."
					+ formatMonthOrDay(c.get(Calendar.MONTH)) + "."
					+ c.get(Calendar.YEAR);
		}

		return s;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private String formatMonthOrDay(int i) {
		DecimalFormat df = new DecimalFormat("00");

		return df.format(i);
	}

	public boolean isEigeneNachricht() {
		return eigeneNachricht;
	}

	public void setEigeneNachricht(boolean eigeneNachricht) {
		this.eigeneNachricht = eigeneNachricht;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

}

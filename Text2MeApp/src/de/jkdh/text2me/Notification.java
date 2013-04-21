package de.jkdh.text2me;

import java.util.ArrayList;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.Html;
import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.conversation.ConversationActivity;
import de.jkdh.text2me.div.DivMethods;
import de.jkdh.text2me.message.UnreadMessage;

public class Notification {
	private final static int NOTIFICATION_ID = 500;
	private Context context;

	public Notification(Context context) {
		this.context = context;
	}

	public void create(ArrayList<UnreadMessage> unreadMessages) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		int anzahl = unreadMessages.size();
		String title = "";
		String text = "";
		String ticker = "";
		Intent openIntent;

		if (anzahl == 1) {
			title = unreadMessages.get(0).getConversation().getContact()
					.getName();

			text = unreadMessages.get(0).getMessage().getText();

			ticker = context.getString(R.string.notif_newMessage_ticker)
					+ " "
					+ unreadMessages.get(0).getConversation().getContact()
							.getName();

			openIntent = new Intent(context, ConversationActivity.class);
			openIntent.putExtra(DivMethods.EXTRA_CONVERSATION_ID,
					unreadMessages.get(0).getConversation().getId());
		} else {
			int anzahlKontakte = countContacts(unreadMessages);

			if (anzahlKontakte == 1) {
				title = unreadMessages.get(0).getConversation().getContact()
						.getName();

				text = unreadMessages.size() + " "
						+ context.getString(R.string.notif_newMessages);

				ticker = context.getString(R.string.notif_newMessages_ticker)
						+ " "
						+ unreadMessages.get(0).getConversation().getContact()
								.getName();

				openIntent = new Intent(context, ConversationActivity.class);
				openIntent.putExtra(DivMethods.EXTRA_CONVERSATION_ID,
						unreadMessages.get(0).getConversation().getId());
			} else {

				title = context.getString(R.string.notif_newMessages_title);

				text = unreadMessages.size() + " "
						+ context.getString(R.string.notif_newMessagesFrom)
						+ " " + anzahlKontakte + " "
						+ context.getString(R.string.notif_contacts);

				ticker = context.getString(R.string.notif_newMessages_ticker)
						+ " " + anzahlKontakte + " "
						+ context.getString(R.string.notif_contacts);
				openIntent = new Intent(context, MainActivity.class);
			}
		}

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		builder.setAutoCancel(true);
		builder.setContentTitle(title);
		builder.setContentText(text);
		builder.setNumber(anzahl);
		builder.setOngoing(true);
		builder.setPriority(NotificationCompat.PRIORITY_HIGH);
		builder.setSmallIcon(R.drawable.ic_notification_small);
		builder.setSound(Uri.parse(sharedPrefs.getString(
				"pref_notification_ringtone",
				"content://settings/system/notification_sound")));
		builder.setTicker(ticker);
		builder.setDefaults(android.app.Notification.DEFAULT_ALL);
		builder.addAction(R.drawable.ic_menu_markasread,
				context.getString(R.string.notif_action_markasread), null);
		builder.addAction(R.drawable.ic_menu_answer,
				context.getString(R.string.notif_action_answer), null);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

		stackBuilder.addParentStack(MainActivity.class);

		stackBuilder.addNextIntent(openIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(resultPendingIntent);

		NotificationCompat.Style style;

		if (anzahl > 1) {
			NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

			inboxStyle.setBigContentTitle(context.getString(R.string.app_name));

			for (int i = 0; i < unreadMessages.size(); i++) {
				inboxStyle.addLine(Html.fromHtml("<b>"
						+ unreadMessages.get(i).getConversation().getContact()
								.getName() + ":</b> "
						+ unreadMessages.get(i).getMessage().getText()));
			}
			style = inboxStyle;

		} else {

			NotificationCompat.BigTextStyle inboxStyle = new NotificationCompat.BigTextStyle();

			inboxStyle.setBigContentTitle(unreadMessages.get(0)
					.getConversation().getContact().getName());

			inboxStyle.bigText(unreadMessages.get(0).getMessage().getText());
			style = inboxStyle;
		}
		builder.setStyle(style);

		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(NOTIFICATION_ID, builder.build());
	}

	private int countContacts(ArrayList<UnreadMessage> unreadMessages) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();

		for (int i = 0; i < unreadMessages.size(); i++) {
			boolean b = true;
			for (int j = 0; j < contacts.size(); j++) {
				if (contacts.get(j).equals(
						unreadMessages.get(i).getConversation().getContact())) {
					b = false;
				}
			}
			if (b) {
				contacts.add(unreadMessages.get(i).getConversation()
						.getContact());
			}
		}

		return contacts.size();
	}
}

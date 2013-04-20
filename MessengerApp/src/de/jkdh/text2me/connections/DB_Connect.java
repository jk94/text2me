package de.jkdh.text2me.connections;

import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.conversation.Conversation;
import de.jkdh.text2me.manager.Control;
import de.jkdh.text2me.message.Message;

public class DB_Connect extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static final String DATABASE_NAME = "text2me.db";

	public static final class messageTBL implements BaseColumns {
		private static final String TABLE_NAME = "messages";
		private static final String CONVERSATIONID_NAME = "CONV_ID";
		private static final String TEXT_NAME = "text";
		private static final String DATETIME_NAME = "datetime";
		private static final String EIGENE_NACHRICHT = "eigene_nachricht";
	}

	public static final class conversationTBL implements BaseColumns {
		private static final String TABLE_NAME = "conversations";
		private static final String CONTACTID_NAME = "CONT_ID";
	}

	public static final class contactTBL implements BaseColumns {
		private static final String TABLE_NAME = "contacts";
		private static final String CONT_ID_NAME = "phone_cont_ID";
		private static final String TELNUMBER_NAME = "telnumber";
		private static final String STATUS_NAME = "status";
	}

	public DB_Connect(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlcommand = "CREATE TABLE " + messageTBL.TABLE_NAME + " ( "
				+ messageTBL._ID + " INTEGER PRIMARY KEY, "
				+ messageTBL.CONVERSATIONID_NAME + " INTEGER, "
				+ messageTBL.TEXT_NAME + " TEXT, " + messageTBL.DATETIME_NAME
				+ " LONG, " + messageTBL.EIGENE_NACHRICHT + " INTEGER)";
		executeSQLCommand(db, sqlcommand);
		log(sqlcommand);

		sqlcommand = "CREATE TABLE " + conversationTBL.TABLE_NAME + " ( "
				+ conversationTBL._ID + " INTEGER PRIMARY KEY, "
				+ conversationTBL.CONTACTID_NAME + " INTEGER UNIQUE)";
		executeSQLCommand(db, sqlcommand);
		log(sqlcommand);

		sqlcommand = "CREATE TABLE " + contactTBL.TABLE_NAME + " ("
				+ contactTBL._ID + " INTEGER PRIMARY KEY, "
				+ contactTBL.CONT_ID_NAME + " TEXT, "
				+ contactTBL.TELNUMBER_NAME + " BLOB, "
				+ contactTBL.STATUS_NAME + " TEXT)";
		executeSQLCommand(db, sqlcommand);
		log(sqlcommand);
	}

	private void log(String s) {
		Log.d("DB_Connect: ", s);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	private void executeSQLCommand(SQLiteDatabase db, String sqlcommand) {
		db.execSQL(sqlcommand);
	}

	public void addMessage(Message message, Conversation conversation) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(messageTBL.CONVERSATIONID_NAME, conversation.getId());
		values.put(messageTBL.TEXT_NAME, message.getText());
		values.put(messageTBL.DATETIME_NAME, message.getTime().getTime());
		values.put(messageTBL.EIGENE_NACHRICHT, message.isEigeneNachricht());

		long newRowId;
		newRowId = db.insert(messageTBL.TABLE_NAME, null, values);
		message.setId((int) newRowId);
	}

	public void addConversation(Conversation conversation, Contact contact) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(conversationTBL.CONTACTID_NAME, contact.getId());

		long newRowId;
		newRowId = db.insert(conversationTBL.TABLE_NAME, null, values);
		conversation.setId((int) newRowId);
	}

	public void addContact(Contact contact) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(contactTBL.CONT_ID_NAME, contact.getPhoneID());
		values.put(contactTBL.TELNUMBER_NAME, contact.getNumber());
		values.put(contactTBL.STATUS_NAME, contact.getStatus());

		long newRowId;
		newRowId = db.insert(contactTBL.TABLE_NAME, null, values);
		contact.setId((int) newRowId);
	}

	public ArrayList<Conversation> getConversations(Control control) {
		SQLiteDatabase db = getReadableDatabase();

		String[] projection = { conversationTBL._ID,
				conversationTBL.CONTACTID_NAME };

		Cursor c = db.query(conversationTBL.TABLE_NAME, projection, // The
																	// columns
																	// to return
				null, // The columns for the WHERE clause
				null, // The values for the WHERE clause
				null, // don't group the rows
				null, // don't filter by row groups
				null // The sort order
				);

		ArrayList<Conversation> cons = new ArrayList<Conversation>();

		while (c.moveToNext()) {
			int _id = c.getInt(0);
			Contact contact = control.getContactByID(c.getInt(1));
			ArrayList<Message> messages = getMessages(_id);
			Conversation con = new Conversation(_id, contact, messages);
			cons.add(con);
		}

		return cons;
	}

	public ArrayList<Contact> getContacts() {
		SQLiteDatabase db = getReadableDatabase();

		String[] projection = { contactTBL._ID, contactTBL.CONT_ID_NAME,
				contactTBL.TELNUMBER_NAME, contactTBL.STATUS_NAME };

		Cursor c = db.query(contactTBL.TABLE_NAME, projection, null, null,
				null, null, null);

		ArrayList<Contact> contacts = new ArrayList<Contact>();

		while (c.moveToNext()) {
			int _id = c.getInt(0);
			int phoneID = c.getInt(1);
			String name = "----"; // c.getString(2);
			String telnumber = c.getString(2);
			String status = c.getString(3);
			Contact contact = new Contact(_id, phoneID, name, telnumber,
					status, "");
			contacts.add(contact);
		}

		return contacts;
	}

	public ArrayList<Message> getMessages(int _id) {
		SQLiteDatabase db = getReadableDatabase();

		String[] projection = { messageTBL._ID, messageTBL.TEXT_NAME,
				messageTBL.DATETIME_NAME, messageTBL.EIGENE_NACHRICHT };

		Cursor c = db.query(messageTBL.TABLE_NAME, projection,
				messageTBL.CONVERSATIONID_NAME + " LIKE ?",
				new String[] { String.valueOf(_id) }, null, null,
				messageTBL.DATETIME_NAME + " ASC");

		ArrayList<Message> messages = new ArrayList<Message>();

		while (c.moveToNext()) {
			int m_id = c.getInt(0);
			String text = c.getString(1);
			Date timestamp = new Date(c.getLong(2));
			boolean eigeneNachricht = c.getInt(3) > 0;
			Message m = new Message(m_id, text, timestamp.getTime(),
					eigeneNachricht);
			messages.add(m);
		}

		return messages;
	}

	public void removeContact(Contact contact) {
		SQLiteDatabase db = getWritableDatabase();

		db.delete(contactTBL.TABLE_NAME, contactTBL.CONT_ID_NAME,
				new String[] { String.valueOf(contact.getId()) });
	}

	public void closeDB() {
		SQLiteDatabase db = getReadableDatabase();

		db.close();
	}
	// public Contact getContact(int _id) {
	// SQLiteDatabase db = getReadableDatabase();
	//
	// String[] projection = { contactTBL._ID, contactTBL.NAME_NAME,
	// contactTBL.TELNUMBER_NAME, contactTBL.STATUS_NAME };
	//
	// Cursor c = db.query(contactTBL.TABLE_NAME, projection, contactTBL._ID
	// + " LIKE ?", new String[] { String.valueOf(_id) }, null, null,
	// null);
	//
	// c.moveToFirst();
	// String name = c.getString(1);
	// String telnumber = c.getString(2);
	// String status = c.getString(3);
	//
	// Contact contact = new Contact(_id, name, telnumber, status, "");
	//
	// return contact;
	// }
}

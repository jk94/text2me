package de.jkdh.text2me.manager;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import de.jkdh.text2me.contact.Contact;
import de.jkdh.text2me.div.beispielwerte;

public class ContactManager {
	private static final String TAG = ContactManager.class.getSimpleName();
	private Control theControl;
	private ArrayList<Contact> ContactsList;
	private Context context;

	class PhoneContact {

		int contactID;
		String forename, surname, shownname;
		List<ArrayList<String>> number;
		Bitmap contactPicture;

		public PhoneContact(int contactID, String forename, String surname,
				String shownname, List<ArrayList<String>> number,
				Bitmap contactPicture) {
			this.contactID = contactID;
			this.forename = forename;
			this.surname = surname;
			this.shownname = shownname;
			this.number = number;
			this.contactPicture = contactPicture;
		}

		public int getContactID() {
			return contactID;
		}

		public String getForename() {
			return forename;
		}

		public String getSurname() {
			return surname;
		}

		public String getShownname() {
			return shownname;
		}

		public List<ArrayList<String>> getNumber() {
			return number;
		}

		public ArrayList<String> getNumber(int index) {
			if (index < number.size()) {
				return number.get(index);
			} else {
				return null;
			}
		}

		public Bitmap getContactPicture() {
			return contactPicture;
		}

		public void setContactID(int contactID) {
			this.contactID = contactID;
		}

		public void setForename(String forename) {
			this.forename = forename;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public void setShownname(String shownname) {
			this.shownname = shownname;
		}

		public void setNumber(List<ArrayList<String>> number) {
			this.number = number;
		}

		public void setContactPicture(Bitmap contactPicture) {
			this.contactPicture = contactPicture;
		}

	}

	public ArrayList<PhoneContact> getPhoneContacts() {
		ArrayList<ContactManager.PhoneContact> erg = new ArrayList<ContactManager.PhoneContact>();

		final String[] GET_COLUMNS = { Contacts._ID, Contacts.DISPLAY_NAME, TAG };

		ContentResolver contentResolver = context.getContentResolver();

		String Selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = ?";

		String[] SelectionArgs = new String[] { "1" };

		Cursor mainQueryCursor = contentResolver.query(
				ContactsContract.Contacts.CONTENT_URI, GET_COLUMNS, Selection,
				SelectionArgs, null);

		while (mainQueryCursor.moveToNext()) {
			String contactId = mainQueryCursor.getString(0);
			String displayName = mainQueryCursor.getString(1);

			erg.add(new PhoneContact(Integer.parseInt(contactId), "", "",
					displayName, null, null));

			log("===> " + erg.get(erg.size() - 1).getShownname() + " ("
					+ erg.get(erg.size() - 1).getContactID() + ")");
		}

		mainQueryCursor.close();

		return erg;
	}

	public ContactManager(Control theControl, Context context) {
		this.ContactsList = new ArrayList<Contact>();
		this.theControl = theControl;
		this.context = context;
	}

	public ContactManager(Control theControl, Context context,
			ArrayList<Contact> Contacts) {
		this.ContactsList = Contacts;
		this.theControl = Control.getControl();
		this.context = context;
	}

	public Contact addContact(Contact contact) {
		this.ContactsList.add(contact);
		this.theControl.getDatabaseConnection().addContact(contact);
		this.theControl.addContactOnUIs(contact);
		return contact;
	}

	public Contact getContactByPhoneID(int phoneID) {
		Contact con = null;

		for (int i = 0; i < ContactsList.size(); i++) {
			if (ContactsList.get(i).getPhoneID() == phoneID) {
				con = ContactsList.get(i);
			}
		}
		return con;
	}

	public void removeNotPhonesavedContactsFromDB(
			ArrayList<Integer> PhoneContactIDs) {
		ArrayList<Contact> contacts = this.theControl.getDatabaseConnection()
				.getContacts(); // Liest alle Kontakte aus der DB

		for (int i = 0; i < contacts.size(); i++) { // für jeden Kontakt aus der
													// DB
			boolean found = false;
			int i2 = 0;
			while (i2 < PhoneContactIDs.size() || !found) { // Mache solange wie
															// noch nicht jeder
															// Kontakt im
															// Telefon
															// durchgelaufen
															// ODER bereits
															// gefunden
				if (contacts.get(i).getPhoneID() == PhoneContactIDs.get(i2)) {
					found = true;
				}
				i2++;
			}
			if (!found) { // Wenn nach der Schleife nicht gefunden -> aus DB
							// löschen
				this.theControl.getDatabaseConnection().removeContact(
						contacts.get(i));
			}
		}
	}

	public void removeNotOnlineregisteredContacts(
			ArrayList<Integer> PhoneContactIDs) {
		// Connection conn = this.theControl.getConnection();
		beispielwerte bspwert = new beispielwerte();
		bspwert.create(ContactsList);

		// GROOOOßER DENKFEHLER MEINERSEITS!! °.°
		/*
		 * for (int i = 0; i < PhoneContactIDs.size(); i++) { if (ContactsList
		 * != null) { if (bspwert.istT2MNummer() {
		 * 
		 * } else { PhoneContactIDs.remove(i); } } }
		 */
		log("-- Übrige Kontakte --");
		for (int i = 0; i < PhoneContactIDs.size(); i++) {
			log("" + PhoneContactIDs.get(i));
		}

	}

	private void log(String s) {
		// Log.d(TAG, s);
	}

}

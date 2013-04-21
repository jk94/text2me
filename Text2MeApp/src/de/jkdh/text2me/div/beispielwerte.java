package de.jkdh.text2me.div;

import java.util.ArrayList;
import de.jkdh.text2me.contact.Contact;

public final class beispielwerte {

	public ArrayList<Contact> KontakteMitT2M = new ArrayList<Contact>();

	public void create(ArrayList<Contact> ctclst) {
		final Contact c1 = new Contact(-1, 58, "Mark G.", "+4915773096109",
				"ZUFALL..\nDieser Status ist völlig zufällig gewählt!!1!",
				"mobil");
		final Contact c2 = new Contact(-1, 59, "Laura F.", "+4915777901233",
				"Ein schönen Status habe ich", "mobil");
		final Contact c3 = new Contact(-1, 60, "Pia F.", "+491778735443",
				"Ich habe einen Status. Du auch?", "mobil");

		KontakteMitT2M.add(c1);
		KontakteMitT2M.add(c2);
		KontakteMitT2M.add(c3);
		ctclst.add(c1);
		ctclst.add(c2);
		ctclst.add(c3);
	}

	public ArrayList<Contact> getKontakteMitT2M() {
		return KontakteMitT2M;
	}

	// Methode für Server zum Überprüfen ob Nummer in Text2Me-Datenbank
	public final boolean istT2MNummer(String number) {
		Boolean erg = false;

		for (int i = 0; i < KontakteMitT2M.size(); i++) {
			if (KontakteMitT2M.get(i).getNumber().equals(number)) {
				erg = true;
			}
		}
		return erg;
	}

}

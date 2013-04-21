package de.jkdh.text2me.div;

import android.util.Log;

public abstract class DivMethods {

	public static final String EXTRA_CONTACT_ID = "contact_id";
	public static final String EXTRA_CONVERSATION_ID = "conversation_id";

	public static boolean haveOnlySpace(String text) {
		boolean erg = true;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) != ' ') {
				erg = false;
			}
		}
		return erg;
	}

	public static void removeAllEnframingSpaces(String txt) {
		String erg = txt;

		while (erg.startsWith(" ") && !erg.equals("")) {
			erg.replaceFirst(" ", "");
		}

		while (erg.endsWith(" ") && !erg.equals("")) {
			replaceLast(erg, "");
		}
		txt = erg;
	}

	public static void replaceLast(String text, String newCharSequenz) {
		String erg = "";
		for (int i = 0; (i - 1) < text.length(); i++) {
			erg = erg + text.charAt(i);
		}
		erg = erg + newCharSequenz;
		text = erg;
	}

	public static void log(String sender, String s) {
		Log.d(sender, s);
	}
}

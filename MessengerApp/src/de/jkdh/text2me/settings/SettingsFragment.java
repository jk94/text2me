package de.jkdh.text2me.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import de.jkdh.text2me.R;

public class SettingsFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.preferences);
	}
}
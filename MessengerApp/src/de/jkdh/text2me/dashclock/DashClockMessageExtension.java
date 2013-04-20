package de.jkdh.text2me.dashclock;

import java.util.ArrayList;

import android.content.Intent;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import de.jkdh.text2me.MainActivity;
import de.jkdh.text2me.R;
import de.jkdh.text2me.message.UnreadMessage;

public class DashClockMessageExtension extends DashClockExtension {
	private static DashClockMessageExtension extension = null;
	private static ArrayList<UnreadMessage> unreadMessages = null;

	@Override
	protected void onUpdateData(int reason) {
		if (unreadMessages == null) {
			unreadMessages = new ArrayList<UnreadMessage>();
		}

		ExtensionData data = new ExtensionData();
		if (unreadMessages.size() > 0) {
			data.icon(R.drawable.ic_dashclock_icon)
					.clickIntent(
							new Intent(extension.getBaseContext(),
									MainActivity.class)).visible(true)
					.status(getString(R.string.app_name))
					.expandedBody("blabla").expandedTitle("blub");
		} else {
			data.visible(false);
		}
		extension.publishUpdate(data);
	}

	@Override
	public void onInitialize(boolean isReconnect) {
		super.onInitialize(isReconnect);
		extension = this;
	}

	public static void setUnreadMessages(ArrayList<UnreadMessage> messages) {
		unreadMessages = messages;
		if (extension != null) {
			extension
					.onUpdateData(DashClockExtension.UPDATE_REASON_SETTINGS_CHANGED);

		}
	}
}

package com.almesh.enthusia;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class GCMBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("GCM", "In Receive");
		String action = intent.getAction();
		if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
			final String update = intent.getStringExtra("message");
			// TODO Send this to my application server to get the real data
			// Lets make something visible to show that we received the message
			createNotification(context, update);
			
		}
	}

	public static void createNotification(Context context, String update) {
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher,"Enthusia Updates", System.currentTimeMillis());
		// Hide the notification after its selected
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.ipl_stadium );
		Intent intent = new Intent(context, EnthusiaActivity.class);
		Bundle b = new Bundle();
		b.putString("updates",update );
		intent.putExtras(b);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,intent, 0);
		notification.setLatestEventInfo(context, "Update",update, pendingIntent);
		notificationManager.notify(0, notification);

	}

}
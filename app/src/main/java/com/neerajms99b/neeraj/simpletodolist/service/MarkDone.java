package com.neerajms99b.neeraj.simpletodolist.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;

import com.neerajms99b.neeraj.simpletodolist.R;
import com.neerajms99b.neeraj.simpletodolist.data.TodoContentProvider;

/**
 * Created by neeraj on 7/1/17.
 */

public class MarkDone extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int notifId = intent.getIntExtra(context.getString(R.string.key_notification_id), 0);
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notifId);
        Uri uri = Uri.parse(TodoContentProvider.uriTodo.toString() + "/" + notifId);
        context.getContentResolver().delete(uri, null, null);
        Intent broadcastIntent = new Intent();
        intent.setAction(context.getString(R.string.key_intent_filter_mark_done));
        intent.putExtra(context.getString(R.string.key_notification_id), notifId);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}

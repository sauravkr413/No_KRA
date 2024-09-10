package com.example.nokra;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("test", "on Receive: ");
        Intent serviceIntent = new Intent(context, MyActivityService.class);
        context.startService(serviceIntent);
    }
}
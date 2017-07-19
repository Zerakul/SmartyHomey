package com.homey.smarty.smartyhomey.Managers;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.homey.smarty.smartyhomey.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Zerakul on 7/19/2017.
 */

public class NotifierManager {

    private Context cont;

    public NotifierManager(Context cont) {
        this.cont = cont;
    }

    public void newNotification(String title, String text){

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(cont)
                        .setSmallIcon(R.mipmap.smart_home_logo)
                        .setContentTitle(title)
                        .setContentText(text);



        NotificationManager mNotifyMgr =
                (NotificationManager) cont.getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(001, mBuilder.build());
    }






}

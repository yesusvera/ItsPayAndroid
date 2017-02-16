package itspay.br.com.util.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import static android.support.design.R.attr.icon;

/**
 * Created by juniorbraga on 15/02/17.
 */

public class CustomNotification {

    private static CustomNotification notification = new CustomNotification();

    public static CustomNotification getInstance(){
        if(notification==null){
            notification = new CustomNotification();
        }
        return notification;
    }

    public void notificationBuilder(Context mContext, int icone , int color , String title , String description) {

        Intent intent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(mContext);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        b.setSmallIcon(icone).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(contentIntent)
                .setStyle(inboxStyle)
                .setSmallIcon(icone)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentText(description)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent);


        b.setColor(mContext.getColor(color));
        b.setVisibility(Notification.VISIBILITY_PUBLIC);
        b.setFullScreenIntent(null, true);

        NotificationManager notificationManager2 = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager2.notify(0, b.build());

        notifyToWear(mContext,icone,color,title,description);
    }

    public void notificationBuilder(Context mContext, int icone , String title , String description) {

        Intent intent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(mContext);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        b.setSmallIcon(icone).setTicker(title).setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(contentIntent)
                .setStyle(inboxStyle)
                .setSmallIcon(icone)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentText(description)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent);


        b.setVisibility(Notification.VISIBILITY_PUBLIC);
        b.setFullScreenIntent(null, true);

        NotificationManager notificationManager2 = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager2.notify(0, b.build());

        notifyToWear(mContext,icone,title,description);
    }

    private void notifyToWear( Context mContext ,int icone, int color,String title, String description) {


        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintShowBackgroundOnly(true);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(icone)
                .setColor(mContext.getColor(color))
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentTitle(title)
                .setContentText(description)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .extend(wearableExtender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void notifyToWear( Context mContext ,int icone, String title, String description) {


        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender()
                .setHintShowBackgroundOnly(true);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(icone)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentTitle(title)
                .setContentText(description)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .extend(wearableExtender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(0, notificationBuilder.build());
    }


}

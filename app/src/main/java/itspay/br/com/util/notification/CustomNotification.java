package itspay.br.com.util.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import itspay.br.com.activity.MarketPlaceActivity;
import itspay.br.com.itspay.R;

import static android.support.design.R.attr.icon;
import static android.support.design.R.attr.paddingEnd;

/**
 * Created by juniorbraga on 15/02/17.
 */

public class CustomNotification {

    private static CustomNotification notification = new CustomNotification();
    long[] v = {500,1000};
    Intent intent;

    public static CustomNotification getInstance(){
        if(notification==null){
            notification = new CustomNotification();
        }
        return notification;
    }

    public void notificationBuilder(Context mContext, int icone , int color , String title , String description ) {

        Intent intent = new Intent(mContext, MarketPlaceActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(mContext);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        b.setSmallIcon(icone).setTicker(title).setWhen(0)
                .setContentTitle(title)
                .setSmallIcon(icone)
                .setContentText(description)
                .setStyle(inboxStyle)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent);


        b.setColor(mContext.getColor(color));
        b.setVisibility(Notification.VISIBILITY_PUBLIC);
        b.setFullScreenIntent(null, true);

        b.addAction(R.drawable.cart, "Loja", contentIntent);


        NotificationManager notificationManager2 = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager2.notify(0, b.build());

        notifyToWear(mContext,icone,color,title,description, contentIntent, b);
    }

    private void notifyToWear( Context mContext ,int icone, int color,String title, String description ,PendingIntent contentIntent, NotificationCompat.Builder b) {


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
                .setVibrate(v)
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_ALL);


        wearableExtender.addAction(new NotificationCompat.Action.Builder(R.drawable.cart, "Loja", contentIntent).build());
        notificationBuilder.extend(wearableExtender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(0, notificationBuilder.build());
    }

    private void notifyToWear(Context mContext, int i, int icone, String title, String description, PendingIntent contentIntent) {



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
                .setVibrate(v)
                .extend(wearableExtender);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        notificationManager.notify(0, notificationBuilder.build());
    }


}

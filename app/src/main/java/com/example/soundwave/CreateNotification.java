package com.example.soundwave;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CreateNotification {
    public static final String CHANNEL_ID = "channel1";

    public static Notification notification;

    public static void createNotification(Context context, Song song, int playButton, int pos, int size){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), song.getPictureID());

            notification = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.ic_baseline_music_note_24).setContentTitle(song.getSongName()).setContentText(song.getArtist()).setLargeIcon(icon).setOnlyAlertOnce(true).setShowWhen(false).setPriority(NotificationCompat.PRIORITY_LOW).build();
            notificationManagerCompat.notify(1,notification);
        }
    }
}

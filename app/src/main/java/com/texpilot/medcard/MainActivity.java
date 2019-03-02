package com.texpilot.medcard;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // open new card
        Button btnNew =findViewById(R.id.btnNew);
        btnNew.setOnClickListener(openNewCard);

        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(Notifications);

        Button btnArchive = findViewById(R.id.btnArchive);
        btnArchive.setOnClickListener(openArchiveCard);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        String channel_name = "medcard";
        String channel_description = "medcard channel";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            channel = notificationManager.getNotificationChannel(CHANNEL_ID);

            //channel_description = channel.getDescription();


        }
    }


    // create notification
    private void Notify() {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        //notificationManager.deleteNotificationChannel(CHANNEL_ID);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("notification title")
                .setContentText("notification content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Integer notificationId=9;

        notificationManager.notify(notificationId, builder.build());
    }

    // open new card activity
    private View.OnClickListener openNewCard = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, mEditCard.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener Notifications = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            createNotificationChannel();
            Notify();
        }
    };

    private View.OnClickListener clearDB = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase database = new MedCardDBSQLiteHelper(view.getContext()).getWritableDatabase();
            String query = "delete from MedCard";
            database.rawQuery(query, null);
            database.delete("MedCard", "", null);
            // delete db - delete the file

        }
    };

    private void ViewDismissCard() {
        Integer MedCardId = 1;      // for testing only
        Intent intent = new Intent(MainActivity.this, mViewDismissCard.class);
        intent.putExtra("MedCardId", MedCardId);
        startActivity(intent);
    };

    private View.OnClickListener openArchiveCard = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewDismissCard();
            return;/*
            SQLiteDatabase database = new MedCardDBSQLiteHelper(view.getContext()).getReadableDatabase();
            String query = "select * from MedCard where MedCardId=1";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            Integer MedCardId;
            Integer CardContentId;
            while (!cursor.isAfterLast()) {
                MedCardId = cursor.getInt(0);

                cursor.moveToNext();
            }

            query = "select * from CardContent where MedCardId=2";
            try {
                cursor = database.rawQuery(query, null);
            }
            catch ( Exception e) {
                Toast.makeText(view.getContext(), "Date is in the wrong format", Toast.LENGTH_LONG).show();
                return;
            }

            cursor.moveToFirst();
            String K;
            String V;
            while (!cursor.isAfterLast()) {
                CardContentId = cursor.getInt(0);
                MedCardId = cursor.getInt(1);
                K = cursor.getString(2);
                V = cursor.getString(3);
                cursor.moveToNext();
            }
            cursor.close();
            */
        }
    };
}

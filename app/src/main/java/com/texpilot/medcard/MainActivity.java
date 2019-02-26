package com.texpilot.medcard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // open new card
        Button btnNew =findViewById(R.id.btnNew);
        btnNew.setOnClickListener(openNewCard);

        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(clearDB);

        Button btnArchive = findViewById(R.id.btnArchive);
        btnArchive.setOnClickListener(openArchiveCard);
    }

    // open About activity
    private View.OnClickListener openNewCard = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, mEditCard.class);
            startActivity(intent);
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

    private View.OnClickListener openArchiveCard = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
        }
    };
}

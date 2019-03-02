package com.texpilot.medcard;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class mViewDismissCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_view_dismiss_card);
        Intent intent = getIntent(); // gets the previously created intent
        Integer MedCardId;
        MedCardId = intent.getIntExtra("MedCardId", -1);
        // show card
        if (MedCardId != -1){
            ShowCard(MedCardId);
        }
    }

    private void ShowCard(Integer MedCardId) {
        SQLiteDatabase database = new MedCardDBSQLiteHelper(this).getReadableDatabase();
        String query = "select * from CardContent where MedCardId=" + MedCardId.toString() + " order by CardContentId";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        try {
            cursor = database.rawQuery(query, null);
        }
        catch ( Exception e) {
            Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
            return;
        }
        // read card content
        cursor.moveToFirst();
        String K;
        String V;
        Integer idx = 0;
        while (!cursor.isAfterLast()) {
            K = cursor.getString(2);
            V = cursor.getString(3);
            cursor.moveToNext();
            idx++;
        }
        cursor.close();
    };
}

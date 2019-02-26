package com.texpilot.medcard;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class mEditCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_edit_card);

        Button btnNew =findViewById(R.id.btnSave);
        btnNew.setOnClickListener(SavetoDBListener);
    }

    // open About activity
    private View.OnClickListener SavetoDBListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SavetoDB();
        }
    };

    private void SavetoDB(){
        SQLiteDatabase database = new MedCardDBSQLiteHelper(this).getWritableDatabase();
        Long MedCardId;
        long date;
        ContentValues values = new ContentValues();
        // insert MedCardId
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S");
            String result = sdf.format(Calendar.getInstance().getTime());

            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    result));
            date = calendar.getTimeInMillis();

            values.put(MedCardDBContract.MedCard.COLUMN_CREATEDDATE, date);
            long newRowId = database.insert(MedCardDBContract.MedCard.TABLE_NAME, null, values);
            MedCardId = newRowId;
        }
        catch (Exception e) {

            Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
            return;
        }
        // if MedCardId insert is successful, then insert pairs of key->value first row
        values = new ContentValues();
        Integer i = 0;
        TextView eKey = findViewById(R.id.eKey1);
        TextView eVal = findViewById(R.id.eVal1);
        String K = eKey.getText().toString();
        String V = eVal.getText().toString();
        if (K != "" && V != "") {
            values.put(MedCardDBContract.CardContent.COLUMN_MEDCARDID,MedCardId);
            values.put(MedCardDBContract.CardContent.COLUMN_KEY, K);
            values.put(MedCardDBContract.CardContent.COLUMN_VALUE, V);
            values  .put(MedCardDBContract.CardContent.COLUMN_CREATEDDATE, date);
        }
        // second row
        eKey = findViewById(R.id.eKey2);
        eVal = findViewById(R.id.eVal2);
        K = eKey.getText().toString();
        V = eVal.getText().toString();
        if (K != "" && V != "") {
            values.put(MedCardDBContract.CardContent.COLUMN_MEDCARDID,MedCardId);
            values.put(MedCardDBContract.CardContent.COLUMN_KEY, K);
            values.put(MedCardDBContract.CardContent.COLUMN_VALUE, V);
            values  .put(MedCardDBContract.CardContent.COLUMN_CREATEDDATE, date);
        }

        // write to the DB
        try {
            Long newRowId = database.insert(MedCardDBContract.CardContent.TABLE_NAME, null,values);
        }
        catch ( Exception e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            return;
        }
    };
}

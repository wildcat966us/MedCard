package com.texpilot.medcard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.view.View;

public class MedCardDBSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "MedPilotDictionary";

    public MedCardDBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MedCardDBContract.MedCard.CREATE_TABLE);
        sqLiteDatabase.execSQL(MedCardDBContract.CardContent.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MedCardDBContract.CardContent.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MedCardDBContract.MedCard.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

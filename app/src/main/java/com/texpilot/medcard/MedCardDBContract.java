package com.texpilot.medcard;

import android.provider.BaseColumns;

public final class MedCardDBContract {
    // define a private constructor for MedCardDBContract
    // so that it won’t be accidentally instantiated
    private  MedCardDBContract(){};

    public static class MedCard implements BaseColumns {
        public static final String TABLE_NAME = "MedCard";
        public static final String COLUMN_MEDCARDID = "MedCardId";
        public static final String COLUMN_CREATEDDATE = "CreatedDate";
        public static final String COLUMN_MODIFIEDDATE = "ModifiedDate";
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                COLUMN_MEDCARDID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                COLUMN_CREATEDDATE + " INTEGER NOT NULL, " +
                COLUMN_MODIFIEDDATE + " INTEGER" + ")";
    }

    public static class CardContent implements BaseColumns {
        public static final String TABLE_NAME = "CardContent";
        public static final String COLUMN_CARDCONTENTID = "CardContentId";
        public static final String COLUMN_MEDCARDID = "MedCardId";
        public static final String COLUMN_KEY = "Key";
        public static final String COLUMN_VALUE = "Value";
        public static final String COLUMN_CREATEDDATE = "CreatedDate";
        public static final String COLUMN_MODIFIEDDATE = "ModifiedDate";
        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_CARDCONTENTID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                COLUMN_MEDCARDID + " INTEGER, " +
                COLUMN_KEY + " TEXT, " +
                COLUMN_VALUE + " TEXT, " +
                COLUMN_CREATEDDATE + " INTEGER NOT NULL, " +
                COLUMN_MODIFIEDDATE + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_MEDCARDID + ") REFERENCES " +
                MedCard.TABLE_NAME + "(" + MedCard.COLUMN_MEDCARDID + ") " + ")";
    }
}

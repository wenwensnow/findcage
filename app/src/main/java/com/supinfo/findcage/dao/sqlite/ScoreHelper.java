package com.supinfo.findcage.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 2017/4/1.
 */

public class ScoreHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ScoreList";
    public static final String TABLE_NAME ="scores";
    public static final String KEY_ID ="id";
    public static final String KEY_STR = "name";
    public static final String KEY_SCORE = "score";

    public ScoreHelper(Context context) {
        super(context, TABLE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Scores_Table ="CREATE TABLE " + TABLE_NAME
                + "(" + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_STR +" TEXT," + KEY_SCORE
                +" INTEGER" + ")";
        db.execSQL(Create_Scores_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}

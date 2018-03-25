package com.supinfo.findcage.dao.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.supinfo.findcage.dao.ScoreDao;
import com.supinfo.findcage.mode.Score;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.supinfo.findcage.dao.sqlite.ScoreHelper.KEY_SCORE;
import static com.supinfo.findcage.dao.sqlite.ScoreHelper.KEY_STR;
import static com.supinfo.findcage.dao.sqlite.ScoreHelper.TABLE_NAME;

/**
 * Created by dell on 2017/4/1.
 */

public class SQLIteScoreDao implements ScoreDao {
    private SQLiteDatabase db;
    private ScoreHelper myHelper;
    public SQLIteScoreDao (Context context) {
        myHelper = new ScoreHelper(context);
    }
    @Override
    public void insertScore(Score score) {
        db = myHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STR,score.getName());
        values.put(KEY_SCORE,score.getScore());
        db.insert(TABLE_NAME,null,values);
    }


    @Override
    public List<Score> getAllQuotes() {

        List<Score> scoreList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+ KEY_SCORE;
        db = myHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst())
        {
            do {
                Score myscore = new Score( );
                myscore.setId(Integer.parseInt(cursor.getString(0)));
                myscore.setName(cursor.getString(1));
                myscore.setScore(cursor.getInt(2));
                scoreList.add(myscore);
            }while (cursor.moveToNext());
        }
        return scoreList;
    }
}

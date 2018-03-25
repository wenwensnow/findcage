package com.supinfo.findcage.dao;

import com.supinfo.findcage.mode.Score;

import java.util.List;

/**
 * Created by dell on 2017/4/1.
 */

public interface ScoreDao {
    void insertScore (Score score);
    List<Score> getAllQuotes();
}

package com.supinfo.findcage.mode;

import java.io.Serializable;

/**
 * Created by dell on 2017/4/1.
 */

public class Score implements Serializable{

    int id;
    String name;
    int score;
    public Score(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Score() {
    }

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

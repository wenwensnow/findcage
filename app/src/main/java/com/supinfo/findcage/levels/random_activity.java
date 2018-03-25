package com.supinfo.findcage.levels;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.supinfo.findcage.R;
import com.supinfo.findcage.dao.sqlite.SQLIteScoreDao;

import com.supinfo.findcage.mode.Score;
import com.supinfo.findcage.touchlistener.Touch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class random_activity extends Activity {
    private ImageView iv_picture;
    private TextView tv_timer;
    long startTime = 0;
    int imageNum;
    private Touch myTouch;
    Handler timerHandler = new Handler();
    Runnable timerRunnable;
    private SQLIteScoreDao myscoreDao;
    private ArrayList<Score> scoreArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_activity);
        myscoreDao = new SQLIteScoreDao(random_activity.this);
        iv_picture = (ImageView) findViewById(R.id.iv_picture);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        Random r = new Random();
        int i1 = r.nextInt(3 - 1) + 1;

        if (i1 == 2) {
            iv_picture.setImageResource(R.drawable.n);
            imageNum = 1;
        } else {
            iv_picture.setImageResource(R.drawable.nicolas);
            imageNum = 2;
        }

        myTouch = new Touch(imageNum);
        iv_picture.setOnTouchListener(myTouch);
    }

    @Override
    protected void onStart() {
        super.onStart();

        startTime = System.currentTimeMillis();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv_timer.setText(String.format("%d:%02d", minutes, seconds));
                final int total_seconds = minutes * 60 + seconds;
                timerHandler.postDelayed(this, 500);
                if (myTouch.result == 1) {

                    System.out.println("you stop it");
                    timerHandler.removeCallbacks(timerRunnable);

                    final Dialog alertDialog = new Dialog(random_activity.this);
                    alertDialog.setContentView(R.layout.custom);
                    alertDialog.setTitle("Enter Your Name here");
                    alertDialog.show();
                    Button btn_ok = (Button)alertDialog.findViewById(R.id.dialogButtonOK);
                    final EditText et_name = (EditText)alertDialog.findViewById(R.id.text);
                    TextView totaltime =(TextView)alertDialog.findViewById(R.id.tv_total_time);
                    totaltime.setText("You use "+tv_timer.getText() +" to find Nicolas Cage");
                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myscoreDao.insertScore(new Score(et_name.getText().toString(),total_seconds));
                            List<Score> myscoreDaoAllQuotes = myscoreDao.getAllQuotes();
                            for (Score i:myscoreDaoAllQuotes) {
                                scoreArrayList.add(i);
                            }
                            alertDialog.dismiss();
                            Intent myIntent = new Intent(random_activity.this,wall_of_fame.class);
                            myIntent.putExtra("ScoreListExtra", scoreArrayList);
                            startActivity(myIntent);
                        }
                    });
                }
                else if (myTouch.result ==2)
                {

                }
            }};
        timerHandler.postDelayed(timerRunnable, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
    }

}

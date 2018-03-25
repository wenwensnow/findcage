package com.supinfo.findcage.levels;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.supinfo.findcage.R;
import com.supinfo.findcage.mainpage.level_choose;
import com.supinfo.findcage.mode.Score;
import com.supinfo.findcage.touchlistener.Touch;

import java.util.List;

public class basic_start extends AppCompatActivity {
    private ImageView iv_basic1;
    private TextView tv_basix1;
    private Button back_to_level;
    int imageNum=1;
    long startTime = 0;
    private Touch myTouch;
    Handler timerHandler = new Handler();
    Runnable timerRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_start);
        iv_basic1 = (ImageView)findViewById(R.id.iv_basic1);
        tv_basix1 =(TextView)findViewById(R.id.tv_basic1);
        back_to_level = (Button)findViewById(R.id.btn_back_to_level);
        iv_basic1.setImageResource(R.drawable.n);
        myTouch = new Touch(imageNum);
        iv_basic1.setOnTouchListener(myTouch);
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
                tv_basix1.setText(String.format("%d:%02d", minutes, seconds));
                timerHandler.postDelayed(this, 500);
                if(myTouch.result==1)
                {
                    timerHandler.removeCallbacks(timerRunnable);
                    final Dialog alertDialog = new Dialog(basic_start.this);
                    alertDialog.setContentView(R.layout.custom);
                    alertDialog.show();
                    Button btn_ok = (Button)alertDialog.findViewById(R.id.dialogButtonOK);
                    EditText text = (EditText)alertDialog.findViewById(R.id.text);
                    text.setVisibility(View.INVISIBLE);
                    TextView totaltime =(TextView)alertDialog.findViewById(R.id.tv_total_time);
                    totaltime.setText("You use "+ tv_basix1.getText() +" to find Nicolas Cage");
                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                            Intent myIntent = new Intent(basic_start.this,level_choose.class);
                            startActivity(myIntent);
                        }
                    });

                }
            }
        };
        timerHandler.postDelayed(timerRunnable, 0);

    }
}

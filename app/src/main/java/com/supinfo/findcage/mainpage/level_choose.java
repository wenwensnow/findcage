package com.supinfo.findcage.mainpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.supinfo.findcage.R;
import com.supinfo.findcage.levels.basic_start;
import com.supinfo.findcage.levels.random_activity;

public class level_choose extends AppCompatActivity {
     private Button btn_random,btn_basic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_choose);
        btn_random = (Button) findViewById(R.id.btn_random);
        btn_basic = (Button)findViewById(R.id.btn_basix);

        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(level_choose.this,random_activity.class);
                startActivity(myIntent);
            }
        });
        btn_basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(level_choose.this,basic_start.class);
                startActivity(myIntent);
            }
        });

    }
}

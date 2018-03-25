package com.supinfo.findcage.mainpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.supinfo.findcage.R;

public class MainActivity extends AppCompatActivity {
   private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent myIntent = new Intent(MainActivity.this,level_choose.class);
                  startActivity(myIntent);
            }
        });
    }
}

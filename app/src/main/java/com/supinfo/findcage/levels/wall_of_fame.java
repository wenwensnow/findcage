package com.supinfo.findcage.levels;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.supinfo.findcage.R;
import com.supinfo.findcage.adapter.ScoreAdapter;
import com.supinfo.findcage.mainpage.level_choose;
import com.supinfo.findcage.mode.Score;

import java.util.ArrayList;
import java.util.List;

public class wall_of_fame extends AppCompatActivity {
    private List<Score>ScoreList = new ArrayList<>();
    private ListView lv_display;
    private ScoreAdapter adapter;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_wall_of_fame);
        ScoreList = (ArrayList<Score>)getIntent().getSerializableExtra("ScoreListExtra");
        lv_display = (ListView)findViewById(R.id.lv_display);
        adapter = new ScoreAdapter(this,ScoreList);
        lv_display.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wall_of_fame.this,level_choose.class);
                startActivity(intent);
            }
        });
    }
}

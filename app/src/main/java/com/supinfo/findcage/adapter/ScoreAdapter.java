package com.supinfo.findcage.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.supinfo.findcage.R;
import com.supinfo.findcage.mode.Score;

import java.util.List;


/**
 * Created by dell on 2017/4/1.
 */

public class ScoreAdapter extends ArrayAdapter<Score> {
    public ScoreAdapter(@NonNull Context context,@NonNull List<Score> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Score selectedScore = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_score, parent, false);
        }
        String myScore = String.valueOf(selectedScore.getScore());
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
// Populate the data into the template view using the data object
        tvName.setText(selectedScore.getName() +" finish the game in "+ myScore + "sec");

// Return the completed view to render on screen
        return convertView;
    }
}

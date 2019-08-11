package com.appsaga.feli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    TextView heyText;
    TextView moodText;
    ImageView walkImage;
    ImageView writeDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String mood = getIntent().getStringExtra("mood");
        String name = getIntent().getStringExtra("name");
        heyText=findViewById(R.id.hey_text);
        moodText=findViewById(R.id.mood_text);
        writeDiary = findViewById(R.id.write_diary);

        String hey = "Hey "+name+"!";
        String mood_text = "You are quite "+mood+" today!";

        heyText.setText(hey);
        moodText.setText(mood_text);

        walkImage=findViewById(R.id.walk_image);
        walkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this,EditActivity.class));
            }
        });

        writeDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this,DiaryEntry.class));
            }
        });
    }
}

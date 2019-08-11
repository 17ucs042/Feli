package com.appsaga.feli;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchMood extends AppCompatActivity {

    TextView allDone;
    ImageView goBack;
    Button happy;
    Button sad;
    Button depressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mood);

        final String name = getIntent().getStringExtra("name");
        allDone=findViewById(R.id.all_done);

        String almost_done = "Okay "+name+"! We are\nalmost done.";
        allDone.setText(almost_done);

        goBack=findViewById(R.id.go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        happy=findViewById(R.id.happy);
        sad=findViewById(R.id.sad);
        depressed=findViewById(R.id.depressed);

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchMood.this,Dashboard.class);
                intent.putExtra("mood","happy");
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchMood.this,Dashboard.class);
                intent.putExtra("mood","sad");
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        depressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchMood.this,Dashboard.class);
                intent.putExtra("mood","depressed");
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}

package com.appsaga.feli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    TextView howTo;
    ImageView goBack;
    Button facebook;
    Button google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final String name = getIntent().getStringExtra("name");
        howTo=findViewById(R.id.how_to);
        facebook = findViewById(R.id.facebook);
        google=findViewById(R.id.google);
        goBack=findViewById(R.id.go_back);

        String how_to = "We don\'t want you to confuse you with\nother "+name+"(s) out there!\n\n\nHow do you want to create an account?" +
                "\n\nWe won't post anything we swear!";

        howTo.setText(how_to);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,SearchMood.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,SearchMood.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}

package com.appsaga.feli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EnterName extends AppCompatActivity {

    ImageView goBack;
    Button next;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);

        goBack=findViewById(R.id.go_back);
        next=findViewById(R.id.next);
        name=findViewById(R.id.name);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().length()==0)
                {
                    Toast.makeText(EnterName.this,"Please Enter Your Name",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(EnterName.this,SignUp.class);
                    intent.putExtra("name",name.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}

package com.appsaga.feli;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

import java.util.HashMap;
import java.util.Map;

public class DiaryEntry extends AppCompatActivity {

    ImageView goBack;
    EditText diaryText;
    String sentiment;
    Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);

        goBack=findViewById(R.id.go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        diaryText=findViewById(R.id.enter_diary_text);
        begin=findViewById(R.id.begin);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AskWatsonTask task = new AskWatsonTask();
                task.execute(new String[]{});
            }
        });
    }

    private class AskWatsonTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... textsToAnalyse) {

            System.out.println(diaryText.getText());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                }
            });

            AlchemyLanguage service = new AlchemyLanguage();
            service.setApiKey("CfUVATCRA2Y5Q8Y0X7Rwkc41mBMDA_s9hL_ZEIYqdc7B");

            Map<String, Object> params = new HashMap<String, Object>();
            params.put(AlchemyLanguage.TEXT, diaryText.getText());
            DocumentSentiment sentiment = service.getSentiment(params).execute();

            //passing the result to be displayed at UI in the main tread
            return sentiment.getSentiment().getType().name();
        }

        //setting the value of UI outside of the thread
        @Override
        protected void onPostExecute(String result) {

            Log.d("Senti",result);
        }
    }
}

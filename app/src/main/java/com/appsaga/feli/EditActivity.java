package com.appsaga.feli;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.health.SystemHealthManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

public class EditActivity extends AppCompatActivity implements SensorEventListener {

    ImageView goBack;
    SensorManager sensorManager;
    boolean running = false;
    TextView graph;

    Double steps=0.0;
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    GraphView graph2;

    Button begin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        goBack = findViewById(R.id.go_back);
        graph = findViewById(R.id.graph);
        begin = findViewById(R.id.begin);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        graph2 = findViewById(R.id.graph2);
        // data
        series = new LineGraphSeries<>();
        // customize a little bit viewport

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sensorManager = (SensorManager) getSystemService(EditActivity.this.SENSOR_SERVICE);
                running = true;

                Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

                if (countSensor != null) {
                    sensorManager.registerListener(EditActivity.this, countSensor, SensorManager.SENSOR_DELAY_UI);
                } else {
                    Toast.makeText(EditActivity.this, "Count Sensor not available", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (running) {
            steps = Double.valueOf(event.values[0]);

            graph.setText(String.valueOf(event.values[0]));
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // we add 100 new entries
                    for (int i = 0; i < 1; i++) {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                addEntry();
                            }
                        });

                        // sleep to slow down the add of entries
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException e) {
                            // manage error ...
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }

    private void addEntry() {
        // here, we choose to display max 10 points on the viewport and we scroll to end
        series.appendData(new DataPoint(lastX++, steps / 1000), true, 10);
        graph2.addSeries(series);
    }
}

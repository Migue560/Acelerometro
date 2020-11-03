package com.example.acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import static java.lang.Math.*;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager Sensores,Sensora;
    private Sensor SensorAce, SensorGra;
    private float X,Y,Z,E,O,P;
    private TextView ValorX,Valory,Valorz,LogText,ValorO,ValorE,ValorP;
    private ScrollView ScrollView;


    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogText=findViewById(R.id.textView2);
        ValorX=findViewById(R.id.textView4);
        Valory=findViewById(R.id.textView6);
        Valorz=findViewById(R.id.textView8);
        ValorO=findViewById(R.id.textView10);
        ValorE=findViewById(R.id.textView11);
        ValorP=findViewById(R.id.textView12);
        ScrollView= findViewById(R.id.scrollView);
        ValorX.setText("0");Valory.setText("0");Valorz.setText("0");ValorO.setText("0");ValorE.setText("0");ValorP.setText("0");
        X=0;Y=0;Z=0;E=0;P=0;O=0;

        Sensores=(SensorManager)getSystemService(SENSOR_SERVICE);
       //Sensora=(SensorManager)getSystemService(SEARCH_SERVICE);
      SensorAce=Sensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
      Sensores.registerListener((SensorEventListener) this,SensorAce,SensorManager.SENSOR_DELAY_NORMAL);

       SensorGra=Sensores.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensores.registerListener((SensorEventListener)this,SensorGra,SensorManager.SENSOR_DELAY_NORMAL);


        List<Sensor> listSensors=Sensores.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor:listSensors){
            log ("Sensor:"+sensor.getName().toString());
        }
    }

    private void log(String s) {
        LogText.append("\n"+s);
        ScrollView.post((new Runnable() {
            @Override
            public void run() {
                ScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }));

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            try {
                float Xa = sensorEvent.values[0];
                float Ya = sensorEvent.values[1];
                float Za = sensorEvent.values[2];
                if (abs(Xa - X) >= 1 || abs(Ya - Y) >= 1 || abs(Za - Z) >= 1) {
                    ValorX.setText(String.valueOf((sensorEvent.values[0])));
                    Valory.setText(String.valueOf((sensorEvent.values[1])));
                    Valorz.setText(String.valueOf((sensorEvent.values[2])));
                }
                X = sensorEvent.values[0];
                Y = sensorEvent.values[1];
                Z = sensorEvent.values[2];
            } catch (Exception e) {

            }
        }

        if (mySensor.getType() == Sensor.TYPE_GYROSCOPE) {
            try {
              //  final double alpha=0.8;
              // float  Oa = (float) (sensorEvent.values[0]*(1-alpha));
               // float Ea = (float) (sensorEvent.values[1]*(1-alpha));
              // float Pa = (float) (sensorEvent.values[2]*(1-alpha));

                float Oa = sensorEvent.values[0];
                float Ea = sensorEvent.values[1];
                float Pa = sensorEvent.values[2];
                
                if (abs(Oa - O) >= 1 || abs(Ea - E) >= 1 || abs(Pa - P) >= 1) {
                    ValorO.setText(String.valueOf((sensorEvent.values[0])));
                    ValorE.setText(String.valueOf((sensorEvent.values[1])));
                    ValorP.setText(String.valueOf((sensorEvent.values[2])));
                }
                E = sensorEvent.values[0];
                O = sensorEvent.values[1];
                P = sensorEvent.values[2];
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    }



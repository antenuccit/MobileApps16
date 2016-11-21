package osucse5236.chooseyourownadventure;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class CombatActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Combat";


    //Combat Countdown Timer
    SensorManager sensManager;
    Sensor sensSpeed;
    TextView timer;
    int time = 12;
    View health1;
    View health2;
    View health3;
    View health4;
    View health5;
    View health6;
    View health7;
    View health8;
    List<View> health = new ArrayList<View>();
    int healthIndex = 7;
    int changeIndex = 0;


    Handler timeHandler = new Handler();
    Runnable timeRunner = new Runnable() {
        @Override
        public void run()  {
            time = time - 1;
            if (health1.getVisibility() == View.INVISIBLE) {
                //End Activity as Combat Won
            } else if (time == 0) {
                //End Activity as Combat Lost
            }
            if (time >= 0){
                String timeS = "" + time + "";
                timer.setText(timeS);
            }
            timeHandler.postDelayed(this, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        sensManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensSpeed = sensManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
        sensManager.registerListener(this, sensSpeed, 1000);
        health1 = findViewById(R.id.health1);
        health.add(0,health1);
        health2 = findViewById(R.id.health2);
        health.add(1,health2);
        health3 = findViewById(R.id.health3);
        health.add(2,health3);
        health4 = findViewById(R.id.health4);
        health.add(3,health4);
        health5 = findViewById(R.id.health5);
        health.add(4,health5);
        health6 = findViewById(R.id.health6);
        health.add(5,health6);
        health7 = findViewById(R.id.health7);
        health.add(6,health7);
        health8 = findViewById(R.id.health8);
        health.add(7,health8);
        timer = (TextView) findViewById(R.id.timer);
        timeHandler.postDelayed(timeRunner, 1000);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        sensManager.unregisterListener(this);
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        sensManager.registerListener(this, sensSpeed, 1000);
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        if (sensSpeed.getType() == Sensor.TYPE_ACCELEROMETER){
            Float val1 = sensorEvent.values[0];
            Float val2 = sensorEvent.values[1];
            Float val3 = sensorEvent.values[2];
            float speed = Math.abs(val1 + val2 + val3) / 3;
            if (speed > 6 && healthIndex >= 0 && (changeIndex % 50 == 1) ){
                View healthX = health.get(healthIndex);
                healthX.setVisibility(View.INVISIBLE);
                healthIndex = healthIndex - 1;
            } else {
                changeIndex++;
            }

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}

package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor accelerometer,mGyro,mMagno;
    TextView xValue,yValue,zValue,xGyroValue,yGyroValue,zGyroValue,xMagnoValue,yMagnoValue,zMagnoValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xValue = (TextView) findViewById(R.id.xValue);
        yValue =  (TextView)findViewById(R.id.yValue);
        zValue =  (TextView)findViewById(R.id.zValue);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue =  (TextView)findViewById(R.id.yGyroValue);
        zGyroValue =  (TextView)findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView) findViewById(R.id.xMagnoValue);
        yMagnoValue =  (TextView)findViewById(R.id.yMagnoValue);
        zMagnoValue =  (TextView)findViewById(R.id.zMagnoValue);


        Log.d(TAG, "oncreate: Intializing sensor services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer!=null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "oncreate: Registered accelerometer Listener");
        }else {
            xValue.setText("Accelerometer Not Supported");
            yValue.setText("Accelerometer Not Supported");
            zValue.setText("Accelerometer Not Supported");
        }


        mGyro=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mGyro!=null) {
            sensorManager.registerListener(MainActivity.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "oncreate: Registered gyro Listener");
        }else {
            xGyroValue.setText("Gyroscope Not Supported");
            yGyroValue.setText("Gyroscope Not Supported");
            zGyroValue.setText("Gyroscope Not Supported");
        }


        mMagno=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mMagno!=null) {
            sensorManager.registerListener(MainActivity.this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "oncreate: Registered Magno Listener");
        }else {
            xMagnoValue.setText("Magno Not Supported");
            yMagnoValue.setText("Magno Not Supported");
            zMagnoValue.setText("Magno Not Supported");
        }



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);
            xValue.setText("xvalue =" + sensorEvent.values[0]);
            yValue.setText("yvalue =" + sensorEvent.values[1]);
            zValue.setText("zvalue =" + sensorEvent.values[2]);
        }else if (sensor.getType()==Sensor.TYPE_GYROSCOPE){
            xGyroValue.setText("xGvalue =" + sensorEvent.values[0]);
            yGyroValue.setText("yGvalue =" + sensorEvent.values[1]);
            zGyroValue.setText("zGvalue =" + sensorEvent.values[2]);

        }else if (sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            xMagnoValue.setText("xMvalue =" + sensorEvent.values[0]);
            yMagnoValue.setText("yMvalue =" + sensorEvent.values[1]);
            zMagnoValue.setText("zMvalue =" + sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
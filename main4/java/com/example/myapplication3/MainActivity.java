package com.example.myapplication3;


import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView mAccelerometerX, mAccelerometerY, mAccelerometerZ,
             mMagneticX, mMagneticY, mMagneticZ,
             mProximity, mLight, magnet, accel;
    SensorManager sensorManager;
    Sensor mAccelerometerSensor, mProximitySensor, mMagneticSensor, mLightSensor;
    float mMaxValue;
    float mValue;
    float[] lastMagnetometerData = new float[3];
    float[] lastAccelData = new float[3];
    float[] rotationMatrix = new float[9];
    float[] orientationAngles = new float[3];
    float posX, posY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mLightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mMaxValue = mLightSensor.getMaximumRange();

        accel = (TextView) findViewById(R.id.textView);
        mAccelerometerX = (TextView)findViewById(R.id.textView2);
        mAccelerometerY = (TextView)findViewById(R.id.textView3);
        mAccelerometerZ = (TextView)findViewById(R.id.textView4);

        mMagneticX = (TextView)findViewById(R.id.textView6);
        mMagneticY = (TextView)findViewById(R.id.textView7);
        mMagneticZ = (TextView)findViewById(R.id.textView8);

        mProximity = (TextView)findViewById(R.id.textView10);
        mLight = (TextView)findViewById(R.id.textView12);

        magnet = (TextView)findViewById(R.id.textView13);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            System.arraycopy(event.values, 0, lastAccelData, 0, event.values.length);
            posX = event.values[0];
            posY = event.values[1];

            float p1 = accel.getX();
            float p2 = accel.getY();

            if (accel.getY() > 2000 && posY > 0 || accel.getY() < 10 && posY < 0){
                return;
            }
            if (accel.getX() > 1000 && posX > 0 || accel.getX() < 0 && posX < 0){
                return;
            }

            accel.setX(accel.getX() - (posX * 2));
            accel.setY(accel.getY() + (posY * 2));



            mAccelerometerX.setText(Float.toString(event.values[0]));
            mAccelerometerY.setText(Float.toString(event.values[1]));
            mAccelerometerZ.setText(Float.toString(event.values[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            System.arraycopy(event.values, 0, lastMagnetometerData, 0, event.values.length);
            updateOrientationAngles();
            magnet.setText(getDirectionFromAngles());
            mMagneticX.setText(Float.toString(event.values[0]));
            mMagneticY.setText(Float.toString(event.values[1]));
            mMagneticZ.setText(Float.toString(event.values[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            mProximity.setText(Float.toString(event.values[0]));
        }
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            mLight.setText(Float.toString(event.values[0]));
        }
    }
    private void updateOrientationAngles() {
        SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelData, lastMagnetometerData);
        SensorManager.getOrientation(rotationMatrix, orientationAngles);

        for (int i = 0; i < orientationAngles.length; i++) {
            orientationAngles[i] = (float) Math.toDegrees(orientationAngles[i]);
        }
    }

    private String getDirectionFromAngles() {
        float angle = orientationAngles[0];
        if (angle >= -22.5 && angle < 22.5) {
            return "Север";
        } else if (angle >= 22.5 && angle < 67.5) {
            return "СВ";
        } else if (angle >= 67.5 && angle < 112.5) {
            return "Восток";
        } else if (angle >= 112.5 && angle < 157.5) {
            return "ЮВ";
        } else if (angle >= 157.5 && angle < -157.5) {
            return "Юг";
        } else if (angle >= -157.5 && angle < -112.5) {
            return "ЮЗ";
        } else if (angle >= -112.5 && angle < -67.5) {
            return "Запад";
        } else if (angle >= -67.5 && angle < -22.5) {
            return "СЗ";
        } else {
            return "Unknown";
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, mAccelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mMagneticSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, mProximitySensor,
                SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, mLightSensor,
                SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, mAccelerometerSensor);
        sensorManager.unregisterListener(this, mMagneticSensor);
        sensorManager.unregisterListener(this, mProximitySensor);
        sensorManager.unregisterListener(this, mLightSensor);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

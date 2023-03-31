package com.example.sensoraccelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    /*---Khai báo các biến cần thiết---*/
    SensorManager sensorManager;
    Sensor sensorAccelerometer;
    TextView textView;
    TextView textView_speed;
    LinearLayout linearLayout;
    float last_x, last_y, last_z;
    long last_time = 0;

    /*---main---*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*---find id---*/
        textView = findViewById(R.id.textView);
        textView_speed = findViewById(R.id.textView_speed);
        linearLayout = findViewById(R.id.linearLayout);

        /*---create and register sensor---*/
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensorAccelerometer == null) {
            Toast.makeText(this, "Have no support sensor", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    /*---sensor---*/
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        /*---khai báo sensor---*/
        sensorAccelerometer = sensorEvent.sensor;

        if(sensorAccelerometer.getType() == Sensor.TYPE_ACCELEROMETER) {

            /*---gán các hệ toạ độ của cảm biến vào 3 biến xyz---*/
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            /*---xác định thời gian thực khi có tác động vào thiết bị---*/
            long currentTime = System.currentTimeMillis();
            if((currentTime - last_time) > 100){
                long time = currentTime - last_time;
                last_time = currentTime;

                /*---tính toán lực tác động speed vào thiết bị---*/
                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/time * 10000;

                /*---speed > 500 chạy hàm randomNumber, ngược lại hiển thị thông số của speed---*/
                if(speed > 500) {
                    randomNumber();
                    textView_speed.setText("Speed : " + speed);
                } else {
                    textView_speed.setText("Speed : " + speed);
                }

                /*---gán lại các giá trị hệ toạ độ cho cảm biến---*/
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /*---hàm random number---*/
    private void randomNumber(){
        Random random = new Random();
        int number = random.nextInt(100) + 1;

        textView.setText("" + number);

        /*---tạo hiệu ứng chuyển động cho ứng dụng khi thay đổi số ngẫu nhiên---*/
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.random_number);
        linearLayout.clearAnimation();
        linearLayout.startAnimation(animation);
    }
}
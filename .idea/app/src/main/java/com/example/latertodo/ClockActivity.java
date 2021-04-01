package com.example.latertodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class ClockActivity extends AppCompatActivity {
    private static CountdownView countdownView;
    private int time = 900;
    private int minute = 15;
    private final Timer[] timer = {new Timer()};
    private ImageView time_stop;
    private ImageView time_pause;
    private ImageView back_button;
    private boolean click_pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        init();
    }

    private void init() {
        getData();
        timer[0].cancel();
        timer[0] = new Timer();
        Intent intent =new Intent(ClockActivity.this,BackgroundService.class);
        startService(intent);

        timer[0].schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time--;
                        countdownView.setCountdown(time);
                        countdownView.setCountdownStatus(CountdownView.COUNTDOWN_START);
                        if (time == 0) {
                            timer[0].cancel();

                        }
                    }
                });
            }
        }, 1000, 1000);


        time_pause = findViewById(R.id.pause);
        time_stop = findViewById(R.id.stop);
        back_button = findViewById(R.id.back);
        countdownView = findViewById(R.id.view_countdown);
        // 设置倒计时时长
        countdownView.setCountdown(time);
        // 设置倒计时改变监听
        countdownView.setOnCountdownListener(new CountdownView.OnCountdownListener() {
            @Override
            public void countdown(int minute) {
                ClockActivity.this.minute = minute;
                ClockActivity.this.time = 60 * minute;
            }
        });

//        if (!Settings.canDrawOverlays(getApplicationContext())) {
//            //若未授权则请求权限
//            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//            intent.setData(Uri.parse("package:" + getPackageName()));
//            startActivityForResult(intent, 0);
//        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdownView.setCountdownStatus(CountdownView.COUNTDOWN_STOP);
                Intent intent =new Intent(ClockActivity.this,BackgroundService.class);
                stopService(intent);
                timer[0].cancel();
                Intent intent_1 =new Intent(ClockActivity.this,MainActivity2.class);
                startActivity(intent_1);
                finish();
            }
        });

        time_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!click_pause){
                    countdownView.setCountdownStatus(CountdownView.COUNTDOWN_PAUSE);
                    Intent intent =new Intent(ClockActivity.this,BackgroundService.class);
                    stopService(intent);
                    timer[0].cancel();
                    click_pause = true;
                } else {
                    timer[0].cancel();
                    timer[0] = new Timer();
                    Intent intent =new Intent(ClockActivity.this,BackgroundService.class);
                    startService(intent);

                    timer[0].schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    time--;
                                    countdownView.setCountdown(time);
                                    countdownView.setCountdownStatus(CountdownView.COUNTDOWN_START);
                                    if (time == 0) {
                                        timer[0].cancel();

                                    }
                                }
                            });
                        }
                    }, 1000, 1000);
                    click_pause = false;
                }
            }
        });

        time_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdownView.setCountdownStatus(CountdownView.COUNTDOWN_STOP);
                Intent intent =new Intent(ClockActivity.this,BackgroundService.class);
                stopService(intent);
                timer[0].cancel();
                Intent intent_1 =new Intent(ClockActivity.this,MainActivity2.class);
                startActivity(intent_1);
                finish();
            }
        });


    }

    public void getData(){
        final Intent intent = getIntent();
        time = intent.getIntExtra("time",900);
    }
}
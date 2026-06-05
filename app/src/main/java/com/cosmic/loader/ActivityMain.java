package com.cosmic.loader;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityMain extends AppCompatActivity {

    private TextView tvTimer, tvDeviceInfo;
    private Button btnStart, btnStop, btnPUBGGlobal, btnPUBGKorea, btnPUBGTaiwan, btnPUBGVietnam, btnBGMI;
    private LinearLayout layoutGameButtons;
    private CountDownTimer countDownTimer;
    private long timeRemaining = 3600000; // 1 jam
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
        displayDeviceInfo();
    }

    private void initViews() {
        tvTimer = findViewById(R.id.tvTimer);
        tvDeviceInfo = findViewById(R.id.tvDeviceInfo);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnPUBGGlobal = findViewById(R.id.btnPUBGGlobal);
        btnPUBGKorea = findViewById(R.id.btnPUBGKorea);
        btnPUBGTaiwan = findViewById(R.id.btnPUBGTaiwan);
        btnPUBGVietnam = findViewById(R.id.btnPUBGVietnam);
        btnBGMI = findViewById(R.id.btnBGMI);
        layoutGameButtons = findViewById(R.id.layoutGameButtons);
    }

    private void setupListeners() {
        btnStart.setOnClickListener(v -> startTimer());
        btnStop.setOnClickListener(v -> stopTimer());

        btnPUBGGlobal.setOnClickListener(v -> showGameOptions("PUBG Global"));
        btnPUBGKorea.setOnClickListener(v -> showGameOptions("PUBG Korea"));
        btnPUBGTaiwan.setOnClickListener(v -> showGameOptions("PUBG Taiwan"));
        btnPUBGVietnam.setOnClickListener(v -> showGameOptions("PUBG Vietnam"));
        btnBGMI.setOnClickListener(v -> showGameOptions("BGMI"));
    }

    private void startTimer() {
        if (isTimerRunning) return;

        isTimerRunning = true;
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateTimerDisplay(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                Toast.makeText(ActivityMain.this, "Timer Expired!", Toast.LENGTH_SHORT).show();
                tvTimer.setText("00:00:00");
            }
        }.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
    }

    private void updateTimerDisplay(long millisUntilFinished) {
        long hours = millisUntilFinished / 3600000;
        long minutes = (millisUntilFinished % 3600000) / 60000;
        long seconds = (millisUntilFinished % 60000) / 1000;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        tvTimer.setText(timeString);
    }

    private void displayDeviceInfo() {
        String deviceInfo = DeviceInfoHelper.getDeviceInfo();
        tvDeviceInfo.setText(deviceInfo);
    }

    private void showGameOptions(String gameName) {
        GameOptionsDialog dialog = new GameOptionsDialog(this, gameName);
        dialog.show();
    }
}

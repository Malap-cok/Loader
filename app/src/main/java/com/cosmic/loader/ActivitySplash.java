package com.cosmic.loader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay 2 detik sebelum pindah ke ActivityLogin
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(ActivitySplash.this, ActivityLogin.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}

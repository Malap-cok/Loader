package com.cosmic.loader;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {

    private EditText edtLicenseKey;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLicenseKey = findViewById(R.id.edtLicenseKey);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> validateLicense());
    }

    private void validateLicense() {
        String licenseKey = edtLicenseKey.getText().toString().trim();

        if (licenseKey.isEmpty()) {
            Toast.makeText(this, "License Key tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validasi License Key (bisa dari JNI atau API)
        if (LicenseManager.validateLicense(licenseKey)) {
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "License Key Tidak Valid", Toast.LENGTH_SHORT).show();
        }
    }
}

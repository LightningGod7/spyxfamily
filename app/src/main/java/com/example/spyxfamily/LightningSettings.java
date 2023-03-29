package com.example.spyxfamily;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LightningSettings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new LightningFragment())
                .commit();
    }
}

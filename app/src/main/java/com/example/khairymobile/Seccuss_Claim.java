package com.example.khairymobile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Seccuss_Claim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccuss__claim);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}

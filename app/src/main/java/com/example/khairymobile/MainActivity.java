package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

String ttoken, token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        getToken();

    }

    public void getToken() {
        final SharedPreferences sharedPreferences = this.getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        ttoken = sharedPreferences.getString("AUTH",token);
//        token = sharedPreferences.getString("TOKEN","NO TOKEN");

        Toast.makeText(this, "WE FOUND IT\n" + ttoken, Toast.LENGTH_SHORT).show();
    }

}



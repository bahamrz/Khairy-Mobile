package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    private CardView register;
    private TextView alreadyuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        register = findViewById(R.id.registercard);
        alreadyuser = findViewById(R.id.alreadyuserlogin);
        setTitle("الإنضمام للعائلة");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Register Button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerme();
            }
        });

        // Already User Button
        alreadyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takemelogin = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(takemelogin);
            }
        });


    }

    private void registerme() {

        Toast.makeText(getApplicationContext(), "I will be Registering You Senpai", Toast.LENGTH_SHORT).show();


    }
    //Return to parent Fragment when pressing back arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}

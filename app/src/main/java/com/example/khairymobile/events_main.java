package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

public class events_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);
        //Show Back Arrow
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // When Pressing Back Button Get back to previous Activity
        @Override
        public void onBackPressed(){
            super.onBackPressed();
            this.finish();
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

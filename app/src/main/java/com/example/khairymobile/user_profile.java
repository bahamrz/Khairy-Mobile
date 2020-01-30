package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class user_profile extends AppCompatActivity {

    private CardView editmyinfo, logemout;
    private TextView usrname, email, datejoined,roles,lasteditonaccount;
    private String token,ttoken,userid;
    String URL = "http://192.168.1.7/khairy/public/api/me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //Show Back Arrow
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editmyinfo = findViewById(R.id.editmyinfo);
        logemout = findViewById(R.id.logout);
        usrname = findViewById(R.id.username);
        email = findViewById(R.id.email);
//        datejoined = findViewById(R.id.dateofjoining);
        lasteditonaccount = findViewById(R.id.lasteditonaccount);
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        ttoken = sharedPreferences.getString("AUTH",token);
        getUserinfo();
//TODO  hello
    }

    private void getUserinfo() {

        // add token to URL
        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendQueryParameter("token",ttoken);
        String newURL = builder.build().toString();
        // make HTTP Request to get Recipe

        Toast.makeText(getApplicationContext(), "Making Request", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newURL,
                jsonObject ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(getApplicationContext(), "Success :", Toast.LENGTH_SHORT).show();
                    usrname.setText(response.getString("name"));
                    email.setText(response.getString("email"));
//                    roles.setText(response.getString("phone"));
                    userid=response.getString("id");
                    lasteditonaccount.setText(response.getString("updated_at"));

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(user_profile.this, "Error Logging in:: "+error, Toast.LENGTH_SHORT).show();
                        Log.d("GGGGGGG", "onErrorResponse: " +error.getMessage());
                    }
                });
        requestQueue.add(request);
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


    public void emi(View view) {
        Toast.makeText(this, "Hii Edit me Senpai", Toast.LENGTH_SHORT).show();

        Intent editinfoactivity = new Intent(getApplicationContext(),user_edit.class);
        editinfoactivity.putExtra("username",usrname.getText());
        editinfoactivity.putExtra("useremail",email.getText());
        editinfoactivity.putExtra("userid",userid);
        editinfoactivity.putExtra("updatedat",lasteditonaccount.getText());
        startActivityForResult(editinfoactivity,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Intent refresh = new Intent(this, user_profile.class);
            startActivity(refresh);
            this.finish();
        }
    }


    public void loogout(View view) {
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}


package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class apiuserdonations extends AppCompatActivity {
private String token, ttoken;
String URL = "http://localhost:8080/khairy/public/api/user/donations";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiuserdonations);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        ttoken = sharedPreferences.getString("AUTH",token);

        getUserDonations();

    }

    private void getUserDonations() {

        // add token to URL
        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendQueryParameter("token",ttoken);
        String newURL = builder.build().toString();
        // make HTTP Request to get Recipe

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newURL,
                null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Hello Master: "+ response.length(), Toast.LENGTH_SHORT).show();
//                    title.setText(response.getString("name"));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error Logging in:: "+error, Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
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

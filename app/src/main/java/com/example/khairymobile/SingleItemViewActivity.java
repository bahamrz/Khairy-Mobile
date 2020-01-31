package com.example.khairymobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class SingleItemViewActivity extends AppCompatActivity {

    String userid;
    String Token, TToken;
    int itemPosition;
    String image;
    String title;
    String date;
    String creator;
    String description;
    TextView mtitle, mdate, mdescription, mcreator;
    ImageView mimage;

    //To check if user Authinticated and see there ID
    String URL = "http://localhost:8080/khairy/public/api/me";

    //To send the Clame Request
    String RESERVE_URL = "http://localhost:8080/khairy/public/api/reserve/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        setTitle("خيــــري ");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        creator = getIntent().getStringExtra("creator");
        description = getIntent().getStringExtra("description");
        image = getIntent().getStringExtra("image");
        itemPosition = getIntent().getIntExtra("position", -1) + 1;

        mtitle = findViewById(R.id.singleViewTitle);
        mdate = findViewById(R.id.singleViewDate);
        mdescription = findViewById(R.id.singleViewDescription);
        mcreator = findViewById(R.id.singleViewCreator);
        mimage = findViewById(R.id.image);

        mtitle.setText(title);
        mdate.setText(date);
        mdescription.setText(description);
        mcreator.setText(creator);
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(image)
                .into(mimage);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        Token = sharedPreferences.getString("AUTH", TToken);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getUserinfo();
                requestToClame();
            }
        });
    }

    private void getUserinfo() {

        // add token to URL
        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendQueryParameter("token", Token);
        String newURL = builder.build().toString();

        // make HTTP Request to get Recipe
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newURL,
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    roles.setText(response.getString("phone"));
                    userid = response.getString("id");

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SingleItemViewActivity.this, "Error id", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(request);
    }

    public void requestToClame() {

        String finalURL = RESERVE_URL +itemPosition;

        // add token to URL
        Uri.Builder builder = Uri.parse(finalURL).buildUpon();
        builder.appendQueryParameter("token",Token);
        String newURL = builder.build().toString();

        //check if can Claim
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest claimrequest = new JsonObjectRequest(Request.Method.POST, newURL,
                jsonObject ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Toast.makeText(SingleItemViewActivity.this, "Your response MyKing = "+ response.getString("response"), Toast.LENGTH_SHORT).show();
                    if (response.getString("response").equals("ok")){
                        startActivity(new Intent(getApplicationContext(),Seccuss_Claim.class));
                    }else{
                        Toast.makeText(SingleItemViewActivity.this, "هوا شوف ياأنت طلبته قبل يأنت مدايره فافوتهله", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SingleItemViewActivity.this, "Error Claim :: "+error, Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(claimrequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_website) {
            Toast.makeText(this, "kkkkkkkkk", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

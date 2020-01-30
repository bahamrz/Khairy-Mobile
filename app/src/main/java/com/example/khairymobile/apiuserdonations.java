package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class apiuserdonations extends AppCompatActivity {
    private String token, ttoken;
    final String imageurlcompleter ="http://localhost:8080/khairy/public/storage/";
    String URL = "http://localhost:8080/khairy/public/api/user/donations";
//    String URL = "http://localhost:8080/khairy/public/api/user/donations";
    private ArrayList<String> mtitle = new ArrayList<>();
    private ArrayList<String> mdescription = new ArrayList<>();
    private ArrayList<String> mdate = new ArrayList<>();
    private ArrayList<String> mimage = new ArrayList<>();

    RecyclerView recyclerView;
    userDonationsAdapter adapter;

    private String title, description, date,image, creatorid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiuserdonations);
        Intent usrtoken = getIntent();
        ttoken = usrtoken.getStringExtra("ttoken");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("تــبرعاتـي");
        recyclerView = findViewById(R.id.viewuserapi);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new userDonationsAdapter(apiuserdonations.this, mimage,mtitle,mdescription,mdate);
//        recyclerView.setAdapter(adapter);

//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("AUTH", Context.MODE_PRIVATE);
//        ttoken = sharedPreferences.getString("AUTH",token);
        getUserDonations();


        //vars


    }

    private void getUserDonations() {

        //************************** Sbeita Request ************************
        Toast.makeText(getApplicationContext(), "Making Request", Toast.LENGTH_SHORT).show();
        // add token to URL
        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendQueryParameter("token",ttoken);
        String newURL = builder.build().toString();

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));
        // make HTTP Request to get Recipe
        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET, newURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i =0 ; i < response.length() ; i++){
                    try {
                        Toast.makeText(getApplicationContext(), "we did it we bring data", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = response.getJSONObject(i);
//                        JSONObject user = jsonObject.getJSONObject("creator");

                         title = jsonObject.getString("name");
                         description = jsonObject.getString("description");
                        date = jsonObject.getString("created_at");
                         image = jsonObject.getString("image");
                         //donation id to delete or edit
                        creatorid =jsonObject.getString("id");

                        mtitle.add(title);
                        mdescription.add(description);
                        mdate.add(date);
                        image = image.replace("public/","");
                        mimage.add(imageurlcompleter+image);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        initiItemClickiListiner();

                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "JSON ERROR DIE", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "YOU Have no internet connection idiot ", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(JsonArrayRequest);
    }


    // Recycle viewer
    private void initiItemClickiListiner(){
        adapter.setOnItemClickListener(new userDonationsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent usrdonatedit = new Intent(getApplicationContext(),user_single_item.class);
                usrdonatedit.putExtra("usrname",mtitle.get(position));
                usrdonatedit.putExtra("desc",mdescription.get(position));
                usrdonatedit.putExtra("img",mimage.get(position));
                usrdonatedit.putExtra("dat",mdate.get(position));
                usrdonatedit.putExtra("donateid",creatorid);
                startActivityForResult(usrdonatedit,1);
            }
        });


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

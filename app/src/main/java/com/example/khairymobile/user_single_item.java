package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class user_single_item extends AppCompatActivity {

    private ImageView usrdonateimg;
    private TextView usrdonatename,usrdonatedate,usrdonatedesc;
    private CardView usrdonateedit,usrdonatedelete;
    private String URL = "http://localhost:8080/khairy/public/api/user/donation/",donationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_single_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usrdonateimg = findViewById(R.id.usrdonateimg);
        usrdonatename = findViewById(R.id.usrdonatename);
        usrdonatedate = findViewById(R.id.usrdonatedate);
        usrdonatedesc = findViewById(R.id.usrdonatedesc);
        usrdonateedit = findViewById(R.id.usrdonateedit);
        usrdonatedelete = findViewById(R.id.usrdonatedelete);
        Intent intent = getIntent();
        usrdonatename.setText(intent.getStringExtra("usrname"));
        setTitle(intent.getStringExtra("usrname"));
        usrdonatedate.setText(intent.getStringExtra("dat"));
        usrdonatedesc.setText(intent.getStringExtra("desc"));
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(intent.getStringExtra("img"))
                .into(usrdonateimg);
        donationid = intent.getStringExtra("donateid");

//        usrdonatename.setText(intent.getStringExtra("usrname"));
//        usrdonatename.setText(intent.getStringExtra("usrname"));

usrdonatedelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        deletedonation();
    }
});
usrdonateedit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent edititem = new Intent(getApplicationContext(),edit_item.class);
        startActivityForResult(edititem,1);

    }
});

    }

    private void deletedonation() {

        // add token to URL
        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendPath(donationid);
        String newURL = builder.build().toString();
        // make HTTP Request to get Recipe

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, newURL,
                null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Deleted " , Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, null);
                finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error :: "+error, Toast.LENGTH_SHORT).show();
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

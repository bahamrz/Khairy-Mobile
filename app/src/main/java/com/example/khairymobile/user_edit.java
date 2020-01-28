package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

public class user_edit extends AppCompatActivity {
private EditText editeduser, editedmail,editedpassword;
private TextView lasteditinaccount;
String URL="http://localhost:8080/khairy/public/api/user/update";
private String token, ttoken,userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        Intent intent = getIntent();


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        editeduser = findViewById(R.id.editusername);
        editedmail = findViewById(R.id.editemail);
        lasteditinaccount=findViewById(R.id.lasteditinaccount);
        editedpassword=findViewById(R.id.editpassword);
        editeduser.setText(intent.getStringExtra("username"));
        editedmail.setText(intent.getStringExtra("useremail"));
        userid=(intent.getStringExtra("userid"));
        lasteditinaccount.setText(intent.getStringExtra("updatedat"));


        //Not Used So Far!
        final SharedPreferences sharedPreferences = this.getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        ttoken = sharedPreferences.getString("AUTH",token);



    }


    // Yaooo Consider not using this (To reload the Activity)
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

    public void saveeditdata(View view) {


        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendPath(userid);
        String newURL = builder.build().toString();
        Toast.makeText(getApplicationContext(), "Making Request", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", editeduser.getText().toString());
            jsonObject.put("email", editedmail.getText().toString());
            jsonObject.put("password", editedpassword.getText().toString());

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "JSON ERROR Die", Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, newURL,
                jsonObject ,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                    Toast.makeText(getApplicationContext(), "تمت العملية بنجاح", Toast.LENGTH_SHORT).show();
                // Refresh
                    setResult(RESULT_OK, null);
                    finish();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "err save: " + error, Toast.LENGTH_LONG).show();
//                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);




    }
}

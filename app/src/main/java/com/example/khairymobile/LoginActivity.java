package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView registrate;
    private static String LOGIN_URL="http://localhost:8080/khairy/public/api/login";
    private String loginemail;
    private String loginpass;
    public  String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("تسجيل الدخول إلى خيري :)");
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.WHITE));

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpass);
        registrate=findViewById(R.id.register);

        registrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),signup.class));
            }
        });
    }

    public void login(View view) {
        loginemail=email.getText().toString().trim();
        loginpass=password.getText().toString().trim();
        if(loginemail.isEmpty() || loginpass.isEmpty()){
            email.setError("أين البريد الإلكتروني أنا لا أراه");
            password.setError("أين كلمة المرور أنا لا أراها");
        }
        else{
            logmein();
        }
    }

    private void logmein() {


//                        JSONObject jsonObject = null;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email",loginemail);
            jsonObject.put("password",loginpass);
        } catch (JSONException e) {

            Toast.makeText(this, "JSON ERROR Die", Toast.LENGTH_SHORT).show();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, LOGIN_URL,
                jsonObject ,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Toast.makeText(LoginActivity.this, "Your Token Master"+ response.getString("access_token"), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginActivity.this ,MainActivity.class);
                            saveToken(response.getString("access_token"));
//                            startActivity(intent);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error Logging in:: "+error, Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(request);

    }

    public void saveToken(String access_token) {
        //Writing TOken to Shared Prefrences
        SharedPreferences sharedPreferences = this.getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AUTH",access_token);
        editor.commit();
        startActivity(new Intent(LoginActivity.this,MainActivity.class));



//        SharedPreferences preferences = getSharedPreferences("AUTH", LoginActivity.this.MODE_PRIVATE);
//        preferences.edit().putString("TOKEN", access_token).apply();
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

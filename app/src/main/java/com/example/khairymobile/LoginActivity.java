package com.example.khairymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private TextView registrate;
    private static String LOGIN_URL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpass);
        registrate=findViewById(R.id.register);
    }

    public void login(View view) {
        String loginemail=email.getText().toString().trim();
        String loginpass=password.getText().toString();
        if(loginemail.isEmpty() || loginpass.isEmpty()){
            logmein();
        }
        else{
            email.setError("Nerter Email");
            password.setError("Enter Pass");
        }



    }

    private void logmein() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String success= jsonObject.getString("success");
//                        JSONArray jsonArray = jsonObject.getJSONArray("login");



                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };

    }
}

package com.example.khairymobile;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class userprofilefrag extends Fragment {

private Button lgnbtn,logout;
private String token,ttoken;
private TextView title, nologin;
CardView myinfo, mydonation, myevent,showus;
String URL = "http://localhost:8080/khairy/public/api/me";
    public userprofilefrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_userprofilefrag, container, false);
        lgnbtn =(Button)view.findViewById(R.id.loginbtn);
        title =(TextView) view.findViewById(R.id.title);
        nologin =(TextView) view.findViewById(R.id.nologin);
        logout=(Button)view.findViewById(R.id.logout);
        myinfo=view.findViewById(R.id.myaccount);
        mydonation=view.findViewById(R.id.mydonations);
        myevent=view.findViewById(R.id.myevents);
        showus=view.findViewById(R.id.aboutus);
        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AUTH", Context.MODE_PRIVATE);
        ttoken = sharedPreferences.getString("AUTH",token);
//        Toast.makeText(getActivity(), "Hi"+ttoken, Toast.LENGTH_SHORT).show();
        // if there's Token Remove Login Button
        if(ttoken != null)
        {
            lgnbtn.setVisibility(View.GONE);
            nologin.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            myevent.setVisibility((View.VISIBLE));
            mydonation.setVisibility((View.VISIBLE));
            showus.setVisibility((View.VISIBLE));
            myinfo.setVisibility((View.VISIBLE));




            getUser();
    }




        lgnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                Toast.makeText(getActivity(), "You Acually Made it Bich", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });
        showus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showmeinfo = new Intent(getActivity(), aboutus.class);
                startActivity(showmeinfo);
//                startActivity(new Intent(getActivity(),events_main.class));
            }
        });

        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Hello Weka", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),user_profile.class));
            }
        });
        mydonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent usrapidon = new Intent(getActivity(),apiuserdonations.class);
                usrapidon.putExtra("ttoken",ttoken);
                startActivity(usrapidon);

            }
        });
        return view;


}

    private void getUser() {
        // add token to URL
        Uri.Builder builder = Uri.parse(URL).buildUpon();
        builder.appendQueryParameter("token",ttoken);
        String newURL = builder.build().toString();
        // make HTTP Request to get Recipe

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newURL,
                null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
//                    Toast.makeText(getActivity(), "Hello Master: "+ response.getString("name"), Toast.LENGTH_SHORT).show();
                    title.setText(response.getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getActivity(), "Error Logging in:: "+error, Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(request);
    }
    }

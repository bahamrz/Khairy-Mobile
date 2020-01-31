package com.example.khairymobile;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class eventsfrag extends Fragment {


    public eventsfrag() {
        // Required empty public constructor
    }



    //vars
    private ArrayList<String> mtitle = new ArrayList<>();
    private ArrayList<String> mdescription = new ArrayList<>();
    private ArrayList<String> mdate = new ArrayList<>();
    private ArrayList<String> mimage = new ArrayList<>();
    private ArrayList<String> mcreator = new ArrayList<>();
    private String creator;
    private String date;
    private String title;
    private String image;
    private String description;
    RecyclerView recyclerView;
    eventsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventsfrag, container, false);
        recyclerView = view.findViewById(R.id.eventsrv);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new eventsAdapter(getActivity(), mimage,mtitle,mdescription,mdate);

        getApiContentEvents();

        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getApiContentEvents() {
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        final String imageurlcompleter = "http://localhost:8080/khairy/public/storage/";
        final String url = "http://localhost:8080/khairy/public/api/events";

        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
//                        JSONObject user = jsonObject.getJSONObject("creator");

                        title = jsonObject.getString("Name");
                        description = jsonObject.getString("Description");
                        date = jsonObject.getString("created_at");
                        image = jsonObject.getString("image");
//                        creator = user.getString("name");

                        mtitle.add(title);
                        mdescription.add(description);
                        mdate.add(date);
                        image = image.replace("public/", "");
                        mimage.add(imageurlcompleter + image);
                        mcreator.add(creator);

                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        initiItemClickiListiner();


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(JsonArrayRequest);
    }


//
//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(getActivity(), SingleItemViewActivity.class);
//        intent.putExtra("title", mtitle.get(position));
////        intent.putExtra("creator", mcreator.get(position));
//        intent.putExtra("creator", "Creator");
//        intent.putExtra("date", mdate.get(position));
//        intent.putExtra("image", mimage.get(position));
//        intent.putExtra("description", mdescription.get(position));
//        intent.putExtra("position",position);
//
//        startActivity(intent);
//    }
//

    // Recycle viewer
    private void initiItemClickiListiner(){
        adapter.setOnItemClickListener(new eventsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent usrdonatedit = new Intent(getActivity(),SingleItemViewActivity.class);
                usrdonatedit.putExtra("title",mtitle.get(position));
                usrdonatedit.putExtra("description",mdescription.get(position));
                usrdonatedit.putExtra("image",mimage.get(position));
                usrdonatedit.putExtra("date",mdate.get(position));
//                usrdonatedit.putExtra("donateid",creatorid);
                startActivityForResult(usrdonatedit,1);
            }
        });
    }


}

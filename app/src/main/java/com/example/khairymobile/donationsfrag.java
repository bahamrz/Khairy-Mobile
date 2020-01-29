package com.example.khairymobile;


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
public class donationsfrag extends Fragment {

    private static final String TAG = "donation_main";

    //vars
    private ArrayList<String> mtitle = new ArrayList<>();
    private ArrayList<String> mdescription = new ArrayList<>();
    private ArrayList<String> mdate = new ArrayList<>();
    private ArrayList<String> mimage = new ArrayList<>();

    public donationsfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donationfrag, container, false);
        // To print static items
//        initImageBitMaps();
        getApiContent();
        // Inflate the layout for this fragment
        return view;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getApiContent() {
        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        final String imageurlcompleter = "http://192.168.1.7/khairy/public/storage/";
        String url = "http://192.168.1.7/khairy/public/api/products";

        JsonArrayRequest JsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i =0 ; i < response.length() ; i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        JSONObject user = jsonObject.getJSONObject("creator");

                        String title = jsonObject.getString("name");
                        String description = jsonObject.getString("description");
                        String date = jsonObject.getString("created_at");
                        String image = jsonObject.getString("image");
                        String creator =user.getString("name");

                        mtitle.add(title);
                        mdescription.add(description);
                        mdate.add(date);
                        mimage.add(imageurlcompleter+image);
                        initRecycleView();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "YOU Have no internet connection idiot ", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(JsonArrayRequest);
    }
//

    // the function to print static recycle view item

//    private void initImageBitMaps(){
//
//        mimage.add("https://www.voicesofyouth.org/sites/default/files/images/2019-03/nature-3125912_960_720.jpg");
//        mtitle.add("زياره مجانيه الي هذا المكان");
//        mdescription.add("نبي نعبي ديسكربشن لاكن مش عارف شن بندير فيه المهم كلام وخلاص بش تطلع منظرها حلو لان والله ماعاد معاودها خلاص ياسر :v :v ");
//        mdate.add("1/1/2020");
//
//        mimage.add("https://www.yesmagazine.org/wp-content/uploads/imports/97c16c71e4ac4236bc5058d63a667663.png");
//        mtitle.add("زياره مجانيه الي هذا المكان");
//        mdescription.add("نبي نعبي ديسكربشن لاكن مش عارف شن بندير فيه المهم كلام وخلاص بش تطلع منظرها حلو لان والله ماعاد معاودها خلاص ياسر :v :v ");
//        mdate.add("1/1/2020");
//
//        mimage.add("https://www.visitportugal.com/sites/www.visitportugal.com/files/mediateca/23_660x371.jpg");
//        mtitle.add("زياره مجانيه الي هذا المكان");
//        mdescription.add("نبي نعبي ديسكربشن لاكن مش عارف شن بندير فيه المهم كلام وخلاص بش تطلع منظرها حلو لان والله ماعاد معاودها خلاص ياسر :v :v ");
//        mdate.add("1/1/2020");
//
//        mimage.add("https://www.voicesofyouth.org/sites/default/files/images/2019-03/nature-3125912_960_720.jpg");
//        mtitle.add("زياره مجانيه الي هذا المكان");
//        mdescription.add("نبي نعبي ديسكربشن لاكن مش عارف شن بندير فيه المهم كلام وخلاص بش تطلع منظرها حلو لان والله ماعاد معاودها خلاص ياسر :v :v ");
//        mdate.add("1/1/2020");
//
//        mimage.add("https://www.voicesofyouth.org/sites/default/files/images/2019-03/nature-3125912_960_720.jpg");
//        mtitle.add("زياره مجانيه الي هذا المكان");
//        mdescription.add("نبي نعبي ديسكربشن لاكن مش عارف شن بندير فيه المهم كلام وخلاص بش تطلع منظرها حلو لان والله ماعاد معاودها خلاص ياسر :v :v ");
//        mdate.add("1/1/2020");
//
//        mimage.add("https://www.visitportugal.com/sites/www.visitportugal.com/files/mediateca/23_660x371.jpg");
//        mtitle.add("زياره مجانيه الي هذا المكان");
//        mdescription.add("نبي نعبي ديسكربشن لاكن مش عارف شن بندير فيه المهم كلام وخلاص بش تطلع منظرها حلو لان والله ماعاد معاودها خلاص ياسر :v :v ");
//        mdate.add("1/1/2020");
//
//        initRecycleView();
//    }

    private void initRecycleView(){
        RecyclerView RecyclerView = getView().findViewById(R.id.recycler_view);
        recyclerViewAdapter adapter = new recyclerViewAdapter(getContext(),mimage,mtitle,mdescription,mdate);
        RecyclerView.setAdapter(adapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}


package com.example.codecircuits_final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<MainClass> mainClass;
    private RequestQueue requestQueue;
    private RadioGroup radioGroup;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radio_group);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainClass = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

    }

    public void checkButton(View view){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        if(radioButton.getText().toString().equals("Ongoing Events")) {
            mainClass.clear();
            parseJSON_og();
        }
        else if(radioButton.getText().toString().equals("Upcoming Events")) {
            mainClass.clear();
            parseJSON_uc();
        }
    }

    private void parseJSON_og() {
        String url = "https://contesttrackerapi.herokuapp.com/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONObject("result").getJSONArray("ongoing");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject ongoing = jsonArray.getJSONObject(i);

                                String contestname = ongoing.getString("Name");
                                String endtime = "The contest ends at : " + ongoing.getString("EndTime");
                                String platform = "< " + ongoing.getString("Platform") + " />";

                                mainClass.add(new MainClass(contestname, endtime, platform));
                            }
                            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, mainClass);
                            recyclerView.setAdapter(recyclerViewAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    private void parseJSON_uc() {
        String url = "https://contesttrackerapi.herokuapp.com/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONObject("result").getJSONArray("upcoming");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject ongoing = jsonArray.getJSONObject(i);

                                String contestname = ongoing.getString("Name");
                                String starttime = ongoing.getString("StartTime");
                                String platform = "< " + ongoing.getString("Platform") + " />";
                                String duration = ongoing.getString("Duration");
                                String st_d = "The contest begins at " + starttime +
                                        " & Duration is : " + duration;

                                mainClass.add(new MainClass(contestname, st_d, platform));
                            }
                            recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, mainClass);
                            recyclerView.setAdapter(recyclerViewAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }


}

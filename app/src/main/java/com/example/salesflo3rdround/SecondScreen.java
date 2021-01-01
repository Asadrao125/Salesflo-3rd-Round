package com.example.salesflo3rdround;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SecondScreen extends AppCompatActivity {
    Button btnSync;
    String URL = "https://c8cpuut85c.execute-api.us-east-1.amazonaws.com/assessmentcenter/form";
    String MY_PREFS_NAME = "MyApp";
    String name;
    String gender;
    String skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        name = prefs.getString("name", "No name defined");
        gender = prefs.getString("gender", "No name defined");
        skill = prefs.getString("skill", "No name defined");

        btnSync = findViewById(R.id.btnSync);

        btnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postdatatoserver();
            }
        });
    }

    private void postdatatoserver() {
        HashMap<String, Object> data = new HashMap();
        data.put("name", name);
        data.put("gender", gender);
        data.put("skill", skill);
        postData(URL, data, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(SecondScreen.this, "" + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SecondScreen.this, "" + error, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void postData(String url, HashMap<String, Object> data, final Response.Listener<JSONObject> success, Response.ErrorListener f) {
        RequestQueue requstQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data),
                success,
                f
        ) {

        };
        requstQueue.add(jsonobj);

    }
}
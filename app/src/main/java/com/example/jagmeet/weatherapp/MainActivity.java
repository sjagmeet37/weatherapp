package com.example.jagmeet.weatherapp;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dl = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = findViewById(R.id.drawer);
        getWeather();
    }



    public void getWeather()
    {
            String url = "http://api.openweathermap.org/data/2.5/weather?lat=31.66&lon=74.8&APPID=9023edec46a2836e31f3b10e1cb02b84";

        JSONObject jsonObject = new JSONObject();

        Response.Listener<JSONObject> jsonObjectListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray = response.getJSONArray("weather");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    String weather = jsonObject1.getString("main");
                    TextView wetherxml = findViewById(R.id.weather);
                    wetherxml.setText(weather);

                    JSONObject jsonObject2 = response.getJSONObject("wind");
                    String wind = jsonObject2.getString("speed");
                    TextView windspeed = findViewById(R.id.windvalue);
                    windspeed.setText(wind);

                    JSONObject jsonObject3 = response.getJSONObject("main");
                    String pressurevalue = jsonObject3.getString("pressure");
                    TextView pressure = findViewById(R.id.pressurevalue);
                    pressure.setText(pressurevalue);

                    String humidutyvalue = jsonObject3.getString("humidity");
                    TextView humidity = findViewById(R.id.humidityvalue);
                    humidity.setText(humidutyvalue);

                    String temp = jsonObject3.getString("temp");
                    TextView tempvalue = findViewById(R.id.temp);
                    tempvalue.setText(temp);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,jsonObject,jsonObjectListener,errorListener);

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        queue.add(jsonObjectRequest);

    }


    public void drawer(View view) {
        dl.openDrawer(Gravity.LEFT);
    }
}

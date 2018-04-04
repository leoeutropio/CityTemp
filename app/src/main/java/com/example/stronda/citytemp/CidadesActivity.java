package com.example.stronda.citytemp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CidadesActivity extends AppCompatActivity {

    TextView data_tv, cidade_tv, grau_tv, temp_tv  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidades);

        data_tv = findViewById(R.id.date_tv);
        cidade_tv = findViewById(R.id.city_tv);
        temp_tv = findViewById(R.id.temp_tv);

        findWeather();

    }

    private void findWeather() {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=Curitiba,BR&appid=ffe1590c1fdd20ca58cab59e461f830c&units=imperial";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");

                    cidade_tv.setText(city);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("'Data:' dd.MM.yyyy '  Hora:' HH:mm:ss z");
                    String formatted_date = sdf.format(calendar.getTime());

                    data_tv.setText(formatted_date);

                    double temp_int = Double.parseDouble(temp);
                    double cent = (temp_int - 32)/ 1.8000;
                    cent = Math.round(cent);
                    int aux = (int) cent;

                    temp_tv.setText(String.valueOf(aux));


                }catch (JSONException e) {

                        e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);

    }


}

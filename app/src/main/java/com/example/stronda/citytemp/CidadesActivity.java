package com.example.stronda.citytemp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class CidadesActivity extends AppCompatActivity {

    TextView data_tv, cidade_tv, grau_tv, temp_tv, descricao_tv,umidade_tv,vento_tv;
    int id;
    CircleImageView previsao_cv;

    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();

    CountDownTimer CDT;
    int i =5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidades);

        data_tv = findViewById(R.id.date_tv);
        cidade_tv = findViewById(R.id.city_tv);
        temp_tv = findViewById(R.id.temp_tv);
        previsao_cv = findViewById(R.id.previsao_cv);
        descricao_tv = findViewById(R.id.descricao_tv);
        vento_tv = findViewById(R.id.vento_tv);
        umidade_tv = findViewById(R.id.umidade_tv);


        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Loading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.show();
        progressBarStatus = 0;


        CDT = new CountDownTimer(1200, 1000){

            public void onTick(long millisUntilFinished){
                progressBar.setMessage("Loading...");
                i--;
            }

            public void onFinish(){
                progressBar.dismiss();
            }
        }.start();

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null) {
             id = (int) bd.get("id");
             findWeather(id);
        }
    }

    private void findWeather(int id) {

        if (id == 1){
            String url = "http://api.openweathermap.org/data/2.5/weather?q=Curitiba,BR&appid=ffe1590c1fdd20ca58cab59e461f830c";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONObject wind_object = response.getJSONObject("wind");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String city = response.getString("name");
                    String description = object.getString("main");
                    String previsao = object.getString("description");
                    String umidade = String.valueOf(main_object.getInt("humidity"));
                    String vento = String.valueOf(wind_object.getDouble("speed"));




                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("'Data:' dd.MM.yyyy '  Hora:' HH:mm:ss");
                    String formatted_date = sdf.format(calendar.getTime());


                    double temp_int = Double.parseDouble(temp);
                    double cent = temp_int - 273;
                    //double cent = (temp_int - 32) / 1.8000;
                    cent = Math.round(cent);
                    int aux = (int) cent;

                    temp_tv.setText(String.valueOf(aux));
                    cidade_tv.setText(city);
                    data_tv.setText(formatted_date);
                    descricao_tv.setText(previsao);
                    vento_tv.setText(vento +" m/s");
                    umidade_tv.setText(umidade + "%");

                    SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
                    int hora = Integer.parseInt(sdf2.format(calendar.getTime()).toString());


                    if(description.equals("Clear") && (hora <=0500 || hora>=1800)){
                        previsao_cv.setImageResource(R.drawable.clear_night);
                    }
                    else if (description.equals("Clouds") && (hora <=0500 || hora>=1800)){
                        previsao_cv.setImageResource(R.drawable.cloud_night);
                    }
                    else if (description.equals("Rain") && (hora <=0500 || hora>=1800)){
                        previsao_cv.setImageResource(R.drawable.rain_night);
                    }

                    else if(description.equals("Clear") && (hora >0500 || hora<1800)){
                        previsao_cv.setImageResource(R.drawable.clear_day);
                    }
                    else if(description.equals("Clouds") && (hora >0500 || hora<1800)){
                        previsao_cv.setImageResource(R.drawable.cloud_day);
                    }
                    else if(description.equals("Rain") && (hora >0500 || hora<1800)){
                        previsao_cv.setImageResource(R.drawable.rain_day);
                    }

                } catch (JSONException e) {

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

        if (id == 2){
            String url = "http://api.openweathermap.org/data/2.5/weather?q=Sao Paulo,BR&appid=ffe1590c1fdd20ca58cab59e461f830c";

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject main_object = response.getJSONObject("main");
                        JSONObject wind_object = response.getJSONObject("wind");
                        JSONArray array = response.getJSONArray("weather");
                        JSONObject object = array.getJSONObject(0);
                        String temp = String.valueOf(main_object.getDouble("temp"));
                        String city = response.getString("name");
                        String description = object.getString("main");
                        String previsao = object.getString("description");
                        String umidade = String.valueOf(main_object.getInt("humidity"));
                        String vento = String.valueOf(wind_object.getDouble("speed"));


                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("'Data:' dd.MM.yyyy '  Hora:' HH:mm:ss");
                        String formatted_date = sdf.format(calendar.getTime());



                        double temp_int = Double.parseDouble(temp);
                        double cent = temp_int - 273;
                        //double cent = (temp_int - 32) / 1.8000;
                        cent = Math.round(cent);
                        int aux = (int) cent;

                        cidade_tv.setText(city);
                        data_tv.setText(formatted_date);
                        temp_tv.setText(String.valueOf(aux));
                        descricao_tv.setText(previsao);
                        vento_tv.setText(vento +" m/s");
                        umidade_tv.setText(umidade + "%");


                        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
                        int hora = Integer.parseInt(sdf2.format(calendar.getTime()).toString());

                        if(description.equals("Clear") && (hora <=0500 || hora>=1800)){
                            previsao_cv.setImageResource(R.drawable.clear_night);
                        }
                        else if (description.equals("Clouds") && (hora <=0500 || hora>=1800)){
                            previsao_cv.setImageResource(R.drawable.cloud_night);
                        }
                        else if (description.equals("Rain") && (hora <=0500 || hora>=1800)){
                            previsao_cv.setImageResource(R.drawable.rain_night);
                        }

                        else if(description.equals("Clear") && (hora >0500 || hora<1800)){
                            previsao_cv.setImageResource(R.drawable.clear_day);
                        }
                        else if(description.equals("Clouds") && (hora >0500 || hora<1800)){
                            previsao_cv.setImageResource(R.drawable.cloud_day);
                        }
                        else if(description.equals("Rain") && (hora >0500 || hora<1800)){
                            previsao_cv.setImageResource(R.drawable.rain_day);
                        }

                    } catch (JSONException e) {

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

        if (id == 3){
            String url = "http://api.openweathermap.org/data/2.5/weather?q=Florianopolis,BR&appid=ffe1590c1fdd20ca58cab59e461f830c";

            JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject main_object = response.getJSONObject("main");
                        JSONObject wind_object = response.getJSONObject("wind");
                        JSONArray array = response.getJSONArray("weather");
                        JSONObject object = array.getJSONObject(0);
                        String temp = String.valueOf(main_object.getDouble("temp"));
                        String city = response.getString("name");
                        String description = object.getString("main");
                        String previsao = object.getString("description");
                        String umidade = String.valueOf(main_object.getInt("humidity"));
                        String vento = String.valueOf(wind_object.getDouble("speed"));



                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("'Data:' dd.MM.yyyy '  Hora:' HH:mm:ss");
                        String formatted_date = sdf.format(calendar.getTime());



                        double temp_int = Double.parseDouble(temp);
                        double cent = temp_int - 273;
                        //double cent = (temp_int - 32) / 1.8000;
                        cent = Math.round(cent);
                        int aux = (int) cent;

                        temp_tv.setText(String.valueOf(aux));
                        cidade_tv.setText(city);
                        data_tv.setText(formatted_date);
                        descricao_tv.setText(previsao);
                        vento_tv.setText(vento +" m/s");
                        umidade_tv.setText(umidade + "%");

                        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
                        int hora = Integer.parseInt(sdf2.format(calendar.getTime()).toString());

                        if(description.equals("Clear") && (hora <=0500 || hora>=1800)){
                            previsao_cv.setImageResource(R.drawable.clear_night);
                        }
                        else if (description.equals("Clouds") && (hora <=0500 || hora>=1800)){
                            previsao_cv.setImageResource(R.drawable.cloud_night);
                        }
                        else if (description.equals("Rain") && (hora <=0500 || hora>=1800)){
                            previsao_cv.setImageResource(R.drawable.rain_night);
                        }

                        else if(description.equals("Clear") && (hora >0500 || hora<1800)){
                            previsao_cv.setImageResource(R.drawable.clear_day);
                        }
                        else if(description.equals("Clouds") && (hora >0500 || hora<1800)){
                            previsao_cv.setImageResource(R.drawable.cloud_day);
                        }
                        else if(description.equals("Rain") && (hora >0500 || hora<1800)){
                            previsao_cv.setImageResource(R.drawable.rain_day);
                        }

                    } catch (JSONException e) {

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


}

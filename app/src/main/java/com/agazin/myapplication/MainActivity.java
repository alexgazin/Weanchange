package com.agazin.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.agazin.myapplication.api.*;
import com.agazin.myapplication.ExchangeModel.*;
import com.agazin.myapplication.WeatherModel.Weather;


import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.valueOf;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static String EXCHANGE_URL = "http://www.cbr-xml-daily.ru";
    private static String WEATHER_URL = "http://api.openweathermap.org";
    private static String WEATHER_API = "82eff2c845841c89c837d4e125613d83";

    private TextView mUsd, mEuro, mPreviousUsd, mPreviousEur, mDifferenceUsd, mDifferenceEur;
    private TextView mCelcuis, mNameTown, mWind, mHumidity, mWeatherSys;
    private ImageView mIconWeather;
    private SharedPreferences mSp;
    private String mChoiseTownFromSettings;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressDialog mLoadDataDialog;
    private NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsd = (TextView) findViewById(R.id.usd);
        mEuro = (TextView) findViewById(R.id.euro);
        mCelcuis = (TextView) findViewById(R.id.celcius);
        mIconWeather = (ImageView) findViewById(R.id.iconWeather);
        mNameTown = (TextView) findViewById(R.id.townName);
        mWind = (TextView) findViewById(R.id.wind);
        mHumidity = (TextView) findViewById(R.id.humidity);
        mWeatherSys = (TextView) findViewById(R.id.weatherSys);
        mPreviousEur = (TextView) findViewById(R.id.previousEuro);
        mPreviousUsd = (TextView) findViewById(R.id.previousUsd);
        mSp = PreferenceManager.getDefaultSharedPreferences(this);
        mDifferenceUsd = (TextView) findViewById(R.id.differenceUsd);
        mDifferenceEur = (TextView) findViewById(R.id.differenceEur);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mNotificationManager=(NotificationManager) this.getApplicationContext().getSystemService(this.NOTIFICATION_SERVICE);

        loadExchangeRates();
        loadWeather();
    }

    public void onRefresh() {
        // начинаем показывать прогресс
        mSwipeRefreshLayout.setRefreshing(true);
        // ждем 3 секунды и прячем прогресс
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadWeather();
                loadExchangeRates();
            }
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWeather();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void showSettings(MenuItem item) {
        Intent intent = new Intent(this, com.agazin.myapplication.MyPreferenceActivity.class);
        startActivity(intent);
    }



        public void loadExchangeRates() {
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(EXCHANGE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            ExchangeRatesApi service = client.create(ExchangeRatesApi.class);
            Call<ExchangeRates> call = service.getValues();// инфо о валюте
            call.enqueue(new Callback<ExchangeRates>() {
                @Override
                public void onResponse(Call<ExchangeRates> call, Response<ExchangeRates> response) {
                    try {

                        String usdd = ("$ " + response.body().getValute().getuSD().getValue().toString());
                        mUsd.setText(usdd.substring(0, 7));

                        String previousUsdd = (response.body().getValute().getuSD().getPrevious().toString());
                        mPreviousUsd.setText("$ " + previousUsdd.substring(0, 5));

                        String euroo = ("€ " + response.body().getValute().geteUR().getValue().toString());
                        mEuro.setText(euroo.substring(0, 7));

                        String previousEurr = (response.body().getValute().geteUR().getPrevious().toString());
                        mPreviousEur.setText("€ " + previousEurr.substring(0, 5));

                        // Делаем разницу с прошлым днем по доллару
                        float a = response.body().getValute().getuSD().getValue().floatValue();
                        float b = response.body().getValute().getuSD().getPrevious().floatValue();
                        float c = a - b;
                        if (c > 0) {
                            mDifferenceUsd.setText("+" + (String.valueOf(c).substring(0,4)));
                            mDifferenceUsd.setTextColor(Color.GREEN);
                        } else {
                            mDifferenceUsd.setText((String.valueOf(c).substring(0,4)));
                            mDifferenceUsd.setTextColor(Color.RED);
                        }

                        // Делаем разницу с прошлым днем по евро
                        float d = response.body().getValute().geteUR().getValue().floatValue();
                        float e = response.body().getValute().geteUR().getPrevious().floatValue();
                        float f = d - e;
                        if (f > 0) {
                            mDifferenceEur.setText("+" + (String.valueOf(f).substring(0,4)));
                            mDifferenceEur.setTextColor(Color.GREEN);
                        } else {
                            mDifferenceEur.setText((String.valueOf(f).substring(0,4)));
                            mDifferenceEur.setTextColor(Color.RED);
                        }
                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ExchangeRates> call, Throwable t) {
                    Log.d("Error", t.toString());
                }
            });
        }

        public void loadWeather() {
            final ProgressDialog loading = ProgressDialog.show(this, "Загрузка", "Пожалуйста, подождите...", false, false);

            mChoiseTownFromSettings = mSp.getString("choiseTown", "");

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(WEATHER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WeatherApi service = client.create(WeatherApi.class);
            Call<Weather> call = service.getValues(
                    WEATHER_API,
                    mChoiseTownFromSettings,
                    "metric");

            call.enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {
                    try {
                        mSwipeRefreshLayout.setRefreshing(false);
                        loading.dismiss();


                        String celcuiss = (response.body().getMain().getTemp().toString());
                        mCelcuis.setText((celcuiss.substring(0,3)).replace(".", "") + " °C");

                        mNameTown.setText(response.body().getName());

                        String windd = (response.body().getWind().getSpeed().toString());
                        mWind.setText("Ветер: " + (windd.substring(0,2)).replace(".", "")  + " м/с");

                        mHumidity.setText("Влажность: " + response.body().getMain().getHumidity() + "%");
                        mWeatherSys.setText(response.body().getWeather().get(0).getMain());

                        // Делаем картинку погоды (день)
                        if (response.body().getWeather().get(0).getIcon().equals("01d")) {
                            mIconWeather.setImageResource(R.drawable.sun);
                        } else if (response.body().getWeather().get(0).getIcon().equals("02d")) {
                            mIconWeather.setImageResource(R.drawable.fewcloud);
                        } else if (response.body().getWeather().get(0).getIcon().equals("03d")) {
                            mIconWeather.setImageResource(R.drawable.cloud);
                        } else if (response.body().getWeather().get(0).getIcon().equals("04d")) {
                            mIconWeather.setImageResource(R.drawable.cloud);
                        } else if (response.body().getWeather().get(0).getIcon().equals("09d")) {
                            mIconWeather.setImageResource(R.drawable.rain);
                        } else if (response.body().getWeather().get(0).getIcon().equals("10d")) {
                            mIconWeather.setImageResource(R.drawable.rain);
                        } else if (response.body().getWeather().get(0).getIcon().equals("11d")) {
                            mIconWeather.setImageResource(R.drawable.storm);
                        } else if (response.body().getWeather().get(0).getIcon().equals("13d")) {
                            mIconWeather.setImageResource(R.drawable.snowflake);
                        } else if (response.body().getWeather().get(0).getIcon().equals("50d")) {
                            mIconWeather.setImageResource(R.drawable.haze);
                        } else {
                            mIconWeather.setImageResource(R.drawable.sun);
                        }

                        // Делаем картинку погоды (ночь)
                        if (response.body().getWeather().get(0).getIcon().equals("01n")) {
                            mIconWeather.setImageResource(R.drawable.sun);
                        } else if (response.body().getWeather().get(0).getIcon().equals("02n")) {
                            mIconWeather.setImageResource(R.drawable.fewcloud);
                        } else if (response.body().getWeather().get(0).getIcon().equals("03n")) {
                            mIconWeather.setImageResource(R.drawable.cloud);
                        } else if (response.body().getWeather().get(0).getIcon().equals("04n")) {
                            mIconWeather.setImageResource(R.drawable.cloud);
                        } else if (response.body().getWeather().get(0).getIcon().equals("09n")) {
                            mIconWeather.setImageResource(R.drawable.rain);
                        } else if (response.body().getWeather().get(0).getIcon().equals("10n")) {
                            mIconWeather.setImageResource(R.drawable.rain);
                        } else if (response.body().getWeather().get(0).getIcon().equals("11n")) {
                            mIconWeather.setImageResource(R.drawable.storm);
                        } else if (response.body().getWeather().get(0).getIcon().equals("13n")) {
                            mIconWeather.setImageResource(R.drawable.snowflake);
                        } else if (response.body().getWeather().get(0).getIcon().equals("50n")) {
                            mIconWeather.setImageResource(R.drawable.haze);
                        } else {
                            mIconWeather.setImageResource(R.drawable.sun);
                        }
                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {
                    Log.d("Error", t.toString());
                }
            });
        }


    public void notification(View view) {
        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPending = stackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle("Погода") // main title of the notification
                .setContentText(mNameTown.getText() + " " + mCelcuis.getText() + " °С"+ " | " + mUsd.getText() + " " + mEuro.getText()) // notification text
                .setContentIntent(resultPending); // notification intent

        mNotificationManager.notify(10, mBuilder.build());
    }
}


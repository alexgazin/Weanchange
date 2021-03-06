package com.agazin.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agazin.myapplication.BitcoinModel.Crypto;
import com.agazin.myapplication.EthereumModel.Ethereum;
import com.agazin.myapplication.ExWeatherModel.ExWeather;
import com.agazin.myapplication.ExchangeModel.ExchangeRates;
import com.agazin.myapplication.Settings.MyPreferenceActivity;
import com.agazin.myapplication.WeatherModel.Weather;
import com.agazin.myapplication.api.BitcoinApi;
import com.agazin.myapplication.api.EthereumApi;
import com.agazin.myapplication.api.ExWeatherApi;
import com.agazin.myapplication.api.ExchangeRatesApi;
import com.agazin.myapplication.api.WeatherApi;
import com.agazin.myapplication.api.WeatherbitApi;
import com.agazin.myapplication.weatherbitModel.Weatherbit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    private static String EXCHANGE_URL = "http://www.cbr-xml-daily.ru";
    private static String WEATHER_URL = "http://api.openweathermap.org";
    private static String WEATHER_API = "82eff2c845841c89c837d4e125613d83";
    private static String WEATHERBIT_URL = "https://api.weatherbit.io";
    private static String WEATHERBIT_API = "d1d9fdf32c064be88d66e98ab9777354";
    private static String CRYPTO_API = "https://api.cryptonator.com";
    private static SharedPreferences mSharedPref;
    private static SharedPreferences.Editor mEditor;
    private TextView mUsd, mEuro, mBtc, mEth;
    private TextView mDate1, mDate2, mDate3, mDate4, mDate5, mCelcius1, mCelcius2, mCelcius3, mCelcius4,
            mCelcius5, mApparentTemp, mDiscription1, mDiscription2, mDiscription3, mDiscription4, mDiscription5;
    private TextView mCelcuis, mNameTown, mWind, mHumidity, mWeatherSys, mLastUpdate;
    private ImageView mIconWeather;
    private SharedPreferences mSp;
    private String mChoiseTownFromSettings;
    private ProgressDialog mLoadDataDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsd = findViewById(R.id.usd);
        mEuro = findViewById(R.id.euro);
        mBtc = findViewById(R.id.btc);
        mEth = findViewById(R.id.eth);
        mCelcuis = findViewById(R.id.celcius);
        mIconWeather = findViewById(R.id.iconWeather);
        mNameTown = findViewById(R.id.townName);
        mWind = findViewById(R.id.wind);
        mHumidity = findViewById(R.id.humidity);
        mWeatherSys = findViewById(R.id.weatherSys);
        mSp = PreferenceManager.getDefaultSharedPreferences(this);
        mDate1 = findViewById(R.id.date1);
        mCelcius1 = findViewById(R.id.celcius1);
        mDiscription1 = findViewById(R.id.discription1);
        mDate2 = findViewById(R.id.date2);
        mCelcius2 = findViewById(R.id.celcius2);
        mDiscription2 = findViewById(R.id.discription2);
        mDate3 = findViewById(R.id.date3);
        mCelcius3 = findViewById(R.id.celcius3);
        mDiscription3 = findViewById(R.id.discription3);
        mDate4 = findViewById(R.id.date4);
        mCelcius4 = findViewById(R.id.celcius4);
        mDiscription4 = findViewById(R.id.discription4);
        mDate5 = findViewById(R.id.date5);
        mCelcius5 = findViewById(R.id.celcius5);
        mDiscription5 = findViewById(R.id.discription5);
        mApparentTemp = findViewById(R.id.weatherApparent);
        mLastUpdate = findViewById(R.id.lastUpdate);

        checkConnection();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWeather();
        loadExWeather();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void showSettings(MenuItem item) {
        Intent intent = new Intent(this, MyPreferenceActivity.class);
        startActivity(intent);
    }

    public void refreshStatus(MenuItem item) {

        checkConnection();

    }

    // Грузим курс валют (usd, eur)
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
                    mUsd.setText(usdd.substring(0, 7) + "p");

                    String euroo = ("€ " + response.body().getValute().geteUR().getValue().toString());
                    mEuro.setText(euroo.substring(0, 7) + "p");

                    // Задаем время последнего апдейта информации
                    DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
                    String date11 = df.format(Calendar.getInstance().getTime());
                    mLastUpdate.setText("Обновлено: " + date11);

                    // сохраняем всю информацию в sp
                    saveAllDataToSharedPref();
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ExchangeRates> call, Throwable t) {
                Log.d("Error", t.toString());
                //mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    // Грузим курс криптовалют (bitcoin)
    public void loadBitcoin() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(CRYPTO_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BitcoinApi service = client.create(BitcoinApi.class);
        Call<Crypto> call = service.getValues();// инфо о криптовалюте
        call.enqueue(new Callback<Crypto>() {
            @Override
            public void onResponse(Call<Crypto> call, Response<Crypto> response) {
                try {
                    String btc = (response.body().getTicker().getPrice());
                    mBtc.setText("B " + btc.substring(0, btc.indexOf(".")) + "$");

                    // Задаем время последнего апдейта информации
//                    DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
//                    String date11 = df.format(Calendar.getInstance().getTime());
//                    mLastUpdate.setText("Обновлено: "  + date11);

                    // сохраняем всю информацию в sp
                    saveAllDataToSharedPref();
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Crypto> call, Throwable t) {
                Log.d("Error", t.toString());
                //mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    // Грузим курс криптовалют (eth)
    public void loadEthereum() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(CRYPTO_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EthereumApi service = client.create(EthereumApi.class);
        Call<Ethereum> call = service.getValues();// инфо о криптовалюте
        call.enqueue(new Callback<Ethereum>() {
            @Override
            public void onResponse(Call<Ethereum> call, Response<Ethereum> response) {
                try {
                    String ethereum = (response.body().getTicker().getPrice());
                    mEth.setText("E " + ethereum.substring(0, ethereum.indexOf(".")) + "$");

                    // Задаем время последнего апдейта информации
//                    DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
//                    String date11 = df.format(Calendar.getInstance().getTime());
//                    mLastUpdate.setText("Обновлено: "  + date11);

                    // сохраняем всю информацию в sp
                    saveAllDataToSharedPref();
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Ethereum> call, Throwable t) {
                Log.d("Error", t.toString());
                //mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    // Грузим температуру с weatherbit.io (как ощущается в градусах)
    public void loadApparentTemp() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(WEATHERBIT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherbitApi service = client.create(WeatherbitApi.class);
        Call<Weatherbit> call = service.getValues(mChoiseTownFromSettings, WEATHERBIT_API);// инфо о криптовалюте
        call.enqueue(new Callback<Weatherbit>() {
            @Override
            public void onResponse(Call<Weatherbit> call, Response<Weatherbit> response) {
                try {
                    Double temp = (response.body().getData().get(0).getAppTemp());
                    String apparentT = String.valueOf(temp);
                    mApparentTemp.setText("Ощущается как: " + apparentT.substring(0, apparentT.indexOf(".")) + "°");

                    // Задаем время последнего апдейта информации
//                    DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
//                    String date11 = df.format(Calendar.getInstance().getTime());
//                    mLastUpdate.setText("Обновлено: "  + date11);

                    // сохраняем всю информацию в sp
                    saveAllDataToSharedPref();
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Weatherbit> call, Throwable t) {
                Log.d("Error", t.toString());
                //mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    // Грузим основную погоду
    public void loadWeather() {

        if (mSp.getString("choiseTown", "").equals("")) {
            mChoiseTownFromSettings = mSp.getString("choiseTown", "Moscow");
        } else {
            mChoiseTownFromSettings = mSp.getString("choiseTown", "");
        }

        Retrofit client = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi service = client.create(WeatherApi.class);
        Call<Weather> call = service.getValues(
                WEATHER_API,
                mChoiseTownFromSettings,
                "metric",
                "ru");

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                try {
                    //mSwipeRefreshLayout.setRefreshing(false);
                    mLoadDataDialog.dismiss();


                    String celcuiss4 = (response.body().getMain().getTemp().toString());
                    mCelcuis.setText((celcuiss4.substring(0, celcuiss4.indexOf(".")) + "°"));

                    mNameTown.setText(response.body().getName());

                    String windd = (response.body().getWind().getSpeed().toString());
                    mWind.setText("Ветер: " + (windd.substring(0, 2)).replace(".", "") + " м/с");

                    mHumidity.setText("Влажность: " + response.body().getMain().getHumidity() + "%");
                    mWeatherSys.setText(response.body().getWeather().get(0).getDescription());

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

                    // Задаем время последнего апдейта информации
                    DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
                    String date11 = df.format(Calendar.getInstance().getTime());
                    mLastUpdate.setText("Обновлено: " + date11);

                    saveAllDataToSharedPref();
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d("Error", t.toString());

                //mSwipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    // Грузим расширенную погоду
    public void loadExWeather() {

        if (mSp.getString("choiseTown", "").equals("")) {
            mChoiseTownFromSettings = mSp.getString("choiseTown", "Moscow");
        } else {
            mChoiseTownFromSettings = mSp.getString("choiseTown", "");
        }

        Retrofit client = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExWeatherApi service = client.create(ExWeatherApi.class);
        Call<ExWeather> call = service.getValues(
                WEATHER_API,
                mChoiseTownFromSettings,
                "metric",
                "ru");

        call.enqueue(new Callback<ExWeather>() {
            @Override
            public void onResponse(Call<ExWeather> call, Response<ExWeather> response) {
                try {
                    //mSwipeRefreshLayout.setRefreshing(false);

                    // грузим первую погоду на следующий день
                    String date = (response.body().getList().get(5).getDtTxt().toString());
                    String date_ = (date.substring(5, 7));
                    String date__ = (date.substring(8, 10));
                    mDate1.setText((date__ + "/" + date_));

                    String celcuiss = (response.body().getList().get(5).getMain().getTemp().toString());
                    mCelcius1.setText((celcuiss.substring(0, celcuiss.indexOf(".")) + "°"));

                    mDiscription1.setText(response.body().getList().get(5).getWeather().get(0).getDescription().toString());
                    ////////////

                    // грузим вторую погоду через день
                    String date1 = (response.body().getList().get(13).getDtTxt().toString());
                    String date___ = (date1.substring(5, 7));
                    String date____ = (date1.substring(8, 10));
                    mDate2.setText((date____ + "/" + date___));

                    String celcuiss1 = (response.body().getList().get(13).getMain().getTemp().toString());
                    mCelcius2.setText((celcuiss1.substring(0, celcuiss1.indexOf(".")) + "°"));

                    mDiscription2.setText(response.body().getList().get(13).getWeather().get(0).getDescription().toString());
                    ////////////

                    // грузим третью погоду через два дня
                    String date3 = (response.body().getList().get(21).getDtTxt().toString());
                    String date_____ = (date3.substring(5, 7));
                    String date______ = (date3.substring(8, 10));
                    mDate3.setText((date______ + "/" + date_____));

                    String celcuiss3 = (response.body().getList().get(21).getMain().getTemp().toString());
                    mCelcius3.setText((celcuiss3.substring(0, celcuiss3.indexOf(".")) + "°"));

                    mDiscription3.setText(response.body().getList().get(21).getWeather().get(0).getDescription().toString());

                    // грузим четвертую погоду через два дня
                    String date4 = (response.body().getList().get(29).getDtTxt().toString());
                    String date_______ = (date4.substring(5, 7));
                    String date________ = (date4.substring(8, 10));
                    mDate4.setText((date________ + "/" + date_______));

                    String celcuiss4 = (response.body().getList().get(29).getMain().getTemp().toString());
                    mCelcius4.setText((celcuiss4.substring(0, celcuiss4.indexOf(".")) + "°"));

                    mDiscription4.setText(response.body().getList().get(29).getWeather().get(0).getDescription().toString());

                    // грузим пятую погоду через два дня
                    String date5 = (response.body().getList().get(37).getDtTxt().toString());
                    String date_________ = (date5.substring(5, 7));
                    String date__________ = (date5.substring(8, 10));
                    mDate5.setText((date__________ + "/" + date_________));

                    String celcuiss5 = (response.body().getList().get(37).getMain().getTemp().toString());
                    mCelcius5.setText((celcuiss5.substring(0, celcuiss5.indexOf(".")) + "°"));

                    mDiscription5.setText(response.body().getList().get(37).getWeather().get(0).getDescription().toString());

                    // Задаем время последнего апдейта информации
                    DateFormat df = new SimpleDateFormat("dd MMM, HH:mm");
                    String date11 = df.format(Calendar.getInstance().getTime());
                    mLastUpdate.setText("Обновлено: " + date11);

                    saveAllDataToSharedPref();
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ExWeather> call, Throwable t) {
                Log.d("Error", t.toString());

                //mSwipeRefreshLayout.setRefreshing(false);

            }
        });
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void checkConnection() {
        if (isOnline()) {
            mLoadDataDialog = ProgressDialog.show(this, "Загрузка", "Пожалуйста, подождите...", false, true);
            loadWeather();
            loadExWeather();
            loadExchangeRates();
            loadBitcoin();
            loadEthereum();
            loadApparentTemp();
        } else {
            mLoadDataDialog.cancel();
            loadAllDataFromSharedPref();

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нет соединения с интернетом", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void saveAllDataToSharedPref() {
        mSharedPref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        mEditor = mSharedPref.edit();
        mEditor.putString("nameTown", mNameTown.getText().toString());
        mEditor.putString("Celcuis", mCelcuis.getText().toString());
        mEditor.putString("WeatherSys", mWeatherSys.getText().toString());
        mEditor.putString("Wind", mWind.getText().toString());
        mEditor.putString("Humidity", mHumidity.getText().toString());
        mEditor.putString("Date1", mDate1.getText().toString());
        mEditor.putString("Date2", mDate2.getText().toString());
        mEditor.putString("Date3", mDate3.getText().toString());
        mEditor.putString("Celcius1", mCelcius1.getText().toString());
        mEditor.putString("Celcius2", mCelcius2.getText().toString());
        mEditor.putString("Celcius3", mCelcius3.getText().toString());
        mEditor.putString("Discription1", mDiscription1.getText().toString());
        mEditor.putString("Discription2", mDiscription2.getText().toString());
        mEditor.putString("Discription3", mDiscription3.getText().toString());
        mEditor.putString("Usd", mUsd.getText().toString());
        mEditor.putString("Eur", mEuro.getText().toString());
        mEditor.putString("lastUpdate", mLastUpdate.getText().toString());
        mEditor.apply();
    }

    public void loadAllDataFromSharedPref() {
        mSharedPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String nameTown = mSharedPref.getString("nameTown", "");
        mNameTown.setText(nameTown);
        String Celcuis = mSharedPref.getString("Celcuis", "");
        mCelcuis.setText(Celcuis);
        String WeatherSys = mSharedPref.getString("WeatherSys", "");
        mWeatherSys.setText(WeatherSys);
        String Wind = mSharedPref.getString("Wind", "");
        mWind.setText(Wind);
        String Humidity = mSharedPref.getString("Humidity", "");
        mHumidity.setText(Humidity);
        String Date1 = mSharedPref.getString("Date1", "");
        mDate1.setText(Date1);
        String Date2 = mSharedPref.getString("Date2", "");
        mDate2.setText(Date2);
        String Date3 = mSharedPref.getString("Date3", "");
        mDate3.setText(Date3);
        String Celcius1 = mSharedPref.getString("Celcius1", "");
        mCelcius1.setText(Celcius1);
        String Celcius2 = mSharedPref.getString("Celcius2", "");
        mCelcius2.setText(Celcius2);
        String Celcius3 = mSharedPref.getString("Celcius3", "");
        mCelcius3.setText(Celcius3);
        String Discription1 = mSharedPref.getString("Discription1", "");
        mDiscription1.setText(Discription1);
        String Discription2 = mSharedPref.getString("Discription2", "");
        mDiscription2.setText(Discription2);
        String Discription3 = mSharedPref.getString("Discription3", "");
        mDiscription3.setText(Discription3);
        String Usd = mSharedPref.getString("Usd", "");
        mUsd.setText(Usd);
        String Eur = mSharedPref.getString("Eur", "");
        mEuro.setText(Eur);
        String difUsd = mSharedPref.getString("difUsd", "");
        String lastUpdate = mSharedPref.getString("lastUpdate", "");
        mLastUpdate.setText(lastUpdate);
    }
}


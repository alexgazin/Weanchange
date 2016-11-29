package com.agazin.myapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by ayugazin on 17.11.16.
 */

public class MyPreferenceActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}

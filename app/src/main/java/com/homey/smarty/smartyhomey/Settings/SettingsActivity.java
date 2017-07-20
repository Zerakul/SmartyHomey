package com.homey.smarty.smartyhomey.Settings;

import android.preference.PreferenceActivity;


import com.homey.smarty.smartyhomey.R;

import java.util.List;


public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onBuildHeaders(List<Header> target)
    {
        loadHeadersFromResource(R.xml.headers_preference, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName)
    {
        return  UISettingsFragment.class.getName().equals(fragmentName);
    }
}

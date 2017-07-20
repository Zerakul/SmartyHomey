package com.homey.smarty.smartyhomey.Settings;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homey.smarty.smartyhomey.ConstantValues;
import com.homey.smarty.smartyhomey.R;


public class UISettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private final static String TAG = UISettingsFragment.class.getSimpleName();
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.ui_preference);
        findPreference(ConstantValues.UI_VOICE_SWITCH).setOnPreferenceChangeListener(this);
        findPreference(ConstantValues.REQUEST_LOGIN).setOnPreferenceChangeListener(this);
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.i(TAG, String.format("onPreferenceChange(key: %s, newValue: %s)", preference.getKey(), newValue.toString()));


        switch (preference.getKey()) {
            case ConstantValues.UI_VOICE_SWITCH:
                editor.putBoolean(ConstantValues.UI_VOICE_SWITCH,(boolean)newValue);
                editor.commit();
                break;

            case ConstantValues.REQUEST_LOGIN:
                editor.putBoolean(ConstantValues.REQUEST_LOGIN,(boolean)newValue);
                editor.commit();
                break;

        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i(TAG, String.format("onSharedPreferenceChanged(key: %s)", key));
    }
}

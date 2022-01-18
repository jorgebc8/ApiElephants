package com.example.apielephants.Fragment;


import android.os.Bundle;
import android.widget.Switch;

import androidx.preference.PreferenceFragmentCompat;

import com.example.apielephants.R;

public class Fragmento extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragment,rootKey);
    }
}

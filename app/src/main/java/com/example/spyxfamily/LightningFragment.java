package com.example.spyxfamily;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class LightningFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}

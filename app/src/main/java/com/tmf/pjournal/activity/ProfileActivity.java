package com.tmf.pjournal.activity;

import android.os.Bundle;

import com.tmf.pjournal.R;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);
        disableNavItem(R.id.nav_profile);
    }
}

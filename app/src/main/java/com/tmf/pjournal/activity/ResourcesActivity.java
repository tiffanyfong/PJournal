package com.tmf.pjournal.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.tmf.pjournal.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResourcesActivity extends BaseActivity {

    private static final String WOMENS_HEALTH_URL = "https://www.womenshealth.gov/a-z-topics/menstruation-and-menstrual-cycle";
    private static final String PLANNED_PARENTHOOD_URL = "https://www.plannedparenthood.org/learn/womens-health/menstruation";


    @OnClick(R.id.btnPlannedParenthood) public void btnPlannedPressed() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PLANNED_PARENTHOOD_URL)));
    }

    @OnClick(R.id.btnWomensHealth) public void btnWomensPressed() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(WOMENS_HEALTH_URL)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resources);
        ButterKnife.bind(this);
        disableNavItem(R.id.nav_resources);
    }
}

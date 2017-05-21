package com.tmf.pjournal.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.tmf.pjournal.MainApplication;
import com.tmf.pjournal.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profile);
        disableNavItem(R.id.nav_profile);
        ButterKnife.bind(this);
        ((MainApplication) getApplication()).openRealm();
    }

    @OnClick(R.id.btnClearAllData) public void clearAllDataPressed() {
        showAreYouSureDialog(getString(R.string.are_you_sure_delete_data));
    }

    private void clearRealmData() {
        ((MainApplication) getApplication()).getRealm().beginTransaction();
        ((MainApplication) getApplication()).getRealm().deleteAll();
        ((MainApplication) getApplication()).getRealm().commitTransaction();
    }

    public void showAreYouSureDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearRealmData();
            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    protected void onDestroy() {
        ((MainApplication) getApplication()).closeRealm();
        super.onDestroy();
    }
}

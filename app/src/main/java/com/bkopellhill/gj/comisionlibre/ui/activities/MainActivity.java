package com.bkopellhill.gj.comisionlibre.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import com.bitcodeing.framework.AppSettings;
import com.bitcodeing.framework.activities.BaseActivity;
import com.bkopellhill.gj.comisionlibre.BuildConfig;
import com.bkopellhill.gj.comisionlibre.R;
import com.bkopellhill.gj.comisionlibre.core.adapters.HomeAdapter;
import com.bkopellhill.gj.comisionlibre.core.utils.Utils;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * @author Luis Hernandez
 * @version 0.0.1
 */

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends BaseActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        if (viewPager != null && tabLayout != null){
            HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager(),this);
            viewPager.setAdapter(adapter);

            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.basic_mode)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.advance_mode)));

            tabLayout.setupWithViewPager(viewPager);
        }

        if (Utils.compareVersionNames(PreferenceManager.getDefaultSharedPreferences(this)
                .getString(AppSettings.PREFERENCE_VERSION,"0.65"),BuildConfig.VERSION_NAME) == -1){
            logChange(1);
        }
    }

    @Override
    protected void onResume() {
        setTitle(getString(R.string.app_name));
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_main, mimenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_about:
                logChange(2);
                return true;
            case R.id.action_help:
                AlertDialog.Builder dialog =new AlertDialog.Builder(this);
                dialog.setTitle(R.string.menu_help_titulo)
                        .setMessage(R.string.menu_help_contenido)
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.create();
                dialog.show();
                return true;
            case R.id.action_exit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logChange(int orig){

        AlertDialog.Builder dialog =new AlertDialog.Builder(this);
        dialog.setTitle( orig == 1? R.string.strchgl_titulo : R.string.menu_about_titulo)
            .setMessage( orig == 1? R.string.strchgl_contenido : R.string.menu_about_contenido)
            .setCancelable(false)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
              PreferenceManager.getDefaultSharedPreferences(MainActivity.this)
              .edit()
              .putString(AppSettings.PREFERENCE_VERSION,BuildConfig.VERSION_NAME)
              .apply();
                    }
                });
        dialog.create();
        dialog.show();
    }

    @Override
    public int getImageId() {
        return 0;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getToolbarTitleId() {
        return R.id.title;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getAppBarId() {
        return R.id.toolbar_main;
    }

    @Override
    protected boolean hasAccount() {
        return false;
    }
}

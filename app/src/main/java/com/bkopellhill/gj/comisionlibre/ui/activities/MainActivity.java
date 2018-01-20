package com.bkopellhill.gj.comisionlibre.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import com.bitcodeing.framework.activities.BaseActivity;
import com.bkopellhill.gj.comisionlibre.R;
import com.bkopellhill.gj.comisionlibre.core.adapters.HomeAdapter;
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
        AlertDialog.Builder dialog =new AlertDialog.Builder(this);
        switch (item.getItemId()){
            case R.id.action_about:
                dialog.setTitle(R.string.menu_about_titulo)
                    .setMessage(R.string.menu_about_contenido)
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
            case R.id.action_help:
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
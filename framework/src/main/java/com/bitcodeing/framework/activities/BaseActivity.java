package com.bitcodeing.framework.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import com.bitcodeing.framework.AppSettings;
import com.bitcodeing.framework.callbacks.PermissionStateCallBack;
import com.bitcodeing.framework.util.DisplayUtil;
import com.bitcodeing.framework.views.TextView;
import com.google.firebase.analytics.FirebaseAnalytics;
import static android.view.WindowManager.LayoutParams;

/**
 * Base Activity class
 *
 * @author Luis Hernandez
 * @version 0.0.1
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public abstract class BaseActivity extends AppCompatActivity
        implements PermissionStateCallBack, OnRequestPermissionsResultCallback {

    protected final int REQUEST_PERMISSION_AT_RUNTIME = 10;
    private final String TAG = "BaseActivity";

    private CharSequence mTitle;

    private Toolbar toolbar;
    private PermissionStateCallBack permissionStateCallBack;
    private SecurityManager securityManager;

    /**
     * FirebaseAnalytics
     */
    protected FirebaseAnalytics mFirebaseAnalytics;

    /**
     * flags
     */
    protected boolean isAuthenticated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        setToolbar(null);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        /* setting up permissionStateCallBack */
        permissionStateCallBack = this;
        /* checking up authentication */
        if (!ignoreAuth()) {
            Bundle extras = getIntent() != null ? getIntent().getExtras() : null;
            if (extras != null && extras.containsKey(AppSettings.ACCOUNT_FORCE_LOGOUT)) {
                extras.remove(AppSettings.ACCOUNT_FORCE_LOGOUT);
                showLogin();
                isAuthenticated = false;
            } else {
                isAuthenticated = hasAccount();
                if (!isAuthenticated)
                    showLogin();
            }
        }
    }

    public void hideKeyword() {
        try {
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive())
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        /* checking up authentication */
        if (isAuthenticated && !ignoreAuth()) {
            Bundle extras = intent != null ? intent.getExtras() : null;
            if (extras != null && extras.containsKey(AppSettings.ACCOUNT_FORCE_LOGOUT)) {
                extras.remove(AppSettings.ACCOUNT_FORCE_LOGOUT);
                isAuthenticated = false;
                showLogin();
            } else {
                isAuthenticated = hasAccount();
                if (!isAuthenticated)
                    showLogin();
            }
        }
    }

    /**
     * Set toolbar action bar
     *
     * @param toolbar support v7 Toolbar
     */
    public void setToolbar(@Nullable Toolbar toolbar) {
        if (toolbar == null)
            this.toolbar = (Toolbar) findViewById(getToolbarId());
        else
            this.toolbar = toolbar;
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayShowTitleEnabled(false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().setFlags(LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (hasToolbarPadding()) {
                    AppBarLayout appBar = (AppBarLayout) findViewById(getAppBarId());
                    if (appBar != null)
                        appBar.setPadding(0, DisplayUtil.getStatusBarHeight(this), 0, 0);
                }
            }
        }
    }

    /*
    *
    * */

    public void suggestions(String[] suggestions){

    }

    /**
     * Set title to actionBar
     *
     * @param title CharSequence to set on ActionBar
     */
    public void setTitle(CharSequence title) {
        mTitle = title;
        TextView appBarTitle = (TextView) findViewById(getToolbarTitleId());
        if (appBarTitle != null) { //&& appBarIcon != null) {
            if (title != null) {
                appBarTitle.setVisibility(View.VISIBLE);
                appBarTitle.setText(title);
            } else {
                appBarTitle.setVisibility(View.GONE);
            }
        } else if (getSupportActionBar() != null && mTitle != null)
            getSupportActionBar().setTitle(mTitle);
    }

    /**
     * Set drawable to ActionBar
     *
     * @param iconRes int resource drawable id to set on ActionBar
     */
    public void setActionBarIcon(int iconRes) {
        if (toolbar != null)
            toolbar.setNavigationIcon(iconRes);
    }

    /**
     * Set Visible logo in action
     *
     * @param enable boolean to set on ActionBar
     */
    public void setLogoToolbarEnable(boolean enable) {
        ImageView appBarIcon = (ImageView) findViewById(getImageId());
        if (appBarIcon != null) { //&& appBarIcon != null) {
            if (enable) {
                appBarIcon.setVisibility(View.VISIBLE);
            } else {
                appBarIcon.setVisibility(View.GONE);
            }
        }
    }

    /**
     * Set drawable to ActionBar
     *
     * @param bitmapDrawable BitmapDrawable to set on ActionBar
     */
    public void setActionBarIcon(BitmapDrawable bitmapDrawable) {
        if (toolbar != null)
            toolbar.setNavigationIcon(bitmapDrawable);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_AT_RUNTIME) {
            if (permissions != null && grantResults != null && permissions.length > 0
                    && grantResults.length > 0) {
                int position = 0;
                for (String permission : permissions) {
                    if (grantResults[position] == PackageManager.PERMISSION_GRANTED)
                        permissionStateCallBack.onPermissionGranted(permission);
                    else
                        permissionStateCallBack.onPermissionDeclined(permission);
                    position++;
                }
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        hideKeyword();
        super.onDestroy();
    }

    protected void checkPermission(String permissionToCheck) {
        if (permissionStateCallBack == null)
            return;
        if (ContextCompat.checkSelfPermission(this, permissionToCheck)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permissionToCheck)) {
                permissionStateCallBack.shouldRequestPermission(permissionToCheck);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{permissionToCheck},
                        REQUEST_PERMISSION_AT_RUNTIME);
            }
        }
    }

    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onPermissionDeclined(String permission) {
        //do nothing...
    }

    @Override
    public void onPermissionGranted(String permission) {
        // do nothing...
    }

    @Override
    public void shouldRequestPermission(String permission) {
        // do nothing...
    }


    /**
     * abstraction methods
     */
    /**
     * Get toolbar layout id
     *
     * @return R.id.toolbar id if your layout has declared one, otherwise 0
     */
    public abstract int getImageId();

    /**
     * abstraction methods
     */
    /**
     * Get toolbar layout id
     *
     * @return R.id.toolbar id if your layout has declared one, otherwise 0
     */
    protected abstract int getToolbarId();

    /**
     * abstraction methods
     */
    /**
     * Get toolbar layout id
     *
     * @return R.id.toolbar id if your layout has declared one, otherwise 0
     */
    protected abstract int getToolbarTitleId();

    /**
     * Get layout resource to attach to this activity class
     *
     * @return R.layout.activity_main -> represent the layout view to use inside setContentView method
     */
    protected abstract int getLayoutResourceId();

    /**
     * it is used for activity custom toolbar padding in windows status bar translucent feature
     *
     * @return true if you want to setup padding automatically, otherwise false
     */
    protected boolean hasToolbarPadding() {
        return true;
    }

    protected abstract int getAppBarId();

    protected abstract boolean hasAccount();

    protected boolean ignoreAuth() {
        return false;
    }

    public void showLogin() {
    }

}

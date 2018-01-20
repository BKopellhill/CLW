package com.bitcodeing.framework.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bitcodeing.framework.activities.BaseActivity;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Common Base Fragment
 *
 * @author Luis Hernández
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment {

    private final int MAX_THREAD_ALLOWED = 10;
    protected View rootView;

    protected ExecutorService _executor;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (_executor == null)
            _executor = Executors.newFixedThreadPool(MAX_THREAD_ALLOWED);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutResource(), container, false);
        onCreateView(savedInstanceState);
        return rootView;
    }

    /**
     * Find any view inside of this fragment view
     *
     * @param id int resource id to find
     * @return View instance object
     */
    protected View findViewById(int id) {
        return rootView != null ? rootView.findViewById(id) : null;
    }

    /**
     * Implement this method to be able to initialize this fragment
     * Use findViewById(R.id.view_id) to find any view attached to this fragment
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void onCreateView(Bundle savedInstanceState);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releaseExecutor();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseExecutor();
    }

    /**
     * Set ActionBar title if the activity is an instance of AppCompactActivity
     *
     * @param title CharSequence title to setup
     */
    public void setTitle(CharSequence title) {
        Activity parent = getActivity();
        if (parent != null && parent instanceof BaseActivity) {
            ((BaseActivity) parent).setTitle(title);
        }
    }

    /**
     * Set ActionBar icon if the activity is an instance of AppCompactActivity
     */
    public void setIcon(int drawableId) {
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            BaseActivity parent = (BaseActivity) activity;
            parent.setActionBarIcon(drawableId);
        }
    }

    public void dispatchKeyBack() {
        if (getActivity() == null)
            return;
        getActivity().dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
        getParent().hideKeyword();
    }

    /**
     * Send local broadcast message
     *
     * @param broadcast Intent Broadcast message
     * @return true if the broadcast message was sent successfully, otherwise false
     */
    public boolean sendBroadcast(Intent broadcast) {
        return LocalBroadcastManager.getInstance(getContext())
                .sendBroadcast(broadcast);
    }

    /**
     * Get Thread Pool Executor to allocate background tasks
     *
     * @return ExecutorService Pool
     */
    public ExecutorService getExecutor() {
        if (_executor == null)
            _executor = Executors.newFixedThreadPool(MAX_THREAD_ALLOWED);
        return _executor;
    }

    /**
     * Release thread pool executor
     */
    public void releaseExecutor() {
        if (_executor == null)
            return;
        _executor.shutdownNow();
        _executor = null;
    }
    public BaseActivity getParent() {
        if (getActivity() != null && getActivity() instanceof BaseActivity)
            return (BaseActivity) getActivity();

        if (getActivity() != null)
            return (BaseActivity) getActivity();
        return null;
    }

    public boolean onBackPressed() {
        return false;
    }

    /**
     * Get layout resource id Example <b>R.layout.fragment_main</b>
     *
     * @return Int Layout resource id
     */
    protected abstract int getLayoutResource();

    public void onQueryResult(String query,boolean submit){}
}

package com.bitcodeing.framework.callbacks;

/**
 * Handle the result of check self permission at runtime
 *
 * @author Luis Hernández
 * @version 1.0
 */
public interface PermissionStateCallBack {

    void onPermissionGranted(String permission);

    void onPermissionDeclined(String permission);

    void shouldRequestPermission(String permission);
}

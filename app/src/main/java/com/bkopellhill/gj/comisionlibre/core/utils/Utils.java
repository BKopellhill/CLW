package com.bkopellhill.gj.comisionlibre.core.utils;

/**
 * @author Luis Hernandez
 * @version 0.0.1
 */

public final class Utils {

    public static int compareVersionNames(String oldVersionName, String newVersionName) {
        int res = 0;

        String[] oldNumbers = oldVersionName.split("\\.");
        String[] newNumbers = newVersionName.split("\\.");

        // To avoid IndexOutOfBounds
        int maxIndex = Math.min(oldNumbers.length, newNumbers.length);

        for (int i = 0; i < maxIndex; i ++) {
            int oldVersionPart = Integer.valueOf(oldNumbers[i]);
            int newVersionPart = Integer.valueOf(newNumbers[i]);

            if (oldVersionPart < newVersionPart) {
                res = -1;
                break;
            } else if (oldVersionPart > newVersionPart) {
                res = 1;
                break;
            }
        }

        if (res == 0 && oldNumbers.length != newNumbers.length) {
            res = (oldNumbers.length > newNumbers.length)?1:-1;
        }
        return res;
    }
}

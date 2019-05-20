package com.uniyaz.mobiltif.utils;

/**
 * Created by İrfan Öngüç on 19.05.2019
 */

public class TextCustomUtils {
    public static boolean isFullEmpty(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            String trimStr = str.toString().trim();
            if (trimStr == null || trimStr.length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDigitsOnly(CharSequence str) {
        if (str == null || str.toString() == null || "".equals(str.toString().trim())) {
            return false;
        }

        final int len = str.length();
        for (int cp, i = 0; i < len; i += Character.charCount(cp)) {
            cp = Character.codePointAt(str, i);
            if (!Character.isDigit(cp)) {
                return false;
            }
        }
        return true;
    }
}

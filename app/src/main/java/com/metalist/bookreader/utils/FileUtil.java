package com.metalist.bookreader.utils;

import android.net.Uri;
import android.text.TextUtils;

public class FileUtil {
    public static String getFileExtension(String urlStr) {
        if (TextUtils.isEmpty(urlStr) || urlStr.length() < 4) return "";
        Uri url = Uri.parse(urlStr);
        String fileNameWithParams = url.getLastPathSegment();
        if (fileNameWithParams != null) {
            String fileName = fileNameWithParams.split("\\?")[0]; // get fileName
            if (fileName.contains(".")) {
                return fileName.substring(fileName.lastIndexOf(".") + 1);// get extension
            }
        }
        return "";
    }

    public static boolean isPdf(String url) {
        String suffix = getFileExtension(url);
        return suffix.equalsIgnoreCase("pdf");
    }
}

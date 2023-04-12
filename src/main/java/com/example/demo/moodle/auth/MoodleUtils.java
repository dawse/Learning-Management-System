package com.example.demo.moodle.auth;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class MoodleUtils {
	public static String getURLParamsFromMap(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return sb.toString();
    }

}

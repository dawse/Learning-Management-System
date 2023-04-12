package com.example.demo.moodle.course;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/*
 * URL
 * http://localhost/moodle/webservice/rest/server.php?wstoken=1289dc206f390bbc774de8a1853e2666&wsfunction=core_course_create_courses&moodlewsrestformat=json
 */
public class MoodleServiceCreateCourse {

    private static final String MOODLE_API_TOKEN = "1289dc206f390bbc774de8a1853e2666";
    private static final String MOODLE_DOMAIN_NAME = "http://localhost/moodle/";
    private static final String MOODLE_WS_FUNCTION_NAME = "core_course_create_courses";
    private static final String MOODLE_WS_FORMAT = "json";

    public void createCourse(String fullname, String shortname, String categoryid , String visible , String idnumber) throws IOException {
        // Create REST URL
        String restUrl = MOODLE_DOMAIN_NAME + "/webservice/rest/server.php" +
            "?wstoken=" + MOODLE_API_TOKEN +
            "&wsfunction=" + MOODLE_WS_FUNCTION_NAME +
            "&moodlewsrestformat=" + MOODLE_WS_FORMAT;

        // Create parameters
        String urlParameters =
            "courses[0][fullname]=" + URLEncoder.encode(fullname, "UTF-8") +
            "&courses[0][shortname]=" + URLEncoder.encode(shortname, "UTF-8") +
            "&courses[0][categoryid]=" + URLEncoder.encode(categoryid, "UTF-8")+
            "&courses[0][visible]=" + URLEncoder.encode(visible, "UTF-8")+  //1: available to student, 0:not available
            "&courses[0][idnumber]=" + URLEncoder.encode(categoryid, "UTF-8");

        // Send REST request
        HttpURLConnection connection = (HttpURLConnection) new URL(restUrl).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.writeBytes(urlParameters);
            wr.flush();
        }

        // Read REST response
        try (InputStream is = connection.getInputStream();
             BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }

            System.out.println(response.toString());
        }
    }
}

package com.example.demo.moodle;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;
/* URL
 * http://localhost/moodle/webservice/rest/server.php?wstoken=4c7dafc0ee45e5924125fc295fd737a7&wsfunction=core_user_create_users&moodlewsrestformat=json
 */
@Service
public class MoodleService  {

    private static final String MOODLE_API_TOKEN = "4c7dafc0ee45e5924125fc295fd737a7";
    private static final String MOODLE_DOMAIN_NAME = "http://localhost/moodle/";
    private static final String MOODLE_WS_FUNCTION_NAME = "core_user_create_users";
    private static final String MOODLE_WS_FORMAT = "json";

    public void createUser(String username, String password, String firstname, String lastname,
            String email, String timezone, String city, String country) throws IOException {

        // Create REST URL
        String restUrl = MOODLE_DOMAIN_NAME + "/webservice/rest/server.php" +
            "?wstoken=" + MOODLE_API_TOKEN +
            "&wsfunction=" + MOODLE_WS_FUNCTION_NAME +
            "&moodlewsrestformat=" + MOODLE_WS_FORMAT;

        // Create parameters
        String urlParameters =
            "users[0][username]=" + URLEncoder.encode(username, "UTF-8") +
            "&users[0][password]=" + URLEncoder.encode(password, "UTF-8") +
            "&users[0][firstname]=" + URLEncoder.encode(firstname, "UTF-8") +
            "&users[0][lastname]=" + URLEncoder.encode(lastname, "UTF-8") +
            "&users[0][email]=" + URLEncoder.encode(email, "UTF-8") +
            "&users[0][auth]=" + URLEncoder.encode("manual", "UTF-8") +
            "&users[0][timezone]=" + URLEncoder.encode(timezone, "UTF-8") +
            "&users[0][city]=" + URLEncoder.encode(city, "UTF-8") +
            "&users[0][country]=" + URLEncoder.encode(country, "UTF-8");

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

package com.example.demo.moodle.auth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*
 * URL
 * http://localhost/moodle/webservice/rest/server.php?wstoken=715e4ac6c1b29c5e8cc4caac029f8caf&wsfunction=auth_userkey_request_login_url&moodlewsrestformat=json
 * user[email] use a valid one 
 */
public class MoodleAuthLoginUser {

	  public String getOneTimeKeyBasedLoginURL(String token, String domainName, String restformat, String functionName, String username, String firstname, String lastname, String email) throws IOException {
	        // REST RETURNED VALUES FORMAT
	        if (restformat.equals("json")) {
	            restformat = "&moodlewsrestformat=" + restformat;
	        } else {
	            restformat = "";
	        }

	        // REST CALL
	        String serverurl = domainName + "/webservice/rest/server.php" + "?wstoken=" + token + "&wsfunction=" + functionName;
	        HttpURLConnection con = (HttpURLConnection) new URL(serverurl).openConnection();
	        con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        con.setRequestProperty("Content-Language", "en-US");
	        con.setDoOutput(true);
	        con.setUseCaches(false);
	        con.setDoInput(true);

	        // Set the request parameters
	        Map<String, String> arguments = new HashMap<>();
	        arguments.put("user[username]", username);
	        arguments.put("user[firstname]", firstname);
	        arguments.put("user[lastname]", lastname);
	        arguments.put("user[email]", email);

	        String urlParameters = MoodleUtils.getURLParamsFromMap(arguments);

	        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	        wr.writeBytes(urlParameters);
	        wr.flush();
	        wr.close();

	        // Get Response
	        InputStream is =con.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String line;
	        StringBuilder response = new StringBuilder();
	        while((line = rd.readLine()) != null) {
	            response.append(line);
	            response.append('\r');
	        }
	        rd.close();

	        return response.toString();
	    }
}
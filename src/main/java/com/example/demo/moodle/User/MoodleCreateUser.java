package com.example.demo.moodle.User;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




/* URL
 * http://localhost/moodle/webservice/rest/server.php?wstoken=1289dc206f390bbc774de8a1853e2666&wsfunction=core_user_create_users&moodlewsrestformat=json
 */
public class MoodleCreateUser {
	 public String createUser(String token, String domainName, String restformat, String functionName, String urlParameters) throws IOException {
		 	token = "1289dc206f390bbc774de8a1853e2666";
	        domainName = "http://localhost/moodle/"; 
	        functionName = "core_user_create_users";
		 
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
		    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.flush();
		    wr.close();

		    //Get Response
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
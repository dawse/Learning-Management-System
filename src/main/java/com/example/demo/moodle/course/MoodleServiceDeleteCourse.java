package com.example.demo.moodle.course;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/* URL 
 * http://localhost/moodle/webservice/rest/server.php?wstoken=4c7dafc0ee45e5924125fc295fd737a7&wsfunction=core_course_delete_courses&moodlewsrestformat=json
*/ 

public class MoodleServiceDeleteCourse {
	 private static final String MOODLE_API_TOKEN = "4c7dafc0ee45e5924125fc295fd737a7";
	    private static final String MOODLE_DOMAIN_NAME = "http://localhost/moodle/";
	    private static final String MOODLE_WS_FORMAT = "json";
		
		public void deleteCourse(int courseId) throws IOException {

		    // Create REST URL
		    String restUrl = MOODLE_DOMAIN_NAME + "/webservice/rest/server.php" +
		        "?wstoken=" + MOODLE_API_TOKEN +
		        "&wsfunction=core_course_delete_courses " +
		        "&moodlewsrestformat=" + MOODLE_WS_FORMAT;

		    // Create parameters
		    String urlParameters = "courseids[0]=" + courseId;

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

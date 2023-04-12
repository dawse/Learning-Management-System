package com.example.demo.moodle.course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/* Not Tested yet !!! */
public class MoodleServiceGetCourses {
	 public String getUsers(String token, String domainName, String restformat, List<CourseSearchCriteria> searchCriteriaList) throws IOException {

	    	// REST RETURNED VALUES FORMAT
	        if (restformat.equals("json")) {
	            restformat = "&moodlewsrestformat=" + restformat;
	        } else {
	            restformat = "";
	        }
	        
	        // Build search criteria parameters
	        StringBuilder searchParamsBuilder = new StringBuilder();
	        for (CourseSearchCriteria criteria : searchCriteriaList) {
	            searchParamsBuilder.append("&criteria[").append(criteria.getKey()).append("]=").append(criteria.getValue());
	        }
	        String searchParams = searchParamsBuilder.toString();
	        
	        // REST CALL
	        String serverurl = domainName + "/webservice/rest/server.php" + "?wstoken=" + token +
	                           "&wsfunction=core_course_get_courses " + searchParams + restformat;
	        HttpURLConnection con = (HttpURLConnection) new URL(serverurl).openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        con.setRequestProperty("Content-Language", "en-US");
	        con.setUseCaches(false);
	        con.setDoInput(true);
	        
	        // Get Response
	        InputStream is = con.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String line;
	        StringBuilder response = new StringBuilder();
	        while ((line = rd.readLine()) != null) {
	            response.append(line);
	            response.append('\r');
	        }
	        rd.close();
	        
	        return response.toString();
	    }
	    
	    public static class CourseSearchCriteria {
	        private String key;
	        private String value;
	        
	        public CourseSearchCriteria(String key, String value) {
	            this.key = key;
	            this.value = value;
	        }
	        
	        public String getKey() {
	            return key;
	        }
	        
	        public String getValue() {
	            return value;
	        }
	    }

}

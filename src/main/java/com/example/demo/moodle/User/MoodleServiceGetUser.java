package com.example.demo.moodle.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//URL
//http://localhost/moodle/webservice/rest/server.php?wstoken=4c7dafc0ee45e5924125fc295fd737a7&wsfunction=core_user_get_users&criteria[0][key]=username&criteria[0][value]=dawssentest&moodlewsrestformat=json


public class MoodleServiceGetUser {

    
    public String getUsers(String token, String domainName, String restformat, List<UserSearchCriteria> searchCriteriaList) throws IOException {

    	// REST RETURNED VALUES FORMAT
        if (restformat.equals("json")) {
            restformat = "&moodlewsrestformat=" + restformat;
        } else {
            restformat = "";
        }
        
        // Build search criteria parameters
        StringBuilder searchParamsBuilder = new StringBuilder();
        for (UserSearchCriteria criteria : searchCriteriaList) {
            searchParamsBuilder.append("&criteria[").append(criteria.getKey()).append("]=").append(criteria.getValue());
        }
        String searchParams = searchParamsBuilder.toString();
        
        // REST CALL
        String serverurl = domainName + "/webservice/rest/server.php" + "?wstoken=" + token +
                           "&wsfunction=core_user_get_users" + searchParams + restformat;
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
    
    public static class UserSearchCriteria {
        private String key;
        private String value;
        
        public UserSearchCriteria(String key, String value) {
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

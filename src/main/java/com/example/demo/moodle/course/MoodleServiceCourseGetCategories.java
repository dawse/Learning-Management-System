package com.example.demo.moodle.course;

import org.springframework.web.client.RestTemplate;

public class MoodleServiceCourseGetCategories {
    private static final String MOODLE_TOKEN = "4c7dafc0ee45e5924125fc295fd737a7";
    private static final String MOODLE_API_URL = "http://localhost/moodle/";

	
	   public String getCategories(String key, String value) {
	        RestTemplate restTemplate = new RestTemplate();
	        String endpoint = MOODLE_API_URL + "/webservice/rest/server.php" + "?wstoken=" + MOODLE_TOKEN + "&wsfunction=core_course_get_categories&moodlewsrestformat=json";
	        String requestBody = "{\"criteria\":[{\"key\":\"" + key + "\",\"value\":\"" + value + "\"}]}";
	        String response = restTemplate.postForObject(endpoint, requestBody, String.class);
	        return response;
	    }

}

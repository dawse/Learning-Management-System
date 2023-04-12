package com.example.demo.moodle.course;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
/// Not Tested Yet 

public class AddContentItemToUserFavourites {
    private static final String MOODLE_TOKEN = "4c7dafc0ee45e5924125fc295fd737a7";
    private static final String MOODLE_API_URL = "http://localhost/moodle/";

    @SuppressWarnings("unchecked")
	public Map<String, Object> addToUserFavourites(String componentName, int contentItemId) {
        String url = MOODLE_API_URL + "/webservice/rest/server.php" +
                "?wstoken=" + MOODLE_TOKEN +
                "&wsfunction=core_course_add_content_item_to_user_favourites" +
                "&moodlewsrestformat=json";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("componentname", componentName);
        requestBody.put("contentitemid", String.valueOf(contentItemId));

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        @SuppressWarnings("rawtypes")
		ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody();
    }
}

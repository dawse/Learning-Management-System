package com.example.demo.moodle.course;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
/* URL 
 * http://localhost/moodle/webservice/rest/server.php?wstoken=4c7dafc0ee45e5924125fc295fd737a7&wsfunction=core_course_get_contents&moodlewsrestformat=json */
public class MoodleServiceGetCourseContents {
    private static final String MOODLE_TOKEN = "4c7dafc0ee45e5924125fc295fd737a7";
    private static final String MOODLE_API_URL = "http://localhost/moodle/";

    public String getContents(int courseId) {
        String function = "core_course_get_contents";
        String url = MOODLE_API_URL + "/webservice/rest/server.php" + "?wstoken=" + MOODLE_TOKEN + "&wsfunction=" + function + "&moodlewsrestformat=json";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("courseid", String.valueOf(courseId));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String result = restTemplate.exchange(url, HttpMethod.POST, request, String.class).getBody();
        return result;
    }
}

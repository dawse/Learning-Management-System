package com.example.demo.moodle.course;

import org.springframework.web.client.RestTemplate;
/* URL
 * http://localhost/moodle/webservice/rest/server.php?wstoken=4c7dafc0ee45e5924125fc295fd737a7&wsfunction=core_course_get_recent_courses&moodlewsrestformat=json
 * POST
 * userid */

public class MoodleServiceGetRecentCourse {
    private static final String MOODLE_TOKEN = "4c7dafc0ee45e5924125fc295fd737a7";
    private static final String MOODLE_API_URL = "http://localhost/moodle/";

    public String getRecentCourses(int userId, int limit, int offset, String sort) {
        String url = MOODLE_API_URL + "/webservice/rest/server.php" + "?wstoken=" + MOODLE_TOKEN
                + "&wsfunction=core_course_get_recent_courses&moodlewsrestformat=json";
        if (userId != 0) {
            url += "&userid=" + userId;
        }
        if (limit != 0) {
            url += "&limit=" + limit;
        }
        if (offset != 0) {
            url += "&offset=" + offset;
        }
        if (sort != null) {
            url += "&sort=" + sort;
        }
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}

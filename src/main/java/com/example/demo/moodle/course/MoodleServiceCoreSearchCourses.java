package com.example.demo.moodle.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
/* URL
 * http://localhost/moodle/webservice/rest/server.php?wstoken=4c7dafc0ee45e5924125fc295fd737a7&wsfunction=core_course_search_courses
 * POST
 * criterianame : search
 * criteriavalue*/

public class MoodleServiceCoreSearchCourses {
	// criteria name  (search, modulelist (only admins), blocklist (only admins), tagid)
	 @Autowired
	 private RestTemplate restTemplate;
	 private static final String MOODLE_URL = "http://localhost/moodle/webservice/rest/server.php";
	 private static final String MOODLE_TOKEN = "4c7dafc0ee45e5924125fc295fd737a7";
	 public String searchCourses(String criteriaName, String criteriaValue, int page, int perPage, String[] requiredCapabilities, int limitToEnrolled, int onlyWithCompletion) {
	        Map<String, Object> params = new HashMap<>();
	        params.put("wstoken", MOODLE_TOKEN);
	        params.put("wsfunction", "core_course_search_courses");
	        params.put("criterianame", criteriaName);
	        params.put("criteriavalue", criteriaValue);
	        params.put("page", page);
	        params.put("perpage", perPage);
	        params.put("requiredcapabilities[]", requiredCapabilities);
	        params.put("limittoenrolled", limitToEnrolled);
	        params.put("onlywithcompletion", onlyWithCompletion);

	        String result = restTemplate.postForObject(MOODLE_URL, params, String.class);
	        return result;
	    }

}

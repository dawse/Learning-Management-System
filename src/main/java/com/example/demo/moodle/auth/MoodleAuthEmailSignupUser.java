package com.example.demo.moodle.auth;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
/*
 * URL
 * http://localhost/moodle/webservice/rest/server.php?wstoken=4c7dafc0ee45e5924125fc295fd737a7&wsfunction=auth_email_signup_user&moodlewsrestformat=json
 * POST
 * Use a valid mail
 * 1289dc206f390bbc774de8a1853e2666
 */

public class MoodleAuthEmailSignupUser {

    private final String API_URL = "http://localhost/moodle/webservice/rest/server.php";
    private final String API_TOKEN = "1289dc206f390bbc774de8a1853e2666";

    public void createUser(String username, String password, String firstname, String lastname, String email, String city, String country, String recaptchachallengehash, String recaptcharesponse, Map<String, String> customprofilefields, String redirect) {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(API_URL);
        
        // set the headers and parameters
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Authorization", "Bearer " + API_TOKEN);
        
        String body = "username=" + username + "&password=" + password + "&firstname=" + firstname + "&lastname=" + lastname + "&email=" + email + "&city=" + city + "&country=" + country /*+ "&recaptchachallengehash=" + recaptchachallengehash + "&recaptcharesponse=" + recaptcharesponse + "&redirect=" + redirect*/;
        
        // add the custom profile fields to the body
        for (Map.Entry<String, String> entry : customprofilefields.entrySet()) {
            body += "&customprofilefields[" + entry.getKey() + "]=" + entry.getValue();
        }
        
        post.setEntity(new StringEntity(body, ContentType.DEFAULT_TEXT));

        try {
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println(responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

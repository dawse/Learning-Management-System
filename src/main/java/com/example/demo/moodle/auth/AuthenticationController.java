package com.example.demo.moodle.auth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/auth")

public class AuthenticationController {
	/*	@Value("${external.system.url}")// add it 
	    private String externalSystemUrl=""; // the URL of the external system's authentication endpoint
	    @Value("${external.system.username}")
	    private String externalSystemUsername=""; // the username to use for authentication with the external system
	    @Value("${external.system.password}")
	    private String externalSystemPassword; // the password to use for authentication with the external system

	    @PostMapping("/login")
	    @ResponseBody
	    public ResponseEntity<String> login(@RequestBody AuthenticationRequest userCredentials) {

	        // authenticate the user against the external system via APIGEE
	        RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBasicAuth(externalSystemUsername, externalSystemPassword);
	        HttpEntity<AuthenticationRequest> requestEntity = new HttpEntity<>(userCredentials, headers);
	        ResponseEntity<String> responseEntity = restTemplate.exchange(
	                externalSystemUrl,
	                HttpMethod.POST,
	                requestEntity,
	                String.class);

	        if (responseEntity.getStatusCode().is2xxSuccessful()) {
	            // authentication successful, return an access token
	            String accessToken = "some_random_access_token";
	            return ResponseEntity.ok(accessToken);
	        } else {
	            // authentication failed, return an error message
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	        }
	    }*/
}

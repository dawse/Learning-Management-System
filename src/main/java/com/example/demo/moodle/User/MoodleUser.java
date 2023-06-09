package com.example.demo.moodle.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoodleUser {
	  @JsonProperty("id")
	    private int id;
	    
	    @JsonProperty("username")
	    private String username;
	    
	    @JsonProperty("firstname")
	    private String firstName;
	    
	    @JsonProperty("lastname")
	    private String lastName;
	    
	    public int getId() {
	        return id;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public String getUsername() {
	        return username;
	    }
	    
	    public void setUsername(String username) {
	        this.username = username;
	    }
	    
	    public String getFirstName() {
	        return firstName;
	    }
	    
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

}

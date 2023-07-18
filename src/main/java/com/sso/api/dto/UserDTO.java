package com.sso.api.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	 private Integer id;

	    private String username;
	    
	    private String password;
	    
	    private String email;
	    
	    private boolean enabled;
	    
	    private boolean accountNonExpired;
	    
	    private boolean credentialsNonExpired;
	    
	    private boolean accountNonLocked;
	    
	    private String roles;


}

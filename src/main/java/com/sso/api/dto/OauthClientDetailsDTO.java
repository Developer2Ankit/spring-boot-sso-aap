package com.sso.api.dto;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OauthClientDetailsDTO {
	
    private String clientId;
    
    private String resourceIds;
    
    private String clientSecret;
    
    private String scope;
    
    private String authorizedGrantTypes;
    
    private String webServerRedirectUri;
    
    private String authorities;
    
    private int accessTokenValidity;
    
    private int refreshTokenValidity;
    
    private String additionalInformation;
    
    private String autoapprove;
    
    private String title;
   

    
}

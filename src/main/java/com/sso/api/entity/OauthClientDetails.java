package com.sso.api.entity;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
*
* @author Ankit Yadav
* @since 03 06 20
*/

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "oauth_client_details")

public class OauthClientDetails implements Serializable {
   
	private static final long serialVersionUID = 7657451394244852266L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "resource_ids")
    private String resourceIds;
    
    @Column(name = "client_secret")
    private String clientSecret;
    
    @Column(name = "scope")
    private String scope;
    
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;
    
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;
    
    @Column(name = "authorities")
    private String authorities;
    
    @Column(name = "access_token_validity")
    private int accessTokenValidity;
    
    @Column(name = "refresh_token_validity")
    private int refreshTokenValidity;
    
    @Column(name = "additional_information")
    private String additionalInformation;
    
    @Column(name = "autoapprove")
    private String autoapprove;
    
    @Column(name = "app_authorization_code")
    private String appAuthorizationCode;
    
    @Column(name = "title")
    private String title;
   

    
    
    
}

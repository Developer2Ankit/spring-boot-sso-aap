package com.sso.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sso.api.entity.EmployeeEntity;
import com.sso.api.entity.OauthClientDetails;

/**
 *
 * @author Ankit Yadav
 * @since 03 06 20
 */

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {

	public OauthClientDetails findByClientId(String clientId);

}

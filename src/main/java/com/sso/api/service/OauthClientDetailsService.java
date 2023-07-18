package com.sso.api.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sso.api.common.exceptions.CustomDataIntegrityViolationException;
import com.sso.api.common.exceptions.RecordNotFoundException;
import com.sso.api.common.messages.BaseResponse;
import com.sso.api.common.messages.CustomMessage;
import com.sso.api.common.utils.Topic;
import com.sso.api.common.utils.UserTopic;
import com.sso.api.dto.OauthClientDetailsDTO;
import com.sso.api.entity.OauthClientDetails;
import com.sso.api.repo.OauthClientDetailsRepository;
import com.sso.api.repo.UserDetailRepository;

/**
*
* @author Ankit Yadav
* @since 03 06 20
*/

@Service
@Transactional
public class OauthClientDetailsService {

	//@Autowired
	//JdbcClientDetailsService jdbcClientDetailsService;
	
	@Autowired
	private OauthClientDetailsRepository oauthClientDetailsRepo;

	@Autowired
    private PasswordEncoder bcryptEncoder;
	
	public List<OauthClientDetailsDTO> findOauthClientDetailsList() {
		return oauthClientDetailsRepo.findAll().stream().map(this::copyUserEntityToDto).collect(Collectors.toList());
	}

	public OauthClientDetailsDTO findByClientId(String clientId) {
		if (oauthClientDetailsRepo.existsById(clientId)) {
			Optional<OauthClientDetails> oauthClientDetails = oauthClientDetailsRepo.findById(clientId);
			if(oauthClientDetails.isPresent()) {
				return copyUserEntityToDto(oauthClientDetails.get());	
			}else {
				throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + clientId);
			}
			
	
		}else {
			throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + clientId);
		}
		
	}

	public BaseResponse createOrUpdateOauthClientDetails(OauthClientDetailsDTO oauthClientDetailsDTO) {
		try {
			OauthClientDetails oauthClientDetails = copyUserDtoToEntity(oauthClientDetailsDTO);
			
			long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			oauthClientDetails.setClientSecret(number+"");
			oauthClientDetails.setClientSecret(bcryptEncoder.encode(oauthClientDetails.getClientSecret()));
			oauthClientDetails.setAccessTokenValidity(86400);
			oauthClientDetails.setRefreshTokenValidity(86400);
			oauthClientDetails.setAdditionalInformation("{}");
			oauthClientDetails.setScope("READ,WRITE");
			oauthClientDetails.setAuthorizedGrantTypes("authorization_code,password,refresh_token,implicit");
			oauthClientDetails.setResourceIds("microservice");
			oauthClientDetails=oauthClientDetailsRepo.save(oauthClientDetails);
			String originalInput = oauthClientDetails.getClientId()+":"+number;
			String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
			String appautorizationCode="Basic "+encodedString;
			oauthClientDetails.setAppAuthorizationCode(appautorizationCode);
			oauthClientDetails=oauthClientDetailsRepo.save(oauthClientDetails);
		}  catch (DataIntegrityViolationException ex) {
			throw new CustomDataIntegrityViolationException(ex.getCause().getCause().getMessage());
		}
		return new BaseResponse(UserTopic.USER.getName() + CustomMessage.SAVE_SUCCESS_MESSAGE);
	}

	public BaseResponse deleteOauthClientDetailsByClientId(String clientId) {
		if (oauthClientDetailsRepo.existsById(clientId)) {
			oauthClientDetailsRepo.deleteById(clientId);
		} else {
			throw new RecordNotFoundException(CustomMessage.NO_RECOURD_FOUND + clientId);
		}
		return new BaseResponse(UserTopic.USER.getName() + CustomMessage.DELETE_SUCCESS_MESSAGE);
	}

	private OauthClientDetailsDTO copyUserEntityToDto(OauthClientDetails oauthClientDetails) {
		OauthClientDetailsDTO oauthClientDetailsDTO = new OauthClientDetailsDTO();
		BeanUtils.copyProperties(oauthClientDetails, oauthClientDetailsDTO);
		return oauthClientDetailsDTO;
	}

	private OauthClientDetails copyUserDtoToEntity(OauthClientDetailsDTO oauthClientDetailsDTO) {
		OauthClientDetails oauthClientDetails = new OauthClientDetails();
		BeanUtils.copyProperties(oauthClientDetailsDTO, oauthClientDetails);
		return oauthClientDetails;
	}

}

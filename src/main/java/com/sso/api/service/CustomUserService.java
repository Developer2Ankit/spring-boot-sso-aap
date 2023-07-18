package com.sso.api.service;

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
import com.sso.api.dto.UserDTO;
import com.sso.api.entity.User;
import com.sso.api.repo.UserDetailRepository;

/**
*
* @author Ankit Yadav
* @since 03 06 20
*/

@Service
@Transactional
public class CustomUserService {

	//@Autowired
	//JdbcClientDetailsService jdbcClientDetailsService;
	
	@Autowired
	private UserDetailRepository userRepo;

	@Autowired
    private PasswordEncoder bcryptEncoder;
	
	public List<UserDTO> findUserList() {
		return userRepo.findAll().stream().map(this::copyUserEntityToDto).collect(Collectors.toList());
	}

	public UserDTO findByUserId(Integer userId) {
		if (userRepo.existsById(userId)) {
			Optional<User> user = userRepo.findById(userId);
			if(user.isPresent()) {
				return copyUserEntityToDto(user.get());	
			}else {
				throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + userId);
			}
			
	
		}else {
			throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + userId);
		}
		
	}

	public BaseResponse createOrUpdateUser(UserDTO userDTO) {
		try {
			createOrUpdateClient(userDTO);
			User user = copyUserDtoToEntity(userDTO);
			user.setPassword(bcryptEncoder.encode(user.getPassword()));
			userRepo.save(user);
		}  catch (DataIntegrityViolationException ex) {
			throw new CustomDataIntegrityViolationException(ex.getCause().getCause().getMessage());
		}
		return new BaseResponse(UserTopic.USER.getName() + CustomMessage.SAVE_SUCCESS_MESSAGE);
	}

	public BaseResponse deleteUserById(Integer userId) {
		if (userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
		} else {
			throw new RecordNotFoundException(CustomMessage.NO_RECOURD_FOUND + userId);
		}
		return new BaseResponse(UserTopic.USER.getName() + CustomMessage.DELETE_SUCCESS_MESSAGE);
	}

	private UserDTO copyUserEntityToDto(User user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}

	private User copyUserDtoToEntity(UserDTO useDto) {
		User user = new User();
		BeanUtils.copyProperties(useDto, user);
		return user;
	}
	public BaseResponse createOrUpdateClient(UserDTO userDTO) {
		try {
			String companyId="apar123";
			String resourceIds="microservice";
			String scopes="READ,WRITE";
			String grantTypes="authorization_code,password,refresh_token,implicit";
			String authorities="";
			
			BaseClientDetails clientDetails = new BaseClientDetails(companyId, resourceIds, scopes, grantTypes, authorities);
			clientDetails.setClientSecret("mpin");
		//	jdbcClientDetailsService.addClientDetails(clientDetails);
		}  catch (DataIntegrityViolationException ex) {
			throw new CustomDataIntegrityViolationException(ex.getCause().getCause().getMessage());
		}
		return new BaseResponse(UserTopic.USER.getName() + CustomMessage.SAVE_SUCCESS_MESSAGE);
	}
}

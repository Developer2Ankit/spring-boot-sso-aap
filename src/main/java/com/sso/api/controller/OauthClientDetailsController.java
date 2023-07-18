package com.sso.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sso.api.common.messages.BaseResponse;
import com.sso.api.dto.OauthClientDetailsDTO;
import com.sso.api.service.OauthClientDetailsService;

@Validated
@RestController
@RequestMapping("/client")
public class OauthClientDetailsController {

	@Autowired
	private OauthClientDetailsService oauthClientDetailsService;

	@GetMapping(value = "/find")
	public ResponseEntity<List<OauthClientDetailsDTO>> findOauthClientDetailsList() {
		List<OauthClientDetailsDTO> list = oauthClientDetailsService.findOauthClientDetailsList();
		return new ResponseEntity<List<OauthClientDetailsDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/findbyid/{clientId}")
	public ResponseEntity<OauthClientDetailsDTO> findByClientId(@PathVariable("clientId") String clientId) {
		OauthClientDetailsDTO list = oauthClientDetailsService.findByClientId(clientId);
		return new ResponseEntity<OauthClientDetailsDTO>(list, HttpStatus.OK);
	}

	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateOauthClientDetails(@Valid @RequestBody OauthClientDetailsDTO oauthClientDetailsDTO) {
		BaseResponse response = oauthClientDetailsService.createOrUpdateOauthClientDetails(oauthClientDetailsDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{clientId}")
	public ResponseEntity<BaseResponse> deleteOauthClientDetailsByClientId(@PathVariable("clientId") String clientId) {
		BaseResponse response = oauthClientDetailsService.deleteOauthClientDetailsByClientId(clientId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

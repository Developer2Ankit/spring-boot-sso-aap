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
import com.sso.api.dto.EmployeeDTO;
import com.sso.api.dto.UserDTO;
import com.sso.api.service.CustomUserService;
import com.sso.api.service.EmployeeService;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private CustomUserService customeservice;

	@GetMapping(value = "/find")
	public ResponseEntity<List<UserDTO>> getAllEmployees() {
		List<UserDTO> list = customeservice.findUserList();
		return new ResponseEntity<List<UserDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/find/by-id")
	public ResponseEntity<UserDTO> getUserById(@RequestParam Integer id) {
		UserDTO list = customeservice.findByUserId(id);
		return new ResponseEntity<UserDTO>(list, HttpStatus.OK);
	}

	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateUser(@Valid @RequestBody UserDTO userDTO) {
		BaseResponse response = customeservice.createOrUpdateUser(userDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<BaseResponse> deleteUserById(@PathVariable("id") Integer id) {
		BaseResponse response = customeservice.deleteUserById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

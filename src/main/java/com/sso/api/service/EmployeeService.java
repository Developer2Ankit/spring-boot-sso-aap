package com.sso.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sso.api.common.exceptions.CustomDataIntegrityViolationException;
import com.sso.api.common.exceptions.RecordNotFoundException;
import com.sso.api.common.messages.BaseResponse;
import com.sso.api.common.messages.CustomMessage;
import com.sso.api.common.utils.Topic;
import com.sso.api.dto.EmployeeDTO;
import com.sso.api.entity.EmployeeEntity;
import com.sso.api.repo.EmployeeRepo;

/**
*
* @author Ankit Yadav
* @since 03 06 20
*/

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public List<EmployeeDTO> findEmployeeList() {
		return employeeRepo.findAll().stream().map(this::copyEmployeeEntityToDto).collect(Collectors.toList());
	}

	public EmployeeDTO findByEmployeeId(Long employeeId) {
		if (employeeRepo.existsById(employeeId)) {
			EmployeeEntity employeeEntity = employeeRepo.findByEmployeeId(employeeId);
			return copyEmployeeEntityToDto(employeeEntity);
		}else {
			throw new RecordNotFoundException(CustomMessage.DOESNOT_EXIT + employeeId);
		}
		
	}

	public BaseResponse createOrUpdateEmployee(EmployeeDTO employeeDTO) {
		try {
			EmployeeEntity employeeEntity = copyEmployeeDtoToEntity(employeeDTO);
			employeeRepo.save(employeeEntity);
		}  catch (DataIntegrityViolationException ex) {
			throw new CustomDataIntegrityViolationException(ex.getCause().getCause().getMessage());
		}
		return new BaseResponse(Topic.EMPLOYEE.getName() + CustomMessage.SAVE_SUCCESS_MESSAGE);
	}

	public BaseResponse deleteEmployeeById(Long employeeId) {
		if (employeeRepo.existsById(employeeId)) {
			employeeRepo.deleteById(employeeId);
		} else {
			throw new RecordNotFoundException(CustomMessage.NO_RECOURD_FOUND + employeeId);
		}
		return new BaseResponse(Topic.EMPLOYEE.getName() + CustomMessage.DELETE_SUCCESS_MESSAGE);
	}

	private EmployeeDTO copyEmployeeEntityToDto(EmployeeEntity employeeEntity) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employeeEntity, employeeDTO);
		return employeeDTO;
	}

	private EmployeeEntity copyEmployeeDtoToEntity(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeeDTO, employeeEntity);
		return employeeEntity;
	}

}

package com.sso.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sso.api.entity.EmployeeEntity;

/**
 *
 * @author Ankit Yadav
 * @since 03 06 20
 */

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

	public EmployeeEntity findByEmployeeId(Long empId);

}

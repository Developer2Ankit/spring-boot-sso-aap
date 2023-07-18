package com.sso.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sso.api.entity.User;

/**
*
* @author Ankit Yadav
* @since 03 06 20
*/

public interface UserDetailRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String name);

	boolean existsByUsername(String name);

}

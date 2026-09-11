package com.paul.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paul.inventory_management.entity.AppUser;
import com.paul.inventory_management.enums.Status;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	boolean existsByEmailAndStatus(String email, Status status);
	
	boolean existsByEmailAndIdNot(String email, Long id);
	
	boolean existsByPhoneAndIdNot(String phone, Long id);
	
}

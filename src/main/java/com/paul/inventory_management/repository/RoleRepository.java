package com.paul.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paul.inventory_management.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	boolean existsByName(String name);
	
	boolean existsByNameAndIdNot(String name, Long id);
	
}

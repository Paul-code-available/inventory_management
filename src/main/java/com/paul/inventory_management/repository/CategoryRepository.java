package com.paul.inventory_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paul.inventory_management.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	boolean existsByNameIgnoreCase(String name);
	
	boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

}

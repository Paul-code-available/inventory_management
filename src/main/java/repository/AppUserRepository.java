package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	boolean existsByEmailAndStatusActive(String email);
	
	boolean existsByEmailAndIdNot(Long id, String email);
	
	boolean existByPhoneAndIdNot(Long id, String phone);
	
}

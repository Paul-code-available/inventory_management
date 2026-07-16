package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
}

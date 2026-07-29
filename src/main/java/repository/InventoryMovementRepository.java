package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.InventoryMovement;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

	List<InventoryMovement> findByProduct(Long productId);
	
	List<InventoryMovement> findByUser(Long UserId);
	
}

package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.InventoryMovement;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {

}

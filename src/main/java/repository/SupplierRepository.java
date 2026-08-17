package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	boolean existsByEmail(String email);
	
	boolean existByTaxId(String taxId);
	
	boolean existByTaxIdAndIdNot(String taxId, Long id);
	
	boolean existsByEmailAndIdNot(String email, Long id);
	
}

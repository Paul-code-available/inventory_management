package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.ProductSupplier;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {

}

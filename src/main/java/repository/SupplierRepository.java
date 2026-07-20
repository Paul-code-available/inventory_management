package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}

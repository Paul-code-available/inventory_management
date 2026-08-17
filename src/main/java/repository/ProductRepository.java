package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dto.ProductResponseDTO;
import entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	boolean existsBySku(String sku);
	
	Optional<Product> findBySku(String sku);
	
	boolean existsBySkuAndIdNot(String sku, Long id);

}

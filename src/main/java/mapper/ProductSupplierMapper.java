package mapper;

import org.springframework.stereotype.Component;

import dto.ProductResponseDTO;
import dto.ProductSupplierRequestDTO;
import dto.ProductSupplierResponseDTO;
import entity.Product;
import entity.ProductSupplier;
import entity.Supplier;

@Component
public class ProductSupplierMapper {
	
	ProductMapper productMapper;
	SupplierMapper supplierMapper;
	
	public ProductSupplierResponseDTO toDTO(ProductSupplier productSupplier) {
		
		return new ProductSupplierResponseDTO(
				productSupplier.getId(),
				productSupplier.getSupplierPrice(),
				productSupplier.getLeadTimeDays(),
				productMapper.toDTO(productSupplier.getProduct()),
				supplierMapper.toDTO(productSupplier.getSupplier())
				);
		
	}
	
	public ProductSupplier toEntity(ProductSupplierRequestDTO dto, Product product, Supplier supplier) {
		
		return ProductSupplier.builder()
				.supplierPrice(dto.supplierPrice())
				.leadTimeDays(dto.leadTimeDays())
				.product(product)
				.supplier(supplier)
				.build();
		
	}

}

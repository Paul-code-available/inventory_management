package mapper;

import org.springframework.stereotype.Component;

import dto.ProductResponseDTO;
import entity.Product;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMapper {
	
	private final CategoryMapper categoryMapper;
	
	public ProductResponseDTO toDTO(Product product) {
		
		return new ProductResponseDTO(
				product.getId(),
				product.getName(),
				product.getSalePrice(),
				product.getStock(),
				categoryMapper.toDTO(product.getCategory())
				);
		
	}

}

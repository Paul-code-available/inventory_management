package mapper;

import org.springframework.stereotype.Component;

import dto.ProductCreateDTO;
import dto.ProductResponseDTO;
import entity.Category;
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
	
	public Product toEntity(ProductCreateDTO dto, Category category) {
		
		return Product.builder()
				.sku(dto.sku())
				.name(dto.name())
				.brand(dto.brand())
				.description(dto.description())
				.purchasePrice(dto.purchasePrice())
				.salePrice(dto.salePrice())
				.stock(dto.stock())
				.minimumStock(dto.minimumStock())
				.imageUrl(dto.imgUrl())
				.category(category)
				.build();
		
	}

}

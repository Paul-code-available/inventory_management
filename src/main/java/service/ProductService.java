
package service;

import org.springframework.stereotype.Service;

import dto.ProductCreateDTO;
import dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import mapper.ProductMapper;
import repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	public ProductResponseDTO create(ProductCreateDTO dto) {
		
		
		
	}
	
}

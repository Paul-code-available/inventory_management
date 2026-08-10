package service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mapper.ProductSupplierMapper;
import repository.ProductSupplierRepository;

@Service
@RequiredArgsConstructor
public class ProductSupplierService {

	private final ProductSupplierRepository productSupplierRepository;
	private final ProductSupplierMapper productSupplierMapper;
	
	
	
	
}



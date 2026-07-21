package mapper;

import org.springframework.stereotype.Component;

import dto.SupplierRequestDTO;
import dto.SupplierResponseDTO;
import entity.Supplier;

@Component
public class SupplierMapper {
	
	public SupplierResponseDTO toDTO(Supplier supplier) {
		
		return new SupplierResponseDTO(
				supplier.getId(),
				supplier.getCompanyName(),
				supplier.getContactName(),
				supplier.getEmail(),
				supplier.getTaxId()
				);
		
	}
	
	public Supplier toEntity(SupplierRequestDTO dto) {
		
		return Supplier.builder()
				.companyName(dto.companyName())
				.contactName(dto.contactName())
				.email(dto.email())
				.phone(dto.phone())
				.taxId(dto.taxId())
				.address(dto.address())
				.build();
		
	}

}

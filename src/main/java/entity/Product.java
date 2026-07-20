package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import enums.Status;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sku;
	private String name;
	private String brand;
	private String description;
	
	private BigDecimal purchasePrice;
	private BigDecimal salePrice;
	
	private Integer stock;
	private int minimumStock;
	
	private String image_url;
	
	@Enumerated(EnumType.STRING)
	protected Status status;
	
	@Column(name = "created_at")
	protected LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<ProductSupplier> productSupplier;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
}

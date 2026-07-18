package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import enums.MovementType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class InventoryMovement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "movement_type")
	private MovementType movementType;
	
	private Integer quantity;
	
	@Column(name = "unit_cost")
	private BigDecimal unitCost;

	@Column(name = "stock_before")
	private Integer stockBefore;
	
	@Column(name = "stock_after")
	private Integer stockAfter;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser user;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}
	
}

package org.example.cscb634backend.repository.product;

import org.example.cscb634backend.entity.product.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductOfferRepository extends JpaRepository<ProductOffer,Long> {

	@Query("SELECT po FROM ProductOffer po WHERE po.available=true")
	List<ProductOffer> getAllValidOffers();
}

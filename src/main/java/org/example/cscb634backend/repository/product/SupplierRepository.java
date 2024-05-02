package org.example.cscb634backend.repository.product;

import org.example.cscb634backend.entity.product.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Optional<Supplier> findByName(String name);
}

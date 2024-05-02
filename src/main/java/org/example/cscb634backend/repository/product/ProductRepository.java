package org.example.cscb634backend.repository.product;

import org.example.cscb634backend.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}

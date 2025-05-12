package com.leandroSS.API_Loja.repositories;


import com.leandroSS.API_Loja.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<List<ProductEntity>> findByCategory(String category);
}

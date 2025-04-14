package com.leandroSS.API_Loja.repositories;


import com.leandroSS.API_Loja.entities.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepsitory extends JpaRepository<ProductEntity, Long> {
}

package com.leandroSS.API_Loja.entities.product;

public record ProductResponseDTO(Long id, String name, Integer price) {
    public ProductResponseDTO(ProductEntity product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}

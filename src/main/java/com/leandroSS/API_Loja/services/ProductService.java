package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.product.ProductEntity;
import com.leandroSS.API_Loja.entities.product.ProductRequestDTO;
import com.leandroSS.API_Loja.entities.product.ProductResponseDTO;
import com.leandroSS.API_Loja.repositories.ProductRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepsitory productRepsitory;

    public void postProduct(ProductRequestDTO productRequestDTO) {

        ProductEntity newProduct = new ProductEntity(productRequestDTO);

        this.productRepsitory.save(newProduct);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> productList = this.productRepsitory.findAll()
                .stream().map(product -> new ProductResponseDTO(product)).toList();

        return productList;
    }
}


















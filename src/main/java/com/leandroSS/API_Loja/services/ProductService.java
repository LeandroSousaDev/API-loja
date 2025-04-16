package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.product.ProductEntity;
import com.leandroSS.API_Loja.entities.product.ProductRequestDTO;
import com.leandroSS.API_Loja.entities.product.ProductResponseDTO;
import com.leandroSS.API_Loja.repositories.ProductRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepsitory productRepsitory;

    @Autowired
    private TokenService tokenService;


    public void postProduct(ProductRequestDTO productRequestDTO, JwtAuthenticationToken token) throws Exception {

        if (!this.tokenService.isAdmin(token)) {
            throw new Exception("Usuario não autorizado");
        }

        ProductEntity newProduct = new ProductEntity(productRequestDTO);

        this.productRepsitory.save(newProduct);
    }

    public List<ProductResponseDTO> getAllProducts() {
        var productList = this.productRepsitory.findAll()
                .stream().map(ProductResponseDTO::new).toList();

        return productList;
    }
}


















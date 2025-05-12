package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.product.ProductEntity;
import com.leandroSS.API_Loja.entities.product.ProductRequestDTO;
import com.leandroSS.API_Loja.entities.product.ProductResponseDTO;
import com.leandroSS.API_Loja.exception.UnauthorizedUser;
import com.leandroSS.API_Loja.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepsitory;

    @Autowired
    private TokenService tokenService;


    public void postProduct(ProductRequestDTO productRequestDTO, JwtAuthenticationToken token) {

        if (!this.tokenService.isAdmin(token)) {
            throw new UnauthorizedUser("Usuario não autorizado");
        }

        ProductEntity newProduct = new ProductEntity(productRequestDTO);

        this.productRepsitory.save(newProduct);
    }

    public List<ProductResponseDTO> getAllProducts() {
        var productList = this.productRepsitory.findAll();

        return productList
                .stream()
                .map(product -> new ProductResponseDTO(
                        product.getName(),
                        product.getCategory() ,
                        product.getPrice()))
                .toList();
    }

    public List<ProductResponseDTO> productByCAtegory(String category) {

        var listProduct = this.productRepsitory.findByCategory(category).orElse(null);

        return listProduct.stream()
                .map(product -> new ProductResponseDTO(
                        product.getName(),
                        product.getCategory(),
                        product.getPrice()
                )).toList();
    }
}


















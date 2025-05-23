package com.leandroSS.API_Loja.controllers;

import com.leandroSS.API_Loja.entities.product.dto.ProductRequestDTO;
import com.leandroSS.API_Loja.entities.product.dto.ProductResponseDTO;
import com.leandroSS.API_Loja.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Void> postProduct(@RequestBody ProductRequestDTO productRequestDTO,
                                            JwtAuthenticationToken token) {
        this.productService.postProduct(productRequestDTO, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(
            @RequestParam(required = false) String category) {

        List<ProductResponseDTO> allProduct;

        if (category != null) {
            allProduct = this.productService.productByCAtegory(category);
        } else {
            allProduct = this.productService.getAllProducts();
        }

        return ResponseEntity.status(HttpStatus.OK).body(allProduct);
    }
}

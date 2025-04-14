package com.leandroSS.API_Loja.controllers;

import com.leandroSS.API_Loja.entities.product.ProductEntity;
import com.leandroSS.API_Loja.entities.product.ProductRequestDTO;
import com.leandroSS.API_Loja.entities.product.ProductResponseDTO;
import com.leandroSS.API_Loja.serevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Void> postProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        this.productService.postProduct(productRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        var allProducts = this.productService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }
}
















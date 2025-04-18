package com.leandroSS.API_Loja.controllers;

import com.leandroSS.API_Loja.entities.product.ProductRequestDTO;
import com.leandroSS.API_Loja.entities.product.ProductResponseDTO;
import com.leandroSS.API_Loja.entities.user.UserType;
import com.leandroSS.API_Loja.repositories.UserRepository;
import com.leandroSS.API_Loja.services.ProductService;
import com.leandroSS.API_Loja.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping()
    public ResponseEntity<Void> postProduct(@RequestBody ProductRequestDTO productRequestDTO,
                                            JwtAuthenticationToken token) throws Exception {
        this.productService.postProduct(productRequestDTO, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        var allProducts = this.productService.getAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }
}
















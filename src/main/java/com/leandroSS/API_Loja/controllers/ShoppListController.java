package com.leandroSS.API_Loja.controllers;

import com.leandroSS.API_Loja.entities.shopList.dto.CreateShoppListDTO;
import com.leandroSS.API_Loja.entities.shopList.dto.UserItemListDTO;
import com.leandroSS.API_Loja.services.ShoppListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shoppList")
public class ShoppListController {

    @Autowired
    private ShoppListService shoppListService;

    @PostMapping()
    public ResponseEntity<Void> addItem(@RequestBody CreateShoppListDTO createShoppListDTO,
                                        JwtAuthenticationToken token) {
        this.shoppListService.addItem(createShoppListDTO, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<UserItemListDTO> listItemUser(JwtAuthenticationToken token) {
        var itemList = this.shoppListService.shoppList(token);
        return  ResponseEntity.status(HttpStatus.OK).body(itemList);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteItem(@PathVariable("userId") String userId, JwtAuthenticationToken token) throws Exception {
        this.shoppListService.deleteItem(Long.valueOf(userId), token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

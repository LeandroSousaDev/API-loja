package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.shopList.*;
import com.leandroSS.API_Loja.exception.UnauthorizedUser;
import com.leandroSS.API_Loja.repositories.ProductRepository;
import com.leandroSS.API_Loja.repositories.ShoppListRepository;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppListService {

    @Autowired
    private ShoppListRepository shoppListRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public void addItem(CreateShoppListDTO createShoppListDTO, JwtAuthenticationToken token) {

        if (!this.tokenService.isUser(token)) {
            throw new UnauthorizedUser("Usuario não autorizado");
        }

        var user = this.userRepository.findById(Long.valueOf(createShoppListDTO.userId()));
        var product = this.productRepository.findById(Long.valueOf(createShoppListDTO.productId()));
        var id = new ShoppListId(user.get().getId(), product.get().getId());

        ShoppList newItem = new ShoppList();
        newItem.setId(id);
        newItem.setUser(user.get());
        newItem.setProduct(product.get());
        newItem.setQuantity(createShoppListDTO.quantity());

        this.shoppListRepository.save(newItem);
    }

    public UserItemListDTO shoppList(JwtAuthenticationToken token) {

        var user = this.userRepository.findById(Long.valueOf(token.getName()));

        var itensList = user.get().getShoppLists().stream()
                .map(item -> new ListItemDTO(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getProduct().getPrice()))
                .toList();

        int value = 0;
        for (ListItemDTO item: itensList) {
            int currentValue = item.price() * item.quantity();
            value += currentValue;
        }

        var totalValue = value;


        return new UserItemListDTO(
                user.get().getUsername(),
                itensList,
                totalValue
        );

    }


//    public void deleteItem(Long itemId, JwtAuthenticationToken token) throws Exception {
//        if (!this.tokenService.isAdmin(token)) {
//            throw new Exception("Usuario não autorizado");
//        }
//
//        this.shoppListRepository.deleteById(itemId);
//    }


}
















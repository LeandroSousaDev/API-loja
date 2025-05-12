package com.leandroSS.API_Loja.services;

import com.leandroSS.API_Loja.entities.shopList.*;
import com.leandroSS.API_Loja.entities.shopList.dto.CreateShoppListDTO;
import com.leandroSS.API_Loja.entities.shopList.dto.ListItemDTO;
import com.leandroSS.API_Loja.entities.shopList.dto.UserItemListDTO;
import com.leandroSS.API_Loja.exception.UnauthorizedUser;
import com.leandroSS.API_Loja.repositories.ProductRepository;
import com.leandroSS.API_Loja.repositories.ShoppListRepository;
import com.leandroSS.API_Loja.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

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

        ShoppListEntity newItem = new ShoppListEntity();
        newItem.setId(id);
        newItem.setUser(user.get());
        newItem.setProduct(product.get());
        newItem.setQuantity(createShoppListDTO.quantity());

        this.shoppListRepository.save(newItem);
    }

    public UserItemListDTO shoppList(JwtAuthenticationToken token) {

        var user = this.userRepository.findById(Long.valueOf(token.getName())).orElse(null);

        var itensList = user.getShoppLists().stream()
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
                user.getUsername(),
                itensList,
                totalValue
        );

    }


    public void deleteItem(Long userId, JwtAuthenticationToken token) {
        if (!this.tokenService.isAdmin(token)) {
            throw new UnauthorizedUser("Usuario não autorizado");
        }

        var user = this.userRepository.findById(userId).orElse(null);

        var itemList = this.shoppListRepository.findByUser(user);

        this.shoppListRepository.deleteAll(itemList);
    }


}

package com.leandroSS.API_Loja.entities.shopList;

import com.leandroSS.API_Loja.entities.product.ProductEntity;
import com.leandroSS.API_Loja.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shoppList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppListEntity {

   @EmbeddedId
   private ShoppListId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_Id")
    private UserEntity user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Integer quantity;

}

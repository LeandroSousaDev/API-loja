package com.leandroSS.API_Loja.entities.shopList;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppListId {

    private Long userId;

    private Long productId;
}

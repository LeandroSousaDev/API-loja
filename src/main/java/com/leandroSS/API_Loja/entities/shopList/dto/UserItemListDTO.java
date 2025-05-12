package com.leandroSS.API_Loja.entities.shopList.dto;

import java.util.List;

public record UserItemListDTO(String username, List<ListItemDTO> itemList, Integer totalValue) {
}

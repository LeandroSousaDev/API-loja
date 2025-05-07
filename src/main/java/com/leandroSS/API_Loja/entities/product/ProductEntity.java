package com.leandroSS.API_Loja.entities.product;

import com.leandroSS.API_Loja.entities.shopList.ShoppList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Integer price;

    @OneToMany(mappedBy = "product")
    private List<ShoppList> shoppLists = new ArrayList<>();

    public ProductEntity(ProductRequestDTO data) {
        this.price = data.price();
        this.name = data.name();
        this.category = data.category();
    }


}

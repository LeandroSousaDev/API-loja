package com.leandroSS.API_Loja.repositories;

import com.leandroSS.API_Loja.entities.shopList.ShoppList;
import com.leandroSS.API_Loja.entities.shopList.ShoppListId;
import com.leandroSS.API_Loja.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppListRepository extends JpaRepository<ShoppList, ShoppListId> {
}

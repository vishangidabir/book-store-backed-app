package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.Cart;

import java.util.List;

public interface ICartService {

    Object addToCart(CartDTO cartDTO, String token);

    List<Cart> getAll();

    Cart getById(int id);

    String emptyCart();

    Object removeById(Integer id, String token);

    public String deleteByUserId(String token);

    Cart updateQuantity(String token, CartDTO cartDTO, int id);




}

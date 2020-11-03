package com.example.test.business;

import com.example.test.entity.Cart;

public interface CartService {
    void create(CreateCartCommand cmd);

    Cart findById(Long id);

    void update(Long id, UpdateCartCommand cmd);
//
//    void addCategory(Long id, AddCategoryCommand cmd);
}

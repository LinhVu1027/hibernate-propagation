package com.example.test.api;

import com.example.test.entity.Cart;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartResponse {
    private Long id;
    private String name;
    private List<Category> categories;

    @Getter
    private static class Category {
        private Long id;
        private String code;
        private String name;
        private List<Item> items;
    }

    @Getter
    private static class Item {
        private Long id;
        private String name;
        private Integer price;
        private Integer quantity;
    }

    public static CartResponse from(Cart cart) {
        List<Category> categories = cart.getCategories().stream().map(category -> {
            Category categoryResponse = new Category();
            categoryResponse.id = category.getId().getCart().getId();
            categoryResponse.code = category.getId().getCode();
            categoryResponse.name = category.getName();
            List<Item> items = category.getItems().stream().map(item -> {
                Item itemResponse = new Item();
                itemResponse.id = item.getId();
                itemResponse.name = item.getName();
                itemResponse.price = item.getPrice();
                itemResponse.quantity = item.getQuantity();
                return itemResponse;
            }).collect(Collectors.toList());
            categoryResponse.items = items;
            return categoryResponse;
        }).collect(Collectors.toList());

        CartResponse response = new CartResponse();
        response.id = cart.getId();
        response.name = cart.getName();
        response.categories = categories;
        return response;
    }

}

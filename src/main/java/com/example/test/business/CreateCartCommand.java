package com.example.test.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateCartCommand {
    private String name;
    private List<Category> categories;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Category {
        private String name;
        private String code;
        private List<Item> items;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Item {
        private String name;
        private Integer price;
        private Integer quantity;
    }
}

package com.example.test.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCartCommand {
    private String name;
    private List<Category> categories;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Category {
        private Long id;
        private String code;
        private String name;
        private List<Item> items;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Item {
        private Long id;
        private String name;
        private Integer price;
        private Integer quantity;
    }
}

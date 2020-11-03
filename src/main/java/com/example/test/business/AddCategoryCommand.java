package com.example.test.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AddCategoryCommand {
    private String name;
    private List<Item> items;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Item {
        private String name;
        private Integer price;
        private Integer quantity;
    }
}

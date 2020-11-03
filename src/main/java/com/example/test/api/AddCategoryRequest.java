package com.example.test.api;

import com.example.test.business.AddCategoryCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AddCategoryRequest {
    private String name;
    private List<Item> items;

    public AddCategoryCommand toCommand() {
        return new AddCategoryCommand(
                name,
                items.stream().map(Item::toCommand).collect(Collectors.toList())
        );
    }

    @Getter
    @Setter
    private static class Item {
        private String name;
        private Integer price;
        private Integer quantity;

        public AddCategoryCommand.Item toCommand() {
            return new AddCategoryCommand.Item(name, price, quantity);
        }
    }
}

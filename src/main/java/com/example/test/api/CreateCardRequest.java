package com.example.test.api;

import com.example.test.business.CreateCartCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateCardRequest {
    private String name;
    private List<Category> categories;

    @Getter
    @Setter
    private static class Category {
        private String name;
        private String code;
        private List<Item> items;

        public CreateCartCommand.Category toCommand() {
            return new CreateCartCommand.Category(
                    name,
                    code,
                    items.stream().map(Item::toCommand).collect(Collectors.toList())
            );
        }
    }

    @Getter
    @Setter
    private static class Item {
        private String name;
        private Integer price;
        private Integer quantity;

        public CreateCartCommand.Item toCommand() {
            return new CreateCartCommand.Item(name, price, quantity);
        }
    }

    public CreateCartCommand toCommand() {
        return new CreateCartCommand(
                name,
                categories.stream().map(Category::toCommand).collect(Collectors.toList())
        );
    }
}

package com.example.test.api;

import com.example.test.business.UpdateCartCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UpdateCartRequest {
    private String name;
    private List<Category> categories;

    @Getter
    @Setter
    private static class Category {
        private Long id;
        private String code;
        private String name;
        private List<Item> items;

        public UpdateCartCommand.Category toCommand() {
            return new UpdateCartCommand.Category(
                    id,
                    code,
                    name,
                    items.stream().map(Item::toCommand).collect(Collectors.toList())
            );
        }
    }

    @Getter
    @Setter
    private static class Item {
        private Long id;
        private String name;
        private Integer price;
        private Integer quantity;

        public UpdateCartCommand.Item toCommand() {
            return new UpdateCartCommand.Item(id, name, price, quantity);
        }
    }

    public UpdateCartCommand toCommand() {
        return new UpdateCartCommand(
                name,
                categories.stream().map(Category::toCommand).collect(Collectors.toList())
        );
    }
}

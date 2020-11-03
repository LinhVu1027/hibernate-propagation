package com.example.test.business;

import com.example.test.entity.Cart;
import com.example.test.entity.CartRepository;
import com.example.test.entity.Category;
import com.example.test.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    @Transactional
    public void create(CreateCartCommand cmd) {
        List<Category> categories = cmd.getCategories().stream().map(categoryCmd -> {
            Category category = new Category();
            Category.CategoryPK id = new Category.CategoryPK();
            id.setCode(categoryCmd.getCode());
            category.setId(id);
            category.setName(categoryCmd.getName());
            List<Item> items = categoryCmd.getItems().stream().map(itemCmd -> {
                Item item = new Item();
                item.setName(itemCmd.getName());
                item.setPrice(itemCmd.getPrice());
                item.setQuantity(itemCmd.getQuantity());
                return item;
            }).collect(Collectors.toList());
            category.setItems(items);
            return category;
        }).collect(Collectors.toList());
        Cart cart = new Cart();
        cart.setName(cmd.getName());
        cart.setCategories(categories);
        this.cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return this.cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));
    }

    @Override
    @Transactional
    public void update(Long id, UpdateCartCommand cmd) {
        List<Category> categories = cmd.getCategories().stream().map(categoryCmd -> {
            Category category = new Category();
            Category.CategoryPK cateId = new Category.CategoryPK();
            cateId.setCode(categoryCmd.getCode());
            category.setId(cateId);
            category.setName(categoryCmd.getName());
            List<Item> items = categoryCmd.getItems().stream().map(itemCmd -> {
                Item item = new Item();
                item.setId(itemCmd.getId());
                item.setName(itemCmd.getName());
                item.setPrice(itemCmd.getPrice());
                item.setQuantity(itemCmd.getQuantity());
                return item;
            }).collect(Collectors.toList());
            category.setItems(items);
            return category;
        }).collect(Collectors.toList());
        Cart cart = new Cart();
        cart.setId(id);
        cart.setName(cmd.getName());
        cart.setCategories(categories);
        this.cartRepository.save(cart);
    }

//    @Override
//    @Transactional
//    public void addCategory(Long id, AddCategoryCommand cmd) {
//        Cart cart = this.cartRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid id"));
//
//        List<Item> items = cmd.getItems().stream().map(itemCmd -> {
//            Item item = new Item();
//            item.setName(itemCmd.getName());
//            item.setPrice(itemCmd.getPrice());
//            item.setQuantity(itemCmd.getQuantity());
//            return item;
//        }).collect(Collectors.toList());
//        Category category = new Category();
//        category.setName(cmd.getName());
//        category.setItems(items);
//
//        cart.addCategory(category);
//        this.cartRepository.save(cart);
//
//        System.out.println(cart.getCategories().get(cart.getCategories().size() - 1).getId());
//        System.out.println("--------------");
//    }
}

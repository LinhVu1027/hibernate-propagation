package com.example.test.api;

import com.example.test.business.CartService;
import com.example.test.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public void add(@RequestBody CreateCardRequest request) {
        this.cartService.create(request.toCommand());
    }

    @GetMapping("/{id}")
    public CartResponse findById(@PathVariable Long id) {
        Cart cart = this.cartService.findById(id);
        return CartResponse.from(cart);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateCartRequest request) {
        this.cartService.update(id, request.toCommand());
    }
//
//    @PutMapping("/{id}/categories")
//    public void addCategory(@PathVariable Long id, @RequestBody AddCategoryRequest request) {
//        this.cartService.addCategory(id, request.toCommand());
//    }

}

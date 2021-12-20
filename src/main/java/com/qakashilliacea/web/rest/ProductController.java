package com.qakashilliacea.web.rest;

import com.qakashilliacea.entity.Product;
import com.qakashilliacea.respository.ProductRepository;
import com.qakashilliacea.util.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constants.PRIVATE_API_ENDPOINT + "/product")
public class ProductController {
    private final ProductRepository productRepository;

    @PostMapping
    private void create() {
        productRepository.save(new Product());
    }
    @GetMapping("/{id}")
    private ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id).orElse(null));
    }
}

package com.pvkhai.gearpandabackend.controllers;

import com.pvkhai.gearpandabackend.models.Product;
import com.pvkhai.gearpandabackend.models.ResponseObject;
import com.pvkhai.gearpandabackend.repositories.ProductRepository;
import com.pvkhai.gearpandabackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    // Add new product
    @PostMapping
    public ResponseEntity<ResponseObject> addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    //Get all products: SEARCH(Keyword)
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> getAllProduct(@RequestParam String keyword) {
        return productService.productsList(keyword);
    }

    //Get Product with ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Update Product
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        return productService.updateProduct(id, newProduct);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    // Get product by Type
    @GetMapping("/category/{type}")
    public ResponseEntity<ResponseObject> getProductByType(@PathVariable String type) {
        return productService.getProductByType(type);
    }

    // Sorting product
    @GetMapping("/sort/{field}")
    public List sortingProduct(@PathVariable String field) {
        return productService.findByWithSorting(field);
    }

    // Pagination
    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Product> findByWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        return productService.findByWithPagination(offset, pageSize);
    }

    // Quantity statistics
    @GetMapping("/statistics")
    List<Optional> statistics(){
        return productRepository.countNumberProduct();
    }

}

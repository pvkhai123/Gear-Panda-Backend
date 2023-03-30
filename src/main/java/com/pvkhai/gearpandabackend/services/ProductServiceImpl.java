package com.pvkhai.gearpandabackend.services;

import com.pvkhai.gearpandabackend.models.Product;
import com.pvkhai.gearpandabackend.models.ResponseObject;
import com.pvkhai.gearpandabackend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    /**
     * Add product new:
     * Case 1: coincide with existing products -> update quantity
     * Case 2: not coincide with existing products -> add new Product
     *
     * @param newProduct
     * @return
     */
    public ResponseEntity<ResponseObject> addNewProduct(Product newProduct) {
        Product product = productRepository.findByName(newProduct.getName().trim());
        System.out.printf(String.valueOf(product));
        if (product != null) {
            productRepository.findByName(newProduct.getName()).setQuantity(product.getQuantity() + newProduct.getQuantity());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Insert Product successfully!", productRepository.save(product))
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Insert Product successfully!", productRepository.save(newProduct))
            );
        }
    }


    /**
     * Get all products with keyword (Search)
     *
     * @param keyword
     * @return
     */
    public ResponseEntity<ResponseObject> productsList(String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Query product successfully", productRepository.findAll(keyword))
        );
    }


    /**
     * Get product by ID
     *
     * @param id
     * @return
     */
    public ResponseEntity<ResponseObject> getProductById(Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Query product successfully", foundProduct)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Could not found product with Id = " + id, "N/A")
                );
    }


    /**
     * Get product by Type(Keyboard, Mouse,...)
     *
     * @param type
     * @return
     */
    public ResponseEntity<ResponseObject> getProductByType(String type) {
        List<Product> foundProducts = productRepository.findByType(type.trim());
        return foundProducts.size() > 0 ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Query product successfully", foundProducts)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Could not found product with Type = " + type, "N/A")
                );
    }


    /**
     * Update info product
     *
     * @param id
     * @param newProduct
     * @return
     */
    public ResponseEntity<ResponseObject> updateProduct(Long id, Product newProduct) {
        Product updateProduct = productRepository.findById(id)
                .map(product -> {
                    product.setCode(newProduct.getCode());
                    product.setType(newProduct.getType());
                    product.setName(newProduct.getName());
                    product.setBrand(newProduct.getBrand());
                    product.setIllustration(newProduct.getIllustration());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setQuantity(newProduct.getQuantity());
                    return productRepository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Update Product successfully!", updateProduct)
        );
    }


    /**
     * Delete product based on ID
     *
     * @param id
     * @return
     */
    public ResponseEntity<ResponseObject> deleteProduct(Long id) {
        Boolean exists = productRepository.existsById(id);
        if (exists) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Delete Product successfully!", "N/A")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Cannot found product!", "N/A")
            );
        }
    }


    /**
     * Sorting all product
     *
     * @param field
     * @return
     */
    public List<Product> findByWithSorting(String field) {
        return field != null ?
                productRepository.findAll(Sort.by(Sort.Direction.ASC, field)) :
                productRepository.findAll();
    }


    /**
     * Pagination
     *
     * @param offset
     * @param pageSize
     * @return
     */
    public Page<Product> findByWithPagination(int offset, int pageSize) {
        return productRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public ResponseEntity<ResponseObject> getProductByCode(Long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK", "Query product successfully", foundProduct)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("FAILED", "Could not found product with Id = " + id, "N/A")
                );
    }

}

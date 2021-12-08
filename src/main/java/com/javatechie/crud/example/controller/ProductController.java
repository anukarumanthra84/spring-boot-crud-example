package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productcatalog")
public class ProductController {
    Logger log=LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        log.info("Adding Product");
        ResponseEntity<Product> responseEntity=new ResponseEntity<>(service.saveProduct(product), HttpStatus.CREATED);
        return responseEntity;
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

//    @GetMapping("/products")
//    public List<Product> findAllProducts() {
//        log.info("Retrieving all products");
//        return service.getProducts();
//    }

    @GetMapping("/products")
    public List<Product> findAllProducts(@RequestHeader Map<String,String> header) {
        log.info("Retrieving all products");
        System.out.println(header);
        return service.getProducts();
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id) {
        log.info("Retrieving products by id");
        return service.getProductById(id);

    }


    @GetMapping("/product/{name}")
    public Product findProductByName(@PathVariable String name) {

        log.info("Retrieving products by name");
        return service.getProductByName(name);
    }

    @PutMapping("/product/update/{id}")
    public Product updateProduct(@PathVariable int id,@RequestBody Product product) {
        log.info("updating product by id");
        return service.updateProduct(id,product);
    }
   @PatchMapping("/product/update/{id}")
  public Product patchProduct(@RequestBody Product product,@PathVariable int id) {
      log.info("Partially updating product by id");
      return service.patchProduct(id,product);
  }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        log.info("Deleting products by id");
        return service.deleteProduct(id);
    }
}

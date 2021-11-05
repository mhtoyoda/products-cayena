package com.api.products.application.controller;

import com.api.products.application.model.OperationModel;
import com.api.products.application.model.ProductsModel;
import com.api.products.application.service.IProductService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductsController {

  private final IProductService productService;

  @GetMapping("/products")
  @ApiOperation(value = "Get List products", response = String.class, notes = "Get List products")
  public ResponseEntity<?> getProducts() {
    List<ProductsModel> products = productService.getProducts();

    if(products.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(products);
  }

  @GetMapping("/products/{id}")
  @ApiOperation(value = "Get product by id", response = String.class, notes = "Get product by id")
  public ResponseEntity<?> getProductsById(@PathVariable Long id) {
    ProductsModel product = productService.getProductsById(id);
    return ResponseEntity.ok(product);
  }

  @PostMapping("/products")
  @ApiOperation(value = "Create product", response = String.class, notes = "Create product")
  public ResponseEntity<?> createProduct(@RequestBody @Valid ProductsModel productsModel) {
    ProductsModel products = productService.createProduct(productsModel);

    return ResponseEntity.ok(products);
  }

  @PutMapping("/products")
  @ApiOperation(value = "Update product", response = String.class, notes = "Update product")
  public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductsModel productsModel) {
    ProductsModel products = productService.updateProduct(productsModel);

    return ResponseEntity.ok(products);
  }

  @PatchMapping("/products")
  @ApiOperation(value = "Increase or Decrease stock product", response = String.class, notes = "Increase or Decrease stock product")
  public ResponseEntity<?> updateStockProduct(@RequestBody @Valid OperationModel operationModel) {
    ProductsModel products = productService.updateStockProduct(operationModel);

    return ResponseEntity.ok(products);
  }

  @DeleteMapping("/products/{id}")
  @ApiOperation(value = "Delete product by id", response = String.class, notes = "Delete product by id")
  public ResponseEntity<?> deleteProductsById(@PathVariable Long id) {
    productService.deleteProductsById(id);
    return ResponseEntity.ok().build();
  }
}

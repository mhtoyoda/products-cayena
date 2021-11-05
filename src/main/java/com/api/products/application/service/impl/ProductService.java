package com.api.products.application.service.impl;

import com.api.products.application.exception.ProductNotFoundException;
import com.api.products.application.exception.SuppliersBadRequestException;
import com.api.products.application.exception.SuppliersNotFoundException;
import com.api.products.application.model.OperationEnum;
import com.api.products.application.model.OperationModel;
import com.api.products.application.model.ProductsModel;
import com.api.products.application.model.mapper.ProductsMapper;
import com.api.products.application.service.IProductService;
import com.api.products.domain.entity.Products;
import com.api.products.domain.entity.Suppliers;
import com.api.products.domain.repository.ProductsRepository;
import com.api.products.domain.repository.SuppliersRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

  private final ProductsRepository productsRepository;

  private final SuppliersRepository suppliersRepository;

  private final ProductsMapper productsMapper;

  @Override
  public List<ProductsModel> getProducts() {
    List<ProductsModel> products = new ArrayList<>();
    productsRepository.findAll().forEach(product -> products.add(productsMapper.toModel(product)));
    return products;
  }

  @Override
  public ProductsModel createProduct(ProductsModel productsModel) {
    Optional<Suppliers> suppliersOptional = suppliersRepository.findById(productsModel.getSuppliers().getId());
    Suppliers suppliers = suppliersOptional.orElseThrow(() -> new SuppliersNotFoundException(
        "Suppliers with id " + productsModel.getSuppliers().getId() + " not found"));

    Products products = productsMapper.toEntity(productsModel);
    products.setCreated(LocalDateTime.now());
    products.setSuppliers(suppliers);
    return productsMapper.toModel(productsRepository.save(products));
  }

  @Override
  public ProductsModel getProductsById(Long id) {
    Optional<Products> productsOptional = productsRepository.findById(id);
    Products product = productsOptional
        .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

    return productsMapper.toModel(product);
  }

  @Override
  public ProductsModel updateProduct(ProductsModel productsModel) {

    if(Objects.isNull(productsModel.getId())) {
      throw new SuppliersBadRequestException(
          "Product id " + productsModel.getId() + " must not sent");
    }

    Products product = productsRepository.findById(productsModel.getId())
        .orElseThrow(() -> new SuppliersNotFoundException(
            "Product with id " + productsModel.getId() + " not found"));

    Suppliers suppliers = suppliersRepository.findById(productsModel.getSuppliers().getId()).orElseThrow(() -> new SuppliersNotFoundException(
        "Suppliers with id " + productsModel.getSuppliers().getId() + " not found"));

    suppliers.setName(productsModel.getSuppliers().getName());
    suppliers.setUpdated(LocalDateTime.now());

    product.setSuppliers(suppliers);
    product.setName(productsModel.getName());
    product.setPrice(productsModel.getPrice());
    product.setQuantity(productsModel.getQuantity());
    product.setUpdated(LocalDateTime.now());

    return productsMapper.toModel(productsRepository.save(product));
  }

  @Override
  public void deleteProductsById(Long id) {
    Products product = productsRepository.findById(id)
        .orElseThrow(() -> new SuppliersNotFoundException(
            "Product with id " + id + " not found"));

    productsRepository.delete(product);
  }

  @Override
  public ProductsModel updateStockProduct(OperationModel operationModel) {
    Products product = productsRepository.findById(operationModel.getId())
        .orElseThrow(() -> new SuppliersNotFoundException(
            "Product with id " + operationModel.getId() + " not found"));

    Integer quantity = product.getQuantity();

    OperationEnum operation = operationModel.getOperation();

    if(operation.equals(OperationEnum.DECREASE) && quantity > 0) {
      quantity = quantity - operationModel.getQuantity();
      product.setQuantity(quantity);
    }

    if(operation.equals(OperationEnum.INCREASE)) {
      quantity = quantity + operationModel.getQuantity();
      product.setQuantity(quantity);
    }

    product.setUpdated(LocalDateTime.now());
    return productsMapper.toModel(productsRepository.save(product));
  }
}

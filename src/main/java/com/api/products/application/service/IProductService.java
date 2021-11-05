package com.api.products.application.service;

import com.api.products.application.model.OperationModel;
import com.api.products.application.model.ProductsModel;
import java.util.List;

public interface IProductService {

  List<ProductsModel> getProducts();

  ProductsModel createProduct(ProductsModel productsModel);

  ProductsModel getProductsById(Long id);

  ProductsModel updateProduct(ProductsModel productsModel);

  void deleteProductsById(Long id);

  ProductsModel updateStockProduct(OperationModel operationModel);
}

package com.api.products.application.model.mapper;

import com.api.products.application.model.ProductsModel;
import com.api.products.domain.entity.Products;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

  Products toEntity(ProductsModel productsModel);
  ProductsModel toModel(Products products);
}

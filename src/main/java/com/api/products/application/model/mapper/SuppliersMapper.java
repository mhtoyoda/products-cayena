package com.api.products.application.model.mapper;

import com.api.products.application.model.SuppliersModel;
import com.api.products.domain.entity.Suppliers;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuppliersMapper {

  Suppliers toEntity(SuppliersModel suppliersModel);
  SuppliersModel toModel(Suppliers suppliers);
}

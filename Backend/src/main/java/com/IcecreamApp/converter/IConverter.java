package com.IcecreamApp.converter;

public interface IConverter<DTOType, entityType> {
	DTOType toDTO(DTOType dto, entityType entity);
	
	entityType toEntity(DTOType dto, entityType entity);
}

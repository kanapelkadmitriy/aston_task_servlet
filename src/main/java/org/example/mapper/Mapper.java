package org.example.mapper;

import java.util.List;

public interface Mapper<MODEL, DTO> {

    MODEL convertToModel(DTO dto);
    DTO convertToDto(MODEL model);
    List<MODEL> convertToModelList(List<DTO> dtoList);
    List<DTO> convertToDtoList(List<MODEL> models);
}

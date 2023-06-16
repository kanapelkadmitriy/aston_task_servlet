package org.example.mapper;

import org.example.model.dto.CharacteristicDto;
import org.example.model.entity.Characteristic;

import java.util.ArrayList;
import java.util.List;

public class CharacteristicMapper implements Mapper<Characteristic, CharacteristicDto> {
    @Override
    public Characteristic convertToModel(CharacteristicDto characteristicDto) {
        return Characteristic.builder()
                .name(characteristicDto.getName())
                .build();
    }

    @Override
    public CharacteristicDto convertToDto(Characteristic characteristic) {
        return CharacteristicDto.builder()
                .name(characteristic.getName())
                .build();
    }

    @Override
    public List<Characteristic> convertToModelList(List<CharacteristicDto> characteristicDtos) {
        return characteristicDtos == null
                ? new ArrayList<>()
                : characteristicDtos.stream().map(this::convertToModel).toList();
    }

    @Override
    public List<CharacteristicDto> convertToDtoList(List<Characteristic> characteristics) {
        return characteristics == null
                ? new ArrayList<>()
                : characteristics.stream().map(this::convertToDto).toList();
    }
}

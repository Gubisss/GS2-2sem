package br.com.fiap.gs2.gs2.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.gs2.gs2.dtos.EnergyRequestCreateDto;
import br.com.fiap.gs2.gs2.dtos.EnergyRequestUpdateDto;
import br.com.fiap.gs2.gs2.dtos.EnergyResponseDto;
import br.com.fiap.gs2.gs2.model.Energy;

@Component
public class EnergyMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EnergyResponseDto toDto(Energy energy){
        return modelMapper.map(energy, EnergyResponseDto.class);
    }

    public Energy toModel(EnergyRequestCreateDto dto) {
        return modelMapper.map(dto, Energy.class);
    }

    public Energy toModel(Long id, EnergyRequestUpdateDto dto) {
        Energy result = modelMapper.map(dto, Energy.class);
        result.setId(id);
        return result;
    } 
}

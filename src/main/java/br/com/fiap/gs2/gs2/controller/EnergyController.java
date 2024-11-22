package br.com.fiap.gs2.gs2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.gs2.gs2.dtos.EnergyRequestCreateDto;
import br.com.fiap.gs2.gs2.dtos.EnergyRequestUpdateDto;
import br.com.fiap.gs2.gs2.dtos.EnergyResponseDto;
import br.com.fiap.gs2.gs2.mapper.EnergyMapper;
import br.com.fiap.gs2.gs2.repository.EnergyRepository;
import br.com.fiap.gs2.gs2.service.EnergyService;
import br.com.fiap.gs2.gs2.views.EnergyFullView;
import br.com.fiap.gs2.gs2.views.EnergySimpleView;
import br.com.fiap.gs2.gs2.views.EnergyViewType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/energys")
@RequiredArgsConstructor
public class EnergyController {    
    
    private final EnergyService energyService;
    private final EnergyMapper energyMapper;
    private final EnergyRepository energyRepository;

    @GetMapping
    public ResponseEntity<List<EnergyResponseDto>> list() {
        List<EnergyResponseDto> dtos = energyService.list()
            .stream()
            .map(e -> energyMapper.toDto(e))
            .toList();
        
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<EnergyResponseDto> create(@RequestBody EnergyRequestCreateDto dto) {        

        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(
        			energyMapper.toDto(
        					energyService.save(energyMapper.toModel(dto)))
        			);
    }

    @PutMapping("{id}")
    public ResponseEntity<EnergyResponseDto> update(
                        @PathVariable Long id, 
                        @RequestBody EnergyRequestUpdateDto dto) {
        if (! energyService.existsById(id)){
            throw new RuntimeException("Id inexistente");
        }                
        return ResponseEntity.ok()
        		.body(
        			energyMapper.toDto(
        				energyService.save(energyMapper.toModel(id, dto)))
        		);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (! energyService.existsById(id)){
        	throw new RuntimeException("Id inexistente");
        }

        energyService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<EnergyResponseDto> findById(@PathVariable Long id) {    	
    	return ResponseEntity.ok()
    			.body(
    				energyService
    					.findById(id)
    					.map(e -> energyMapper.toDto(e))
    					.orElseThrow(() -> new RuntimeException("Id inexistente"))
    			);    	  		     
    }
    
    @GetMapping("/find")
    public  ResponseEntity<?> findByNome(
                @RequestParam String nome, 
                EnergyViewType type) { 

        switch (type) {
            case FULL:
                return ResponseEntity.ok().body(energyRepository.findAllByNomeContains(nome, EnergyFullView.class));                
            case SIMPLE:
                return ResponseEntity.ok().body(energyRepository.findAllByNomeContains(nome, EnergySimpleView.class));            
        }
        return ResponseEntity.noContent().build();
    }
}

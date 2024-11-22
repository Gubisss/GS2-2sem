package br.com.fiap.gs2.gs2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.gs2.gs2.model.Energy;
import br.com.fiap.gs2.gs2.repository.EnergyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnergyService {
    private final EnergyRepository energyRepository;

    public List<Energy> list() {
        return energyRepository.findAll();
    }

    public Energy save(Energy energy) {        
        return energyRepository.save(energy);
    }

    public boolean existsById(Long id) {        
        return energyRepository.existsById(id);
    }

    public void delete(Long id) {
        energyRepository.deleteById(id);
    }

    public Optional<Energy> findById(Long id) {
        return energyRepository.findById(id);
    }
   
}

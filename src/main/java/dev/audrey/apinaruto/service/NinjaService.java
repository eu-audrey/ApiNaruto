package dev.audrey.apinaruto.service;

import dev.audrey.apinaruto.exception.ResourceNotFoundException;
import dev.audrey.apinaruto.model.Ninja;
import dev.audrey.apinaruto.model.dto.NinjaRequestDTO;
import dev.audrey.apinaruto.model.dto.NinjaResponseDTO;
import dev.audrey.apinaruto.repository.NinjaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    private Ninja requestDTOParaEntity(NinjaRequestDTO ninjaRequestDTO) {
        Ninja ninja = new Ninja();
        ninja.setNome(ninjaRequestDTO.nome());
        ninja.setAldeia(ninjaRequestDTO.aldeia());
        ninja.setIdade(ninjaRequestDTO.idade());
        return ninja;
    }

    private NinjaResponseDTO entityParaResponseDTO(Ninja ninja) {
        return new NinjaResponseDTO(ninja.getId(), ninja.getNome(), ninja.getAldeia());
    }

    public NinjaResponseDTO criarNinja(NinjaRequestDTO requestDTO) {
        Ninja ninjaParaSalvar = requestDTOParaEntity(requestDTO);
        Ninja ninjaSalvo = ninjaRepository.save(ninjaParaSalvar);
        return entityParaResponseDTO(ninjaSalvo);
    }

    public Page<NinjaResponseDTO> buscarNinjasPaginado(Pageable pageable) {
        Page<Ninja> paginasDeNinjas = ninjaRepository.findAll(pageable);
        return paginasDeNinjas.map(this::entityParaResponseDTO);
    }

    public Optional<NinjaResponseDTO> buscarNinjaPorId(Long id) {
        return ninjaRepository.findById(id).map(this::entityParaResponseDTO);
    }

    public NinjaResponseDTO atualizarNinjaPorId(Long id, NinjaRequestDTO requestDTO) {
        Ninja ninjaExistente = ninjaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ninja não encontrado. Id: " + id));

        if (requestDTO.nome() != null) {
            ninjaExistente.setNome(requestDTO.nome());
        }
        if (requestDTO.aldeia() != null) {
            ninjaExistente.setAldeia(requestDTO.aldeia());
        }
        if (requestDTO.idade() != null) {
            ninjaExistente.setIdade(requestDTO.idade());
        }
        Ninja ninjaAtualizado = ninjaRepository.save(ninjaExistente);
        return entityParaResponseDTO(ninjaAtualizado);
    }

    public void deletarNinjaPorId(Long id) {
        if (!ninjaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ninja não encontrado para o id " + id);
        }
        ninjaRepository.deleteById(id);
    }
}

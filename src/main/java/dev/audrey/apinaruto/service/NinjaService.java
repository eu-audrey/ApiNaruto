package dev.audrey.apinaruto.service;

import dev.audrey.apinaruto.exception.ResourceNotFoundException;
import dev.audrey.apinaruto.model.Ninja;
import dev.audrey.apinaruto.repository.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository){
        this.ninjaRepository = ninjaRepository;
    }

    //criar
    public Ninja criarNinja(Ninja ninja){
        return ninjaRepository.save(ninja);
    }

    //listar
    public List<Ninja> buscarNinja(){
        return ninjaRepository.findAll();
    }

    //listarPorId
    //se o usuario pode passar algo que nao existe, melhor usar optional
    public Optional<Ninja> buscarNinjaPorId(UUID uuid){
        return ninjaRepository.findById(uuid);
    }

    //atualizar ninjas
    public Ninja atualizarNinjaPorId(UUID uuid, Ninja ninja){
        //primeiro preciso achar o ninja
        Optional<Ninja> ninjaDesatualizado = ninjaRepository.findById(uuid);

        if(ninjaDesatualizado.isPresent()){
            Ninja ninjaAtualizado = ninjaDesatualizado.get();

            // Atualiza apenas os campos que não são nulos na requisição
            if (ninja.getNome() != null) {
                ninjaAtualizado.setNome(ninja.getNome());
            }
            if (ninja.getAldeia() != null) {
                ninjaAtualizado.setAldeia(ninja.getAldeia());
            }
            if (ninja.getIdade() != null) {
                ninjaAtualizado.setIdade(ninja.getIdade());
            }

            return ninjaRepository.save(ninjaAtualizado);
        }else{
            throw new ResourceNotFoundException("Ninja não encontrado. Tente novamente. Id fornecido: "+ uuid);
        }

    }

    //deletarPorId
    public void deletarNinjaPorId(UUID uuid){
        if (!ninjaRepository.existsById(uuid)) {
            throw new RuntimeException("Ninja não encontrado para o id " + uuid);
        }
        ninjaRepository.deleteById(uuid);
    }


}

package dev.audrey.apinaruto.service;

import dev.audrey.apinaruto.model.Ninja;
import dev.audrey.apinaruto.repository.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    /*TODO: Refatorar a instancia*/
    @Autowired
    private NinjaRepository ninjaRepository;

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
    public Optional<Ninja> buscarNinjaPorId(Long id){
        return ninjaRepository.findById(id);
    }

    //atualizar ninjas
    public Ninja atualizarNinjaPorId(Long id, Ninja ninja){
        //primeiro preciso achar o ninja
        Optional<Ninja> ninjaDesatualizado = ninjaRepository.findById(id);

        if(ninjaDesatualizado.isPresent()){
            Ninja ninjaAtualizado = ninjaDesatualizado.get();

            // Atualiza apenas os campos que não são nulos na requisição
            if (ninja.getNome() != null) {
                ninjaAtualizado.setNome(ninja.getNome());
            }
            if (ninja.getAldeia() != null) {
                ninjaAtualizado.setAldeia(ninja.getAldeia());
            }
            if (ninja.getElemento() != null) {
                ninjaAtualizado.setElemento(ninja.getElemento());
            }
            if (ninja.getImgUrl() != null) {
                ninjaAtualizado.setImgUrl(ninja.getImgUrl());
            }

            return ninjaRepository.save(ninjaAtualizado);
        }else{
            throw new RuntimeException("Ninja não encontrado. Tente novamente. Id fornecido: "+ id);
        }

    }

    //deletarPorId
    public void deletarNinjaPorId(Long id){
        if (!ninjaRepository.existsById(id)) {
            throw new RuntimeException("Ninja não encontrado para o id " + id);
        }
        ninjaRepository.deleteById(id);
    }


}

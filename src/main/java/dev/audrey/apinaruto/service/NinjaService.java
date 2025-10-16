package dev.audrey.apinaruto.service;

import dev.audrey.apinaruto.model.Ninja;
import dev.audrey.apinaruto.repository.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjaService {

    /*TODO: Refatorar a instancia*/
    @Autowired
    private NinjaRepository ninjaRepository;

    //criar
    public Ninja criarNinja(Ninja ninja){

        return ninjaRepository.save(ninja);
    }

    //mostrar
    public List<Ninja> buscarNinja(){
        return ninjaRepository.findAll();
    }


}

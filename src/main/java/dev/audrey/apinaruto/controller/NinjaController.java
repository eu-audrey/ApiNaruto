package dev.audrey.apinaruto.controller;

import dev.audrey.apinaruto.model.Ninja;
import dev.audrey.apinaruto.service.NinjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/ninjas")
public class NinjaController {

    /*TODO: Refatorar a instancia*/
    @Autowired
    private NinjaService ninjaService;

    @GetMapping
    public ResponseEntity<List<Ninja>> findAllNinjas(){
        List<Ninja> allNinjas = ninjaService.buscarNinja();
        return new ResponseEntity<>(allNinjas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){ 
        Optional<Ninja> ninja = ninjaService.buscarNinjaPorId(id);

        if(ninja.isPresent()){
            return ResponseEntity.ok(ninja.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Desculpa, mas nao achei esse ninja... Digite um id válido🚩");
        }
    }

    @PostMapping
    public ResponseEntity<Ninja> save(@RequestBody Ninja ninja){
        Ninja novoNinja = ninjaService.criarNinja(ninja);
        return new ResponseEntity<>(novoNinja, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Ninja ninja) {

        try {
            Ninja ninjaAtualizado = ninjaService.atualizarNinjaPorId(id, ninja);
            return new ResponseEntity<>(ninjaAtualizado, HttpStatus.OK);

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            ninjaService.deletarNinjaPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

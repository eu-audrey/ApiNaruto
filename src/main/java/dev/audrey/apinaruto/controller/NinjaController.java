package dev.audrey.apinaruto.controller;

import dev.audrey.apinaruto.model.dto.NinjaDTO;
import dev.audrey.apinaruto.exception.ResourceNotFoundException;
import dev.audrey.apinaruto.model.Ninja;
import dev.audrey.apinaruto.service.NinjaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService){
      this.ninjaService = ninjaService;
    }

    @GetMapping
    public ResponseEntity<List<Ninja>> findAllNinjas(){
        List<Ninja> allNinjas = ninjaService.buscarNinja();
        return new ResponseEntity<>(allNinjas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ninja> buscarPorId(@PathVariable("id") UUID uuid) {
        return ninjaService.buscarNinjaPorId(uuid)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Ninja não encontrado. Id: " + uuid));
    }

    @PostMapping
    public ResponseEntity<Ninja> criarNinja(@Valid @RequestBody NinjaDTO ninjaDTO) {
        Ninja ninja = new Ninja();
        ninja.setNome(ninjaDTO.nome());
        ninja.setAldeia(ninjaDTO.aldeia());
        Ninja criado = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ninja> atualizar(@PathVariable("id") UUID uuid, @RequestBody NinjaDTO dto) {
        Ninja ninja = new Ninja();
        // Atualização parcial é suportada pelo service (que verifica campos nulos)
        ninja.setNome(dto.nome());
        Ninja atualizado = ninjaService.atualizarNinjaPorId(uuid, ninja);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") UUID uuid){
            ninjaService.deletarNinjaPorId(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

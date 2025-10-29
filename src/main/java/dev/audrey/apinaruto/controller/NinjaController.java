package dev.audrey.apinaruto.controller;

import dev.audrey.apinaruto.model.dto.NinjaRequestDTO;
import dev.audrey.apinaruto.model.dto.NinjaResponseDTO;
import dev.audrey.apinaruto.service.NinjaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping
    public ResponseEntity<Page<NinjaResponseDTO>> listarNinjasPaginado(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        Page<NinjaResponseDTO> paginaDeNinjas = ninjaService.buscarNinjasPaginado(pageable);
        return ResponseEntity.ok(paginaDeNinjas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {
        return ninjaService.buscarNinjaPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Heitor, nao achei seu ninja");
        });
        }

    @PostMapping
    public ResponseEntity<NinjaResponseDTO> criarNinja(@Valid @RequestBody NinjaRequestDTO requestDTO) {
        NinjaResponseDTO ninjaCriado = ninjaService.criarNinja(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ninjaCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NinjaResponseDTO> atualizarNinja(@PathVariable("id") Long id, @Valid @RequestBody NinjaRequestDTO requestDTO) {
        NinjaResponseDTO ninjaAtualizado = ninjaService.atualizarNinjaPorId(id, requestDTO);
        return ResponseEntity.ok(ninjaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNinja(@PathVariable("id") Long id) {
        ninjaService.deletarNinjaPorId(id);
        return ResponseEntity.noContent().build();
    }
}

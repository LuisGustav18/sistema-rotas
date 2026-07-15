package com.luis.sistema_rotas.domain.imagem.resource;

import com.luis.sistema_rotas.domain.imagem.dto.ImagemDTO;
import com.luis.sistema_rotas.domain.imagem.entity.Imagem;
import com.luis.sistema_rotas.domain.imagem.service.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "imagens")
public class ImagemResource {

    @Autowired
    private ImagemService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ImagemDTO> findById(@PathVariable UUID id){
        Imagem obj = service.findById(id);
        return ResponseEntity.ok().body(new ImagemDTO(obj));
    }

    @GetMapping(value = "rota/{id}")
    public ResponseEntity<List<ImagemDTO>> findImagemByRotaId(@PathVariable UUID id){
        List<Imagem> list = service.findImagemByRotaId(id);
        List<ImagemDTO> listDTO = list.stream().map(ImagemDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ImagemDTO> create(@Valid @ModelAttribute ImagemDTO objDTO, @RequestParam(value = "imagem") MultipartFile imagem) throws Exception {
        Imagem obj = service.create(objDTO, imagem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

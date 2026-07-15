package com.luis.sistema_rotas.domain.imagem.service;

import com.luis.sistema_rotas.domain.imagem.dto.ImagemDTO;
import com.luis.sistema_rotas.domain.imagem.entity.Imagem;
import com.luis.sistema_rotas.domain.imagem.repository.ImagemRepository;
import com.luis.sistema_rotas.domain.rota.entity.Rota;
import com.luis.sistema_rotas.domain.rota.service.RotaService;
import com.luis.sistema_rotas.exceptions.ObjectNotFoundException;
import com.luis.sistema_rotas.infra.minio.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagemService {

    @Autowired
    private ImagemRepository repository;

    @Autowired
    private RotaService rotaService;

    @Autowired
    private MinioService minioService;

    public Imagem findById(UUID id){
        Optional<Imagem> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Imagem não encontrada"));
    }

    public List<Imagem> findImagemByRotaId(UUID id){
        List<Imagem> list = repository.findImagemByRotaId(id);

        if (list.isEmpty()){
            throw new ObjectNotFoundException("Nenhum imagem inserida");
        }

        return list;
    }

    public Imagem create(ImagemDTO objDTO, MultipartFile file) throws Exception {
        Imagem obj = transferDataOfDTO(objDTO);
        obj.setUrl(minioService.uploadImagem(file));
        return repository.save(obj);
    }

    public void delete(UUID id) throws Exception {
        Imagem obj = findById(id);
        minioService.deletar(obj.getUrl());
        repository.delete(obj);
    }

    private Imagem transferDataOfDTO (ImagemDTO objDTO){
        Rota rota = rotaService.findById(objDTO.rota());

        Imagem obj = new Imagem();
        obj.setRota(rota);
        return obj;
    }
}

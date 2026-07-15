package com.luis.sistema_rotas.infra.minio;

import com.luis.sistema_rotas.exceptions.DataIntegrityViolationException;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

@Service
public class MinioService {

    @Autowired
    private final MinioConfig config;

    @Autowired
    private MinioClient minioClient;

    public MinioService(MinioConfig config) {
        this.config = config;
    }

    public String uploadImagem(MultipartFile file) throws Exception {

        String nameFile = file.getOriginalFilename();

        if (!nameFile.contains(".png") && !nameFile.contains(".jpg") && !nameFile.contains(".svg") && !nameFile.contains(".webp") && !nameFile.contains(".gif")){
            throw new DataIntegrityViolationException("Insira apenas arquivos de imagem");
        }

        String url = UUID.randomUUID() + "-" + file.getOriginalFilename();

        if (!nameFile.contains(".webp")){
            BufferedImage imagem = ImageIO.read(file.getInputStream());
            ImageIO.write(imagem, "webp", new File(url));
        }

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("imagens")
                        .object(url)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return url;
    }

    public void deletar(String url) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket("imagems")
                        .object(url)
                        .build()
        );
    }
}

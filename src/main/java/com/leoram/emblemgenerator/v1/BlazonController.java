package com.leoram.emblemgenerator.v1;

import com.google.gson.Gson;
import com.leoram.emblemgenerator.dto.Base64DataUrlDTO;
import com.leoram.emblemgenerator.dto.InputDataDTO;
import com.leoram.emblemgenerator.img.ImgBuilder;
import com.leoram.emblemgenerator.img.ImgLoader;
import com.leoram.emblemgenerator.img.ImgUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;

@RestController
@RequestMapping("/v1")
public class BlazonController {

    @Autowired
    private ImgUtilities imgUtilities;

    @Autowired
    private ImgLoader imgLoader;

    @Autowired
    private ImgBuilder imgBuilder;

    @Autowired
    private Gson gson;

    @PostMapping("/generate")
    public ResponseEntity<Base64DataUrlDTO> getBlazon(@RequestBody String body) {

        InputDataDTO input = gson.fromJson(body, InputDataDTO.class); // Получаем данные с запроса, запиисывая их по шаблону класса InputDataDTO

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String DataUrl = "data:image/png;base64,";
        try {

            ImageIO.write(imgBuilder.buildImage(input.getType(), input.getBackground(), input.getEmblems()), "png", bos);

            String base64output = Base64.getEncoder().encodeToString(bos.toByteArray()); // Кодируем в Base64

            return ResponseEntity.ok(new Base64DataUrlDTO(DataUrl + base64output)); // Возвращаем результат

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

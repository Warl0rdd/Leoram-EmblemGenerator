package com.leoram.emblemgenerator.v1;

import com.leoram.emblemgenerator.dto.Base64DataUrlDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/v1")
public class BlazonController {

    @GetMapping("/generate")
    public ResponseEntity<Base64DataUrlDTO> getBlazon(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String DataUrl = "data:image/png;base64,";
        try{
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Sanya\\IdeaProjects\\EmblemGeneratror\\src\\main\\resources\\img\\white.png"));
            ImageIO.write(img, "png", bos);
            byte[] bosBytes = bos.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(bosBytes);
            return ResponseEntity.ok(new Base64DataUrlDTO(DataUrl + encoded));

        }
        catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

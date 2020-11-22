package com.leoram.emblemgenerator.v1;

import com.leoram.emblemgenerator.dto.Base64DataUrlDTO;
import com.leoram.emblemgenerator.img.ImgUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/v1")
public class BlazonController {

    @Autowired
    private ImgUtilities imgUtilities;

    @GetMapping("/generate")
    public ResponseEntity<Base64DataUrlDTO> getBlazon(){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String DataUrl = "data:image/png;base64,";
        try{
            BufferedImage background = ImageIO.read(new File("C:\\Users\\Sanya\\IdeaProjects\\EmblemGeneratror\\src\\main\\resources\\img\\white.png")); // Достаём файлы
            BufferedImage img1 = ImageIO.read(new File("C:\\Users\\Sanya\\IdeaProjects\\EmblemGeneratror\\src\\main\\resources\\img\\test1.png"));
            BufferedImage img2 = ImageIO.read(new File("C:\\Users\\Sanya\\IdeaProjects\\EmblemGeneratror\\src\\main\\resources\\img\\test2.png"));
            BufferedImage img3 = ImageIO.read(new File("C:\\Users\\Sanya\\IdeaProjects\\EmblemGeneratror\\src\\main\\resources\\img\\test3.png"));

            int w = Math.max(background.getWidth(), img1.getWidth()); // Получаём максимальную длину и ширину
            int h = Math.max(background.getHeight(), img1.getHeight());

            BufferedImage outImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); // Создаём буффер в памяти для результата

            Graphics g = outImg.getGraphics(); // Создаём объект для работы с графикой
            g.drawImage(background, 0, 0, null); // По очереди накладываем картинки в пока-что пустой результат
            g.drawImage(img1, 0, 0, null);

            BufferedImage zSmaller = imgUtilities.toBufferedImage(img2.getScaledInstance(background.getWidth() / 2, 225, Image.SCALE_FAST));
            g.drawImage(zSmaller, 0, 0, null);
            g.drawImage(zSmaller, zSmaller.getWidth() + 1, 0, null);
            g.drawImage(imgUtilities.mirror(img3), 0, 0, null);

            ImageIO.write(outImg, "png", bos); // Результат в формате png кидае в ByteArrayOutputStream

            String base64output = Base64.getEncoder().encodeToString(bos.toByteArray()); // Кодируем в Base64

            return ResponseEntity.ok(new Base64DataUrlDTO(DataUrl + base64output)); // Возвращаем результат
        }
        catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

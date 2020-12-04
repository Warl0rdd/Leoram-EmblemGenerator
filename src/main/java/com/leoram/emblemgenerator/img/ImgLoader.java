package com.leoram.emblemgenerator.img;

import com.leoram.emblemgenerator.dto.EmblemDTO;
import com.leoram.emblemgenerator.exceptions.ImageLoaderException;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
public class ImgLoader {

    private String pathToZ = "C:\\Users\\Sanya\\IdeaProjects\\EmblemGeneratror\\src\\main\\resources\\img\\test2.png";
    private String pathToWhiteBackground = "C:\\Users\\Sanya\\IdeaProjects\\EmblemGeneratror\\src\\main\\resources\\img\\white.png";

    public BufferedImage loadImage (String type) throws ImageLoaderException {

        if(type == "Z"){
            try {
                BufferedImage Z = ImageIO.read(new File(pathToZ));
                return Z;
            }
            catch (IOException e){
                throw new ImageLoaderException("Unable to load Image");
            }
        }
        else if (type == "WhiteBackground"){
            try {
                BufferedImage back = ImageIO.read(new File(pathToWhiteBackground));
                return back;
            }
            catch (IOException e){
                throw new ImageLoaderException("Unable to load Image");
            }
        }
        else {
            throw new ImageLoaderException("Unable to load Image");
        }

    }

}

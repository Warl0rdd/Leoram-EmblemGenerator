package com.leoram.emblemgenerator.img;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ImgUtilities {

    public BufferedImage mirror(BufferedImage simg){

        int w = simg.getWidth();
        int h = simg.getHeight();

        BufferedImage mimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        for(int y = 0; y < h; y++){
            for(int lx = 0, rx = w - 1; lx < w / 2 && rx > w/2; lx++, rx--){

                int p = simg.getRGB(lx, y);
                int p2 = simg.getRGB(rx, y);
                mimg.setRGB(lx, y, p2);
                mimg.setRGB(rx, y, p);

            }
        }

        return mimg;
    }

    public BufferedImage toBufferedImage(Image img){

        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;

    }

}


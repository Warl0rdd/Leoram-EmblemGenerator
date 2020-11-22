package com.leoram.emblemgenerator.img;

import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

@Component
public class ImgMirrorer {

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


}


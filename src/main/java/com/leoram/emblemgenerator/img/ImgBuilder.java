package com.leoram.emblemgenerator.img;

import com.leoram.emblemgenerator.dto.BackgroundDTO;
import com.leoram.emblemgenerator.dto.EmblemDTO;
import com.leoram.emblemgenerator.dto.InputDataDTO;
import com.leoram.emblemgenerator.exceptions.ImageBuilderException;
import com.leoram.emblemgenerator.exceptions.ImageLoaderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Component
public class ImgBuilder {

    @Autowired
    private ImgLoader imgLoader;

    @Autowired
    private ImgUtilities imgUtilities;

    public BufferedImage buildImage(InputDataDTO.blazonType type, ArrayList<BackgroundDTO> backgrounds, ArrayList<EmblemDTO> emblems) throws ImageBuilderException, ImageLoaderException {

        BufferedImage output = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB); // Выделям буффер в памяти для результата

        if (backgrounds.isEmpty())
            throw new ImageBuilderException("No Backgrounds"); // Проверяем на пустоту входные данные
        if (emblems.isEmpty()) throw new ImageBuilderException("No Emblems");

        if (type.getInt() == 1) { // Если "type": "PLAIN"

            BufferedImage back; // Переменные для фона и эмблемы
            BufferedImage emblem;

            if (backgrounds.get(0).getColor().getType() == "WhiteBackground") { // Достаём фон и эмблему
                back = imgLoader.loadImage("WhiteBackground");
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }

            if (emblems.get(0).getType().getTypeName() == "Z") {
                emblem = imgLoader.loadImage("Z");
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }
            Graphics g = output.getGraphics(); // Получаем объект для работы с графикой
            g.drawImage(back, 0, 0, null); // Отрисовываем фон и жмблему
            g.drawImage(emblem, 0, 0, null);
        } else if (type.getInt() == 2) { // Если "type": "HORISONTAL"

            BufferedImage back; // Переменные для двух фонов и двух эмблем
            BufferedImage back2;
            BufferedImage emblem;
            BufferedImage emblem2;

            if (backgrounds.get(0).getColor().getType().equals("WhiteBackground")) { // Загружаем эмблемы и фоны, сразу адаптируя их под нужный размер
                back = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(200, 100, Image.SCALE_FAST)); //! Используя getScaledInstance() мы получаем объект класса Image, так что для дальнейшей работы с ним нам надо преобразовать его в BufferedImage
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }

            if (backgrounds.get(1).getColor().getType().equals("WhiteBackground")) {
                back2 = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(200, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }


            if (emblems.get(0).getType().getTypeName().equals("Z")) {
                emblem = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(200, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }
            if (emblems.get(1).getType().getTypeName().equals("Z")) {
                emblem2 = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(200, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }

            Graphics g = output.getGraphics();
            if (backgrounds.get(0).getPosition() == 1) { // Проверяем поле position, и в зависимости от него, отрисовываем фоны и эмблемы
                g.drawImage(back, 0, 0, null);
                g.drawImage(back2, 0, 100, null);
            } else {
                g.drawImage(back2, 0, 100, null);
                g.drawImage(back, 0, 0, null);
            }

            if (emblems.get(0).getPosition() == 1) {
                g.drawImage(emblem, 0, 0, null);
                g.drawImage(emblem2, 0, 100, null);
            } else {
                g.drawImage(emblem, 0, 100, null);
                g.drawImage(emblem2, 0, 0, null);
            }


        } else if (type.getInt() == 3) { // Если "type": "VERTICAL"

            BufferedImage back; // Переменные для двух фонов и двух эмблем
            BufferedImage back2;
            BufferedImage emblem;
            BufferedImage emblem2;

            if (backgrounds.get(0).getColor().getType().equals("WhiteBackground")) { // Загружаем эмблемы и фоны, сразу адаптируя их под нужный размер
                back = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(100, 200, Image.SCALE_FAST)); //! Используя getScaledInstance() мы получаем объект класса Image, так что для дальнейшей работы с ним нам надо преобразовать его в BufferedImage
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }

            if (backgrounds.get(1).getColor().getType().equals("WhiteBackground")) {
                back2 = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(100, 200, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }


            if (emblems.get(0).getType().getTypeName().equals("Z")) {
                emblem = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(100, 200, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }
            if (emblems.get(1).getType().getTypeName().equals("Z")) {
                emblem2 = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(100, 200, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }

            Graphics g = output.getGraphics();
            if (backgrounds.get(0).getPosition() == 1) { // Проверяем поле position, и в зависимости от него, отрисовываем фоны и эмблемы
                g.drawImage(back, 100, 0, null);
                g.drawImage(back2, 0, 0, null);
            } else {
                g.drawImage(back2, 0, 0, null);
                g.drawImage(back, 100, 0, null);
            }

            if (emblems.get(0).getPosition() == 1) {
                g.drawImage(emblem, 100, 0, null);
                g.drawImage(emblem2, 0, 0, null);
            } else {
                g.drawImage(emblem, 0, 0, null);
                g.drawImage(emblem2, 100, 0, null);
            }


        } else if (type.getInt() == 4) { // Если "type": "QUART"

            BufferedImage back; // Переменные для двух фонов и двух эмблем
            BufferedImage back2;
            BufferedImage back3;
            BufferedImage back4;
            BufferedImage emblem;
            BufferedImage emblem2;
            BufferedImage emblem3;
            BufferedImage emblem4;

            if (backgrounds.get(0).getColor().getType().equals("WhiteBackground")) { // Загружаем эмблемы и фоны, сразу адаптируя их под нужный размер
                back = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(100, 100, Image.SCALE_FAST)); //! Используя getScaledInstance() мы получаем объект класса Image, так что для дальнейшей работы с ним нам надо преобразовать его в BufferedImage
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }

            if (backgrounds.get(1).getColor().getType().equals("WhiteBackground")) {
                back2 = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(100, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }

            if (backgrounds.get(2).getColor().getType().equals("WhiteBackground")) { // Загружаем эмблемы и фоны, сразу адаптируя их под нужный размер
                back3 = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(100, 100, Image.SCALE_FAST)); //! Используя getScaledInstance() мы получаем объект класса Image, так что для дальнейшей работы с ним нам надо преобразовать его в BufferedImage
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }

            if (backgrounds.get(3).getColor().getType().equals("WhiteBackground")) {
                back4 = imgUtilities.toBufferedImage(imgLoader.loadImage("WhiteBackground").getScaledInstance(100, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Background Does not Exists!");
            }


            if (emblems.get(0).getType().getTypeName().equals("Z")) {
                emblem = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(100, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }
            if (emblems.get(1).getType().getTypeName().equals("Z")) {
                emblem2 = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(100, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }

            if (emblems.get(2).getType().getTypeName().equals("Z")) {
                emblem3 = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(100, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }
            if (emblems.get(3).getType().getTypeName().equals("Z")) {
                emblem4 = imgUtilities.toBufferedImage(imgLoader.loadImage("Z").getScaledInstance(100, 100, Image.SCALE_FAST));
            } else {
                throw new ImageBuilderException("Emblem Does not Exists!");
            }

            Graphics g = output.getGraphics();

            switch (backgrounds.get(0).getPosition()) {

                case 1:
                    g.drawImage(back, 100, 0, null);
                    switch (backgrounds.get(1).getPosition()) {
                        case 2:
                            g.drawImage(back2, 100, 100, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 3:
                                    g.drawImage(back3, 0, 100, null);
                                    g.drawImage(back4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(back3, 0, 0, null);
                                    g.drawImage(back4, 0, 100, null);
                                    break;
                            }
                        case 3:
                            g.drawImage(back2, 0, 100, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(back3, 100, 100, null);
                                    g.drawImage(back4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(back3, 0, 0, null);
                                    g.drawImage(back4, 100, 100, null);
                                    break;
                            }
                        case 4:
                            g.drawImage(back2, 0, 0, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(back3, 100, 100, null);
                                    g.drawImage(back4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(back3, 0, 100, null);
                                    g.drawImage(back4, 100, 100, null);
                                    break;
                            }


                    }

                case 2:
                    g.drawImage(back, 100, 100, null);
                    switch (backgrounds.get(1).getPosition()) {
                        case 1:
                            g.drawImage(back2, 100, 0, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 3:
                                    g.drawImage(back3, 0, 100, null);
                                    g.drawImage(back4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(back3, 0, 0, null);
                                    g.drawImage(back4, 0, 100, null);
                                    break;
                            }
                        case 3:
                            g.drawImage(back2, 0, 100, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(back3, 100, 0, null);
                                    g.drawImage(back4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(back3, 0, 0, null);
                                    g.drawImage(back4, 100, 0, null);
                                    break;
                            }
                        case 4:
                            g.drawImage(back2, 0, 0, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(back3, 100, 0, null);
                                    g.drawImage(back4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(back3, 0, 100, null);
                                    g.drawImage(back4, 100, 0, null);
                                    break;
                            }
                    }

                case 3:
                    g.drawImage(back, 0, 100, null);
                    switch (backgrounds.get(1).getPosition()) {
                        case 1:
                            g.drawImage(back2, 100, 0, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(back3, 100, 100, null);
                                    g.drawImage(back4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(back3, 0, 0, null);
                                    g.drawImage(back4, 100, 100, null);
                                    break;
                            }
                        case 2:
                            g.drawImage(back2, 100, 100, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(back3, 100, 0, null);
                                    g.drawImage(back4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(back3, 0, 0, null);
                                    g.drawImage(back4, 100, 0, null);
                                    break;
                            }
                        case 4:
                            g.drawImage(back2, 0, 0, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(back3, 100, 0, null);
                                    g.drawImage(back4, 100, 100, null);
                                    break;
                                case 2:
                                    g.drawImage(back3, 100, 100, null);
                                    g.drawImage(back4, 100, 0, null);
                                    break;
                            }
                    }

                case 4:
                    g.drawImage(back, 0, 0, null);
                    switch (backgrounds.get(1).getPosition()) {
                        case 1:
                            g.drawImage(back2, 100, 0, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(back3, 100, 100, null);
                                    g.drawImage(back4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(back3, 0, 100, null);
                                    g.drawImage(back4, 100, 100, null);
                                    break;
                            }
                        case 2:
                            g.drawImage(back2, 100, 100, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(back3, 100, 0, null);
                                    g.drawImage(back4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(back3, 0, 100, null);
                                    g.drawImage(back4, 100, 0, null);
                                    break;
                            }
                        case 3:
                            g.drawImage(back2, 0, 100, null);
                            switch (backgrounds.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(back3, 100, 0, null);
                                    g.drawImage(back4, 100, 100, null);
                                    break;
                                case 2:
                                    g.drawImage(back3, 100, 100, null);
                                    g.drawImage(back4, 100, 0, null);
                                    break;
                            }
                    }


            }
            switch (emblems.get(0).getPosition()) {

                case 1:
                    g.drawImage(emblem, 100, 0, null);
                    switch (emblems.get(1).getPosition()) {
                        case 2:
                            g.drawImage(emblem2, 100, 100, null);
                            switch (emblems.get(2).getPosition()) {
                                case 3:
                                    g.drawImage(emblem3, 0, 100, null);
                                    g.drawImage(emblem4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(emblem3, 0, 0, null);
                                    g.drawImage(emblem4, 0, 100, null);
                                    break;
                            }
                        case 3:
                            g.drawImage(emblem2, 0, 100, null);
                            switch (emblems.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(emblem3, 100, 100, null);
                                    g.drawImage(emblem4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(emblem3, 0, 0, null);
                                    g.drawImage(emblem4, 100, 100, null);
                                    break;
                            }
                        case 4:
                            g.drawImage(emblem2, 0, 0, null);
                            switch (emblems.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(emblem3, 100, 100, null);
                                    g.drawImage(emblem4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(emblem3, 0, 100, null);
                                    g.drawImage(emblem4, 100, 100, null);
                                    break;
                            }


                    }

                case 2:
                    g.drawImage(emblem, 100, 100, null);
                    switch (emblems.get(1).getPosition()) {
                        case 1:
                            g.drawImage(emblem2, 100, 0, null);
                            switch (emblems.get(2).getPosition()) {
                                case 3:
                                    g.drawImage(emblem3, 0, 100, null);
                                    g.drawImage(emblem4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(emblem3, 0, 0, null);
                                    g.drawImage(emblem4, 0, 100, null);
                                    break;
                            }
                        case 3:
                            g.drawImage(emblem2, 0, 100, null);
                            switch (emblems.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(emblem3, 100, 0, null);
                                    g.drawImage(emblem4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(emblem3, 0, 0, null);
                                    g.drawImage(emblem4, 100, 0, null);
                                    break;
                            }
                        case 4:
                            g.drawImage(emblem2, 0, 0, null);
                            switch (emblems.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(emblem3, 100, 0, null);
                                    g.drawImage(emblem4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(emblem3, 0, 100, null);
                                    g.drawImage(emblem4, 100, 0, null);
                                    break;
                            }
                    }

                case 3:
                    g.drawImage(emblem, 0, 100, null);
                    switch (emblems.get(1).getPosition()) {
                        case 1:
                            g.drawImage(emblem2, 100, 0, null);
                            switch (emblems.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(emblem3, 100, 100, null);
                                    g.drawImage(emblem4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(emblem3, 0, 0, null);
                                    g.drawImage(emblem4, 100, 100, null);
                                    break;
                            }
                        case 2:
                            g.drawImage(emblem2, 100, 100, null);
                            switch (emblems.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(emblem3, 100, 0, null);
                                    g.drawImage(emblem4, 0, 0, null);
                                    break;
                                case 4:
                                    g.drawImage(emblem3, 0, 0, null);
                                    g.drawImage(emblem4, 100, 0, null);
                                    break;
                            }
                        case 4:
                            g.drawImage(emblem2, 0, 0, null);
                            switch (emblems.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(emblem3, 100, 0, null);
                                    g.drawImage(emblem4, 100, 100, null);
                                    break;
                                case 2:
                                    g.drawImage(emblem3, 100, 100, null);
                                    g.drawImage(emblem4, 100, 0, null);
                                    break;
                            }
                    }

                case 4:
                    g.drawImage(emblem, 0, 0, null);
                    switch (emblems.get(1).getPosition()) {
                        case 1:
                            g.drawImage(emblem2, 100, 0, null);
                            switch (emblems.get(2).getPosition()) {
                                case 2:
                                    g.drawImage(emblem3, 100, 100, null);
                                    g.drawImage(emblem4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(emblem3, 0, 100, null);
                                    g.drawImage(emblem4, 100, 100, null);
                                    break;
                            }
                        case 2:
                            g.drawImage(emblem2, 100, 100, null);
                            switch (emblems.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(emblem3, 100, 0, null);
                                    g.drawImage(emblem4, 0, 100, null);
                                    break;
                                case 3:
                                    g.drawImage(emblem3, 0, 100, null);
                                    g.drawImage(emblem4, 100, 0, null);
                                    break;
                            }
                        case 3:
                            g.drawImage(emblem2, 0, 100, null);
                            switch (emblems.get(2).getPosition()) {
                                case 1:
                                    g.drawImage(emblem3, 100, 0, null);
                                    g.drawImage(emblem4, 100, 100, null);
                                    break;
                                case 2:
                                    g.drawImage(emblem3, 100, 100, null);
                                    g.drawImage(emblem4, 100, 0, null);
                                    break;
                            }
                    }


            }

        }
        return output; // Возвращаем результат
    }
}
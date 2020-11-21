//TODO: Данные на выходе: Результат нужно вернуть в формате data url вкодировке Base64
//TODO: Дуинг данные на входе

package com.leoram.emblemgenerator;

import com.leoram.emblemgenerator.v1.BlazonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmblemgeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmblemgeneratorApplication.class, args);
    }

}

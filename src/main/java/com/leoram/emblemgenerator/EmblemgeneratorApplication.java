//!TODO: Входные деанные: В мир торчит контроллер (/v1/blazon) который принимает на вход данные (JSON) для генерации.
//!TODO: Концепт генератора: пока что условимся что генератор это черный ящик который что-то должен делать. Сделай дефолтное поведение при котором он будет отдавть просто картинку с белым фоном
//!TODO: Данные на выходе: Результат нужно вернуть в формате data url вкодировке Base64

package com.leoram.emblemgenerator;

import com.leoram.emblemgenerator.v1.BlazonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmblemgeneratorApplication {

    @Autowired
    private static BlazonController controller;

    public static void main(String[] args) {
        SpringApplication.run(EmblemgeneratorApplication.class, args);
        System.out.println(controller.getBlazon().toString());
    }

}

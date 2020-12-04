package com.leoram.emblemgenerator.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class BackgroundDTO {

    public static enum color {
        WHITE("WhiteBackground"),
        BLACK("BlackBackground"),
        RED("RedBackground"),
        ORANGE("OrangeBackground"),
        YELLOW("YellowBackground"),
        GREEN("GreenBackground"),
        BLUE("BlueBackground"),
        BROWN("BrownBackground");

        private final String type;

        color(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    @NonNull
    @Getter
    @Setter
    private color color;

    @NonNull
    @Getter
    @Setter
    private int position;

}

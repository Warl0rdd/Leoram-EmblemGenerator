package com.leoram.emblemgenerator.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class EmblemDTO {

    public enum color {
        WHITE,
        BLACK,
        RED,
        ORANGE,
        YELLOW,
        GREEN,
        BLUE,
        BROWN;

    }

    public static enum type {
        Z("Z");

        private final String type;

        type(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return type;
        }
    }

    @Getter
    @NonNull
    @Setter
    private color color;

    @Getter
    @NonNull
    @Setter
    private type type;

    @Getter
    @NonNull
    @Setter
    private int position;

}

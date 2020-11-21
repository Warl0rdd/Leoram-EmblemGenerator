package com.leoram.emblemgenerator.dto;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InputDataDTO {

    public enum blazonType {
        HORISONTAL,
        VERTICAL,
        QUART,
        PLAIN
    }

    @Getter
    @NonNull        
    ArrayList<String> background;

    @Getter
    @NonNull
    ArrayList<Integer> emblems;

    @Getter
    @NonNull
    blazonType type;

}

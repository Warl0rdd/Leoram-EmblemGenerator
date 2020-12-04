package com.leoram.emblemgenerator.dto;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InputDataDTO {

    public enum blazonType {
        PLAIN(1),
        HORISONTAL(2),
        VERTICAL(3),
        QUART(4);

        private final int Int;

        blazonType(int Int) {
            this.Int = Int;
        }

        public int getInt() {
            return Int;
        }
    }

    @Getter
    @NonNull
    @Setter        
    ArrayList<BackgroundDTO> background;

    @Getter
    @NonNull
    @Setter        
    ArrayList<EmblemDTO> emblems;

    @Getter
    @NonNull
    @Setter        
    blazonType type;

}

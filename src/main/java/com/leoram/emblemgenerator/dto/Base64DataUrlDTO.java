package com.leoram.emblemgenerator.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Base64DataUrlDTO {

    @Getter
    @NonNull
    private String data;

}
